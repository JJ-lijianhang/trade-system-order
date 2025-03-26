package com.futurebank.order.service.impl.nmi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.futurebank.cache.RedisCache;
import com.futurebank.pojo.enums.PaymentStatusEnum;
import com.futurebank.pojo.utils.BigDecimalUtils;
import com.futurebank.pojo.utils.SingUtils;
import com.futurebank.pojo.vo.notify.NotificationMessage;
import com.futurebank.pojo.vo.notify.NotificationRequestItem;
import com.futurebank.pojo.vo.payment.Amount;
import com.futurebank.rocketmq.NotifyEvent;
import com.futurebank.rocketmq.RocketMQProducer;
import com.futurebank.order.entity.MerchantEntity;
import com.futurebank.order.entity.PaymentBillingEntity;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.entity.PaymentProviderEntity;
import com.futurebank.order.service.*;
import com.futurebank.order.service.payin.PayinRefundQueryService;
import com.futurebank.order.utils.DateUtils;
import com.futurebank.order.utils.FuturebankUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("refund-query-nmi")
class RefundQueryNmiServiceImpl implements PayinRefundQueryService {
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

//    @Autowired
//    SendMQService sendMQService;

    @Autowired
    private RocketMQProducer rocketMQProducer;

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

    @Override
    public void refundQuery(PaymentOrderEntity paymentOrderEntity, PaymentProviderEntity paymentProviderEntity) throws Exception {
        MerchantEntity merchantEntity = merchantService.getMerchant(paymentOrderEntity.getMerchantId() + "");

        if (merchantEntity == null)
            log.error("kgp 未找到商户信息 {}", paymentOrderEntity.getDownstreamOrderNo());

        log.info("paymentOrderEntity={}", paymentOrderEntity);


        String payinConfig = paymentProviderEntity.getPayinConfig();

        if (StringUtils.isBlank(payinConfig))
            log.error("kgp 未找到相关配置 {}", paymentOrderEntity.getDownstreamOrderNo());

        String url = FuturebankUtil.getConfigValue(payinConfig, "refundQuery.url");
        String PartnerID = FuturebankUtil.getConfigValue(payinConfig, "PartnerID");
        String ProjectID = FuturebankUtil.getConfigValue(payinConfig, "ProjectID");
        String ProjectKey = FuturebankUtil.getConfigValue(payinConfig, "ProjectKey");
        String requestId = idGeneratorService.snowFlake(1L) + "";
        if (StringUtils.isBlank(url) ||
                StringUtils.isBlank(PartnerID) ||
                StringUtils.isBlank(ProjectID) ||
                StringUtils.isBlank(ProjectKey))
            log.error("kgp url|PartnerID|PartnerID|PartnerID 配置异常 {}", paymentOrderEntity.getDownstreamOrderNo());
        if (StringUtils.isBlank(requestId))
            log.error("kgp requestId 生成异常 {}", paymentOrderEntity.getDownstreamOrderNo());


        String token = token = getToken(paymentProviderEntity);

        if (StringUtils.isBlank(token)) {
            log.error("kgp token 生成异常 {}", paymentOrderEntity.getDownstreamOrderNo());
        }

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("content-type", "application/json");
        headerMap.put("Authorization", "Bearer " + token);
        headerMap.put("RequestID", requestId);
        headerMap.put("PartnerID", PartnerID);
        headerMap.put("ProjectID", ProjectID);
        headerMap.put("ProjectKey", ProjectKey);
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("partnerRefundID", paymentOrderEntity.getUpstreamOrderNo());

        String requestStr = JSON.toJSONString(postMap);

        log.info("上游请求={}, url={},  token={} data={}", "kgp", url, JSON.toJSONString(headerMap), JSON.toJSONString(postMap));

        String resp = httpClientService.klashaJson(url, requestStr, headerMap, "kgp");
        log.info("kgp refund response  refund url={}, postMap={}, response={}", url, requestStr, resp);

        if (StringUtils.isBlank(resp))
            log.error("kgp 上游响应异常 {}", paymentOrderEntity.getDownstreamOrderNo());

        String status = FuturebankUtil.getConfigValue(resp, "refundStatus");
        if (StringUtils.isBlank(status))
            return;

        //当为返回pending时，重复调用该接口
        if (status.equals("PENDING") || status.equals("PROCESSING")) {
            paymentOrderEntity.setRequestStatus(0);
            paymentOrderService.update(paymentOrderEntity);
            return;
        }


        String message = status;
        String error = status;

        paymentOrderEntity.setUpstreamStatus(status);

        //失败时，结束订单
        if (status.equals("FAILED")) {
            paymentOrderEntity.setOrderStatus(PaymentStatusEnum.交易失败.getStatus());
            paymentOrderEntity.setFailReason(message);
            paymentOrderEntity.setErrorMsg(error);
            paymentOrderEntity.setOrderCompleteTime(new Date());
            if (paymentOrderEntity.getFreezeType().equals(1)) {
                fundsHandleService.refundBack(paymentOrderEntity);
                paymentOrderEntity.setFreezeType(2);
            }
        } else if (status.equals("SUCCEEDED")) {
            paymentOrderEntity.setOrderStatus(PaymentStatusEnum.交易成功.getStatus());
            paymentOrderEntity.setUpstreamStatus(status);
            paymentOrderEntity.setOrderCompleteTime(new Date());
        } else {
            paymentOrderEntity.setUpstreamStatus(status);
        }
        paymentOrderSercice.update(paymentOrderEntity);


        NotificationMessage refundNotifyMessage = new NotificationMessage();
        List<NotificationRequestItem> notificationRequestItems = new ArrayList<>();
        NotificationRequestItem notificationRequestItem = new NotificationRequestItem();


        refundNotifyMessage.setAppId(paymentOrderEntity.getProductId() + "");
        refundNotifyMessage.setMerchantId(paymentOrderEntity.getMerchantId() + "");
        notificationRequestItem.setOriginalReference(paymentOrderEntity.getReferenceOrderNo());
        notificationRequestItem.setPspReference(paymentOrderEntity.getPlatformOrderNo());
        notificationRequestItem.setMerchantReference(paymentOrderEntity.getDownstreamOrderNo());
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
        updateBill(paymentOrderEntity);
//        sendMQService.refundNotify(JSON.toJSONString(refundNotifyMessage));

        NotifyEvent event = new NotifyEvent();
        event.setData(refundNotifyMessage);
        event.setKey("RefundNotifyTopic");
        rocketMQProducer.sendMessage(event);
    }


    /**
     * 获取token
     *
     * @param paymentProviderEntity
     */
    public String getToken(PaymentProviderEntity paymentProviderEntity) throws Exception {

        String kgpTokenKey = "KGP:Login:Token";
        Object cacheObject = redisCache.getCacheObject(kgpTokenKey);
        String token = cacheObject == null ? null : cacheObject.toString();

        if (StringUtils.isNotBlank(token))
            return token;

        if (paymentProviderEntity == null) {
            log.error("paymentProvider is null");
            return null;
        }
        if (StringUtils.isBlank(paymentProviderEntity.getPayinConfig())) {
            log.error("paymentProvider.payinConfig is null");
            return null;
        }

        String consumerID = FuturebankUtil.getConfigValue(paymentProviderEntity.getPayinConfig(), "consumerID");
        String consumerSecret = FuturebankUtil.getConfigValue(paymentProviderEntity.getPayinConfig(), "consumerSecret");
        String xTestMode = FuturebankUtil.getConfigValue(paymentProviderEntity.getPayinConfig(), "xTestMode");
        String envId = FuturebankUtil.getConfigValue(paymentProviderEntity.getPayinConfig(), "envId");


        String url = FuturebankUtil.getConfigValue(paymentProviderEntity.getPayinConfig(), "oauthToken.url");

        if (StringUtils.isBlank(url)) {
            log.error("paymentProvider.payinConfig.url is null");
            return null;
        }
        if (StringUtils.isBlank(consumerID)) {
            log.error("paymentProvider.consumerID is null");
            return null;
        }
        if (StringUtils.isBlank(consumerSecret)) {
            log.error("paymentProvider.consumerSecret is null");
            return null;
        }


        String cert = com.futurebank.pojo.utils.Base64.encode((consumerID + ":" + consumerSecret).getBytes(), "utf-8").replace("\r", "").replace("\n", "");
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Basic " + cert);

        if (StringUtils.isNotBlank(xTestMode) && xTestMode.equals("true"))
            headerMap.put("x-test-mode", xTestMode);

        if (StringUtils.isNotBlank(envId))
            headerMap.put("env-id", envId);

        headerMap.put("Content-Type", "application/x-www-form-urlencoded");


        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credentials");

        String response = httpClientService.postWithKgpToken(url, params, headerMap, "kgp");
        log.info("kgpTokenService:getToken:response:{}", response);
        if (response == null)
            return null;
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject.get("access_token") != null) {
            token = jsonObject.getString("access_token");
            log.info("kgp token {}", token);
            redisCache.setCacheObject(kgpTokenKey, token);
            return token;
        }
        return null;
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
