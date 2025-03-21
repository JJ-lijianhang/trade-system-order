package com.futurebank.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.futurebank.pojo.base.CodeEunm;
import com.futurebank.pojo.enums.*;
import com.futurebank.pojo.exception.CustomException;
import com.futurebank.pojo.utils.BigDecimalUtils;
import com.futurebank.pojo.utils.DateUtils;
import com.futurebank.pojo.utils.FuturebankUtil;
import com.futurebank.pojo.utils.SingUtils;
import com.futurebank.pojo.vo.notify.NotificationMessage;
import com.futurebank.pojo.vo.notify.NotificationRequestItem;
import com.futurebank.pojo.vo.payment.Amount;
import com.futurebank.pojo.vo.payment.PaymentRequest;
import com.futurebank.order.entity.*;
import com.futurebank.order.service.*;
import com.futurebank.order.service.payin.PayinRefundService;
import com.futurebank.order.service.producer.SendMQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.futurebank.pojo.vo.notify.NotificationRequestItem.EVENT_CODE_REFUND;

/**
 * @author ben
 * @date 2024/3/31 23:20
 **/
@Slf4j
@Service("payin-refund-ppro")
@Primary
class PayinRefundPPROServiceImpl implements PayinRefundService {
    @Autowired
    PaymentOrderService paymentOrderSercice;

    @Autowired
    IdGeneratorService idGeneratorService;

    @Autowired
    HttpClientService httpClientService;
    @Autowired
    PaymentOrderUpstreamService paymentOrderUpstreamService;
    @Autowired
    PaymentOrderDownstreamService paymentOrderDownstreamService;

    @Autowired
    SendMQService sendMQService;

    @Autowired
    MerchantService merchantService;
    @Autowired
    FundsHandleService fundsHandleService;

    @Autowired
    MerchantChargeService merchantChargeService;

    @Autowired
    PaymentBillService paymentBillService;

    @Autowired
    PaymentBillingService paymentBillingService;

    @Override
    public void refund(PaymentOrderEntity paymentOrderEntity, PaymentProviderEntity paymentProviderEntity) throws Exception {
        log.info("paymentOrderEntity={}", paymentOrderEntity);
        //获取原始订单
        PaymentOrderEntity paymentOrderOri = paymentOrderSercice.getPaymentOrderByOrderNo(paymentOrderEntity.getReferenceOrderNo());
        if (paymentOrderOri == null)
            log.info("原始订单不存在 订单号={}", paymentOrderEntity.getDownstreamOrderNo());

        String postUrl = getUrl(paymentProviderEntity);
        Map<String, Object> postMap = getRefundParams(paymentProviderEntity);
        postMap.put("reftxid", paymentOrderOri.getUpstreamOrderNo());
        postMap.put("currency", paymentOrderEntity.getCurrency());
        postMap.put("amount", BigDecimalUtils.multiply(paymentOrderEntity.getOrderAmount(), paymentOrderEntity.getCurrency()));
        postMap.put("merchantrefundid", paymentOrderEntity.getPlatformId() == null ? null : paymentOrderEntity.getPlatformId() + "");

        log.info("postMap={}", JSON.toJSONString(postMap));
        log.info(" refund request providerName={},url={}, postMap={}", paymentProviderEntity.getProviderName(), postUrl, JSON.toJSONString(postMap));

        Map<String, String> respMap = httpClientService.postWithPPRO(postUrl, postMap, null,null);

        log.info(" refund response privatename={} refund url={}, postMap={}, respMap={}", paymentProviderEntity.getProviderName(), postUrl, postMap, respMap);

        if (respMap == null) return;
        PaymentRequest orderRequest = new PaymentRequest();
        orderRequest.setReference(paymentOrderEntity.getDownstreamOrderNo());

        //修改上游记录表
        String status = respMap.get("STATUS").equals("SUCCEEDED") ? "SUCCEEDED" : "";
        paymentOrderUpstreamService.addPaymentOrderUpstream(orderRequest, paymentOrderEntity.getPlatformOrderNo(), postMap, postUrl, null, respMap.get("REFUNDID"), status, JSON.toJSONString(respMap), null, null, "SUCCEEDED");

        //修改订单表
        paymentOrderEntity.setUpstreamOrderNo(respMap.get("REFUNDID") + "");
        paymentOrderEntity.setUpstreamStatus(respMap.get("STATUS"));

        //失败时，结束订单
        if (respMap.get("STATUS").equals("FAILED")) {
            paymentOrderEntity.setOrderStatus(PaymentStatusEnum.交易失败.getStatus());
            paymentOrderEntity.setFailReason(respMap.get("FAILREASON"));
            paymentOrderEntity.setErrorMsg(respMap.get("ERRMSG"));
            paymentOrderEntity.setOrderCompleteTime(new Date());
            if (paymentOrderEntity.getFreezeType().equals(1)) {
                fundsHandleService.refundBack(paymentOrderEntity);
                paymentOrderEntity.setFreezeType(2);
            }
        } else if (respMap.get("STATUS").equals("SUCCEEDED")) {
            paymentOrderEntity.setOrderStatus(PaymentStatusEnum.交易成功.getStatus());
            paymentOrderEntity.setUpstreamStatus(respMap.get("STATUS"));
            paymentOrderEntity.setOrderCompleteTime(new Date());
        } else {
            paymentOrderEntity.setUpstreamStatus(respMap.get("STATUS"));
        }
        paymentOrderSercice.update(paymentOrderEntity);

        PaymentOrderDownstreamEntity paymentOrderDownstreamEntity = paymentOrderDownstreamService.getPaymentOrderDownstreamByOrderNo(paymentOrderEntity.getPlatformOrderNo());
        paymentOrderDownstreamEntity.setUpstreamOrderNo(respMap.get("REFUNDID"));
        paymentOrderDownstreamService.update(paymentOrderDownstreamEntity);


        NotificationMessage refundNotifyMessage = new NotificationMessage();
        List<NotificationRequestItem> notificationRequestItems = new ArrayList<>();
        NotificationRequestItem notificationRequestItem = new NotificationRequestItem();


        refundNotifyMessage.setAppId(paymentOrderEntity.getProductId() + "");
        refundNotifyMessage.setMerchantId(paymentOrderEntity.getMerchantId() + "");
        notificationRequestItem.setOriginalReference(paymentOrderEntity.getReferenceOrderNo());
        notificationRequestItem.setPspReference(paymentOrderEntity.getPlatformOrderNo());
        notificationRequestItem.setMerchantReference(paymentOrderEntity.getDownstreamOrderNo());
//        notificationRequestItem.setCurrency(paymentOrderEntity.getCurrency());
        Amount amount = new Amount();
        amount.setCurrency(paymentOrderEntity.getMerchantCurrency());
        amount.setValue(Long.parseLong(BigDecimalUtils.multiply(paymentOrderEntity.getMerchantOrderAmount(), paymentOrderEntity.getMerchantCurrency())));
        notificationRequestItem.setPaymentMethod(paymentOrderEntity.getPaymentMethod());
        notificationRequestItem.setResultCode(paymentOrderEntity.getOrderStatus());
        notificationRequestItem.setReason(paymentOrderEntity.getErrorMsg());
        notificationRequestItem.setEventCode(EVENT_CODE_REFUND);
        notificationRequestItem.setEventDate(DateUtils.nowDateTime());
        notificationRequestItem.setAmount(amount);

        notificationRequestItems.add(notificationRequestItem);
        refundNotifyMessage.setNotificationItems(notificationRequestItems);
        Map<String, Object> payinMap = JSON.parseObject(JSON.toJSONString(refundNotifyMessage), Map.class);
        MerchantEntity merchantEntity = merchantService.getMerchant(paymentOrderEntity.getMerchantId() + "");
        String sign = SingUtils.getSign(payinMap, merchantEntity.getPayinSecurityKey());
        refundNotifyMessage.setSign(sign);
        refundNotifyMessage.setWebhookUrl(paymentOrderEntity.getDownstreamNotifyUrl());
        refundNotifyMessage.setProviderReference(paymentOrderEntity.getUpstreamOrderNo());
        updateBill(paymentOrderEntity);
        sendMQService.refundNotify(JSON.toJSONString(refundNotifyMessage));


    }


    private void updateBill(PaymentOrderEntity paymentOrderEntity) throws Exception {
        PaymentBillingEntity paymentBillingEntity = new PaymentBillingEntity();
        BeanUtils.copyProperties(paymentOrderEntity, paymentBillingEntity);
//        paymentBillingEntity.setBillType(paymentBillEntity.getOrderType());
        boolean b = paymentBillingService.add(paymentBillingEntity);
        if (!b) {
            log.info("账单表 订单号={} 插入失败 Data= {}", paymentOrderEntity.getPlatformOrderNo(), JSON.toJSONString(paymentBillingEntity));
        }
    }

    private String getUrl(PaymentProviderEntity paymentProviderEntity) throws Exception {
        String payinConfig = paymentProviderEntity.getPayinConfig();

        if (StringUtils.isBlank(payinConfig))
            throw new CustomException(CodeEunm.其他原因.getCode(), CodeEunm.其他原因.getMsg());

        //请求地址
        String url = FuturebankUtil.getConfigValue(payinConfig, "refund.url");

        log.info("ppro refund url={}", url);
        return url;
    }

    /**
     * 获取支付配置参数
     *
     * @param paymentProviderEntity
     * @return
     */
    private Map<String, Object> getRefundParams(PaymentProviderEntity paymentProviderEntity) throws Exception {
        String payinConfig = paymentProviderEntity.getPayinConfig();

        if (StringUtils.isBlank(payinConfig))
            throw new CustomException(CodeEunm.其他原因.getCode(), CodeEunm.其他原因.getMsg());

        String fixedContent = FuturebankUtil.getConfigValue(payinConfig, "refund.fixedContent");
        log.info("ppro refund fixedContent : {}", fixedContent);
        if (StringUtils.isBlank(fixedContent))
            throw new CustomException(CodeEunm.其他原因.getCode(), CodeEunm.其他原因.getMsg());

        return JSON.parseObject(fixedContent, Map.class);
    }
}
