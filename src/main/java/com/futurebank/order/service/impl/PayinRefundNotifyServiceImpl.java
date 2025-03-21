package com.futurebank.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.futurebank.pojo.enums.PaymentStatusEnum;
import com.futurebank.pojo.utils.DateUtils;
import com.futurebank.pojo.utils.ValidationUtils;
import com.futurebank.pojo.vo.notify.NotificationMessage;
import com.futurebank.pojo.vo.notify.NotificationResponse;
import com.futurebank.pojo.vo.refund.RefundResponse;
import com.futurebank.pojo.vo.refundNotify.RefundNotifyMessage;
import com.futurebank.order.entity.PaymentOrderDownstreamEntity;
import com.futurebank.order.service.HttpClientService;
import com.futurebank.order.service.PaymentOrderDownstreamService;
import com.futurebank.order.service.payin.PayinOrderNotifyService;
import com.futurebank.order.service.payin.PayinRefundNotifyService;
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
public class PayinRefundNotifyServiceImpl implements PayinRefundNotifyService {

    @Autowired
    private PaymentOrderDownstreamService paymentOrderDownstreamService;

    @Autowired
    private HttpClientService httpClientService;

    @Override
    public boolean payinRefundNotify(String refundNotifyData) {
        log.info("支付退款通知数据：{}", refundNotifyData);


        if (StringUtils.isBlank(refundNotifyData)) {
            log.error("支付订单通知数据为空");
            return true;
        }

        NotificationMessage refundNotifyMessage = JSON.parseObject(refundNotifyData, NotificationMessage.class);

        if (refundNotifyMessage == null) {
            log.error("支付订单通知数据为空");
            return true;
        }

        String errorMsg = ValidationUtils.validateBean(refundNotifyMessage);
        if (StringUtils.isNotBlank(errorMsg)) {
            log.error("支付订单通知数据校验失败：{}", errorMsg);
            return true;
        }


        //设置请求头
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("Authorization", refundNotifyMessage.getSign());
        headerMap.put("merchantId", refundNotifyMessage.getMerchantId());
        headerMap.put("appId", refundNotifyMessage.getAppId());
        headerMap.put("curTime", DateUtils.nowDateTimeStr());

        //构建通知数据
        NotificationResponse refundResponse = new NotificationResponse();
        BeanUtils.copyProperties(refundNotifyMessage, refundResponse);


        //发送通知
        String notifyContent = JSON.toJSONString(refundResponse);
        String body = httpClientService.postNotify(refundNotifyMessage.getWebhookUrl(), notifyContent, headerMap,null);
        log.info("orderid:{} url:{} 通知内容：{} 请求头:{} 返回内容：{}", refundResponse.getNotificationItems().get(0).getPspReference(), refundNotifyMessage.getWebhookUrl(), refundResponse, headerMap, body);

        //更新下游订单表
        PaymentOrderDownstreamEntity paymentOrderDownstreamEntity = paymentOrderDownstreamService.getPaymentOrderDownstreamByOrderNo(refundNotifyMessage.getNotificationItems().get(0).getPspReference());
        if (paymentOrderDownstreamEntity == null) {
            log.error("支付订单通知数据校验失败：{} 下游订单不存在", refundNotifyMessage.getNotificationItems().get(0).getPspReference());
            return true;
        }
        int notifyTimes = paymentOrderDownstreamEntity.getNottifyTimes() == null ? 0 : paymentOrderDownstreamEntity.getNottifyTimes();
        if (notifyTimes >= 26) return true;
        String downstreamNotifyStatus = StringUtils.isBlank(paymentOrderDownstreamEntity.getDownstreamNotifyStatus()) ? "" : paymentOrderDownstreamEntity.getDownstreamNotifyStatus();
        if (downstreamNotifyStatus.equals(PaymentStatusEnum.交易成功.getStatus()))
            return true;
        log.info("notifyResponse.getTradeStatus()：{} PaymentStatusEnum.成功.getStatus(): {}", refundResponse.getNotificationItems().get(0).getResultCode(), PaymentStatusEnum.交易成功.getStatus());
        if (refundResponse.getNotificationItems().get(0).getResultCode().equals(PaymentStatusEnum.交易成功.getStatus()))
            paymentOrderDownstreamEntity.setDownstreamSuccessTime(new Date());
        paymentOrderDownstreamEntity.setDownstreamNotifyTime(new Date());
//        paymentOrderDownstreamEntity.setDownstreamNotifyStatus(refundResponse.getTradeStatus());
        paymentOrderDownstreamEntity.setDownstreamNotifyStatus(body);
        paymentOrderDownstreamEntity.setDownstreamRequestMessage(notifyContent);
        paymentOrderDownstreamEntity.setDownstreamResponseMessage(body);
        paymentOrderDownstreamEntity.setDownstreamNotifyContent(notifyContent);
        paymentOrderDownstreamEntity.setUpdatedAt(new Date());
        paymentOrderDownstreamEntity.setNottifyTimes(notifyTimes + 1);
        paymentOrderDownstreamService.update(paymentOrderDownstreamEntity);

        if (StringUtils.isBlank(body)) return false;
        if (!body.equals("success")) return false;
        return true;
    }

}
