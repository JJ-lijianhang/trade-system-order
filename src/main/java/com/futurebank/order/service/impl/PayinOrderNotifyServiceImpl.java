package com.futurebank.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.futurebank.pojo.enums.PaymentStatusEnum;
import com.futurebank.pojo.utils.DateUtils;
import com.futurebank.pojo.utils.ValidationUtils;
import com.futurebank.pojo.vo.notify.NotificationMessage;
import com.futurebank.pojo.vo.notify.NotificationResponse;
import com.futurebank.order.entity.PaymentOrderDownstreamEntity;
import com.futurebank.order.service.HttpClientService;
import com.futurebank.order.service.PaymentOrderDownstreamService;
import com.futurebank.order.service.payin.PayinOrderNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ben
 * @date 2024/3/21 17:31
 **/
@Slf4j
@Service
public class PayinOrderNotifyServiceImpl implements PayinOrderNotifyService {

    @Autowired
    private PaymentOrderDownstreamService paymentOrderDownstreamService;

    @Autowired
    private HttpClientService httpClientService;

    @Override
    public boolean payinOrderNotify(String notifyData) {
        log.info("支付订单通知数据：{}", notifyData);
        if (StringUtils.isBlank(notifyData)) {
            log.error("支付订单通知数据为空");
            return true;
        }

        NotificationMessage payinNotifyMessage = JSON.parseObject(notifyData, NotificationMessage.class);

        if (payinNotifyMessage == null) {
            log.error("支付订单通知数据为空");
            return true;
        }

        String errorMsg = ValidationUtils.validateBean(payinNotifyMessage);
        if (StringUtils.isNotBlank(errorMsg)) {
            log.error("支付订单通知数据校验失败：{}", errorMsg);
            return true;
        }


        //设置请求头
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("Authorization", payinNotifyMessage.getSign());
        headerMap.put("merchantId", payinNotifyMessage.getMerchantId());
        headerMap.put("appId", payinNotifyMessage.getAppId());
        headerMap.put("curTime", DateUtils.nowDateTimeStr());

        //构建通知数据
        NotificationResponse notifyResponse = new NotificationResponse();
        BeanUtils.copyProperties(payinNotifyMessage, notifyResponse);

        String key = "payin:notify:orderno:" + payinNotifyMessage.getNotificationItems().get(0).getPspReference();


        //发送通知
        String notifyContent = JSON.toJSONString(notifyResponse);
        if (notifyResponse.getNotificationItems().get(0).getResultCode().equals(PaymentStatusEnum.交易处理中.getStatus())) {
            return true;
        }
        String body = httpClientService.postNotify(payinNotifyMessage.getWebhookUrl(), notifyContent, headerMap, null);
        if (!StringUtils.isBlank(body)) {
            body = body.replace("\"", "");
            body = body.trim();
            body = body.toLowerCase();

        }

        log.info("orderid:{} url:{} 通知内容：{} 请求头:{} 返回内容：{}", notifyResponse.getNotificationItems().get(0).getPspReference(), payinNotifyMessage.getWebhookUrl(), notifyResponse, headerMap, body);

        //更新下游订单表
        PaymentOrderDownstreamEntity paymentOrderDownstreamEntity = paymentOrderDownstreamService.getPaymentOrderDownstreamByOrderNo(payinNotifyMessage.getNotificationItems().get(0).getPspReference());
        if (paymentOrderDownstreamEntity == null) {
            log.error("支付订单通知数据校验失败：{} 下游订单不存在", payinNotifyMessage.getNotificationItems().get(0).getPspReference());
            return true;
        }
        int notifyTimes = paymentOrderDownstreamEntity.getNottifyTimes() == null ? 0 : paymentOrderDownstreamEntity.getNottifyTimes();

        if (notifyTimes >= 100 && payinNotifyMessage.getType() == null) return true;
        String downstreamNotifyStatus = StringUtils.isBlank(paymentOrderDownstreamEntity.getDownstreamNotifyStatus()) ? "" : paymentOrderDownstreamEntity.getDownstreamNotifyStatus();
//        if (downstreamNotifyStatus.equals(PaymentStatusEnum.交易成功.getStatus()))
//            return true;
        if (paymentOrderDownstreamEntity.getDownstreamSuccessTime() != null)
            return true;
        if (notifyResponse.getNotificationItems().get(0).getResultCode().equals(PaymentStatusEnum.交易成功.getStatus())) {
            paymentOrderDownstreamEntity.setDownstreamSuccessTime(new Date());
        }
        paymentOrderDownstreamEntity.setDownstreamNotifyTime(new Date());
//        paymentOrderDownstreamEntity.setDownstreamNotifyStatus(notifyResponse.getTradeStatus());
        paymentOrderDownstreamEntity.setDownstreamNotifyStatus(body);
        paymentOrderDownstreamEntity.setDownstreamNotifyContent(notifyContent);
        paymentOrderDownstreamEntity.setUpdatedAt(new Date());
        paymentOrderDownstreamEntity.setNottifyTimes(notifyTimes + 1);
        paymentOrderDownstreamService.update(paymentOrderDownstreamEntity);
        if (notifyResponse.getNotificationItems().get(0).getResultCode().equals(PaymentStatusEnum.交易失败.getStatus()))
            return true;
        if (StringUtils.isBlank(body)) return false;
        if (!(body.equalsIgnoreCase("success"))) return false;
        return true;
    }


}
