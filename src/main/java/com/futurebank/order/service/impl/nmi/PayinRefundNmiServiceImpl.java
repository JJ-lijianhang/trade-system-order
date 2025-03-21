package com.futurebank.order.service.impl.nmi;

import com.alibaba.fastjson.JSON;
import com.futurebank.cache.RedisCache;
import com.futurebank.order.entity.MerchantEntity;
import com.futurebank.order.entity.PaymentBillingEntity;
import com.futurebank.order.entity.PaymentOrderDownstreamEntity;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.entity.PaymentProviderEntity;
import com.futurebank.order.service.FundsHandleService;
import com.futurebank.order.service.HttpClientService;
import com.futurebank.order.service.IdGeneratorService;
import com.futurebank.order.service.MerchantService;
import com.futurebank.order.service.MerchantWalletService;
import com.futurebank.order.service.PaymentBillService;
import com.futurebank.order.service.PaymentBillingService;
import com.futurebank.order.service.PaymentOrderDownstreamService;
import com.futurebank.order.service.PaymentOrderService;
import com.futurebank.order.service.PaymentOrderUpstreamService;
import com.futurebank.order.service.payin.PayinRefundQueryService;
import com.futurebank.order.service.payin.PayinRefundService;
import com.futurebank.order.service.producer.SendMQService;
import com.futurebank.pojo.enums.PaymentStatusEnum;
import com.futurebank.pojo.utils.BigDecimalUtils;
import com.futurebank.pojo.utils.DateUtils;
import com.futurebank.pojo.utils.FuturebankUtil;
import com.futurebank.pojo.utils.SingUtils;
import com.futurebank.pojo.vo.notify.NotificationMessage;
import com.futurebank.pojo.vo.notify.NotificationRequestItem;
import com.futurebank.pojo.vo.payment.Amount;
import com.futurebank.pojo.vo.payment.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.futurebank.pojo.vo.notify.NotificationRequestItem.EVENT_CODE_REFUND;

/**
 * @author ben
 * @date 2024/3/31 23:20
 **/
@Slf4j
@Service("payin-refund-nmi")
class PayinRefundNmiServiceImpl implements PayinRefundService {
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
    PaymentOrderService paymentOrderService;
    @Autowired
    RedisCache redisCache;
    @Autowired
    MerchantWalletService merchantWalletService;
    @Autowired
    PaymentBillService paymentBillService;
    @Autowired
    PaymentBillingService paymentBillingService;
    @Autowired
    @Qualifier("refund-query-nmi")
    PayinRefundQueryService payinRefundQueryService;


    @Override
    public void refund(PaymentOrderEntity paymentOrderEntity, PaymentProviderEntity paymentProviderEntity) throws Exception {
        MerchantEntity merchantEntity = merchantService.getMerchant(paymentOrderEntity.getMerchantId() + "");

        if (merchantEntity == null) log.error("nmi 未找到商户信息 {}", paymentOrderEntity.getDownstreamOrderNo());


        log.info("paymentOrderEntity={}", paymentOrderEntity);
        //获取原始订单
        PaymentOrderEntity paymentOrderOri = paymentOrderSercice.getPaymentOrderByOrderNo(paymentOrderEntity.getReferenceOrderNo());
        if (paymentOrderOri == null) log.info("原始订单不存在 订单号={}", paymentOrderEntity.getDownstreamOrderNo());

        String payinConfig = paymentProviderEntity.getPayinConfig();

        if (StringUtils.isBlank(payinConfig))
            log.error("nmi 未找到相关配置 {}", paymentOrderEntity.getDownstreamOrderNo());

        String postUrl = FuturebankUtil.getConfigValue(payinConfig, "refund.url");
        String securityKey = FuturebankUtil.getConfigValue(payinConfig, "securityKey");
        String requestId = idGeneratorService.snowFlake(1L) + "";
        if (StringUtils.isBlank(postUrl) || StringUtils.isBlank(securityKey))
            log.error("nmi url|securityKey 配置异常 {}", paymentOrderEntity.getDownstreamOrderNo());
        if (StringUtils.isBlank(requestId))
            log.error("nmi requestId 生成异常 {}", paymentOrderEntity.getDownstreamOrderNo());
        DecimalFormat form = new DecimalFormat("#.00");

        Map<String, Object> postMap = new HashMap<>();
        postMap.put("type", "refund");
        postMap.put("security_key", securityKey);
        postMap.put("amount", form.format(paymentOrderEntity.getOrderAmount()));
        postMap.put("transactionid", paymentOrderOri.getUpstreamOrderNo());

        log.info(" refund request providerName={},url={}, postMap={}", paymentProviderEntity.getProviderName(), postUrl, JSON.toJSONString(postMap));

        Map<String, String> respMap = httpClientService.postWithPPRO(postUrl, postMap, null,null);

        log.info(" refund response privatename={} refund url={}, postMap={}, respMap={}", paymentProviderEntity.getProviderName(), postUrl, postMap, respMap);

        if (respMap == null) return;
        PaymentRequest orderRequest = new PaymentRequest();
        orderRequest.setReference(paymentOrderEntity.getDownstreamOrderNo());



        String status = respMap.get("response").equals("1") ? "SUCCEEDED" : "";
        //修改上游记录表
        paymentOrderUpstreamService.addPaymentOrderUpstream(orderRequest, paymentOrderEntity.getPlatformOrderNo(), postMap, postUrl, null, respMap.get("transactionid"), status, JSON.toJSONString(respMap), null, null, "SUCCEEDED");

        //修改订单表
        paymentOrderEntity.setUpstreamOrderNo(respMap.get("transactionid"));
        paymentOrderEntity.setUpstreamStatus(respMap.get("response"));

        //失败时，结束订单
        if (!respMap.get("response").equals("1")) {
            paymentOrderEntity.setOrderStatus(PaymentStatusEnum.交易失败.getStatus());
            paymentOrderEntity.setFailReason(respMap.get("response_code"));
            paymentOrderEntity.setErrorMsg(respMap.get("responsetext"));
            paymentOrderEntity.setOrderCompleteTime(new Date());
            if (paymentOrderEntity.getFreezeType().equals(1)) {
                fundsHandleService.refundBack(paymentOrderEntity);
                paymentOrderEntity.setFreezeType(2);
            }
        } else {
            paymentOrderEntity.setOrderStatus(PaymentStatusEnum.交易成功.getStatus());
            paymentOrderEntity.setUpstreamStatus(respMap.get("response"));
            paymentOrderEntity.setOrderCompleteTime(new Date());
        }
        paymentOrderSercice.update(paymentOrderEntity);

        PaymentOrderDownstreamEntity paymentOrderDownstreamEntity = paymentOrderDownstreamService.getPaymentOrderDownstreamByOrderNo(paymentOrderEntity.getPlatformOrderNo());
        paymentOrderDownstreamEntity.setUpstreamOrderNo(respMap.get("transactionid"));
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
        String sign = SingUtils.getSign(payinMap, merchantEntity.getPayinSecurityKey());
        refundNotifyMessage.setSign(sign);
        refundNotifyMessage.setWebhookUrl(paymentOrderEntity.getDownstreamNotifyUrl());
        refundNotifyMessage.setProviderReference(paymentOrderEntity.getUpstreamOrderNo());
        updateBill(paymentOrderEntity);

        //发送MQ消息，异步通知下游商户退款状态
        sendMQService.refundNotify(JSON.toJSONString(refundNotifyMessage));

    }

    private void updateBill(PaymentOrderEntity paymentOrderEntity) throws Exception {
        PaymentBillingEntity paymentBillingEntity = new PaymentBillingEntity();
        BeanUtils.copyProperties(paymentOrderEntity, paymentBillingEntity);
        boolean b = paymentBillingService.add(paymentBillingEntity);
        if (!b) {
            log.info("账单表 订单号={} 插入失败 Data= {}", paymentOrderEntity.getPlatformOrderNo(), JSON.toJSONString(paymentBillingEntity));
        }
    }


}
