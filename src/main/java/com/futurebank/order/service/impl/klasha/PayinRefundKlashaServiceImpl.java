package com.futurebank.order.service.impl.klasha;

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
import com.futurebank.order.service.KlashaTokenService;
import com.futurebank.order.service.MerchantChargeService;
import com.futurebank.order.service.MerchantService;
import com.futurebank.order.service.MerchantWalletService;
import com.futurebank.order.service.PaymentBillService;
import com.futurebank.order.service.PaymentBillingService;
import com.futurebank.order.service.PaymentOrderDownstreamService;
import com.futurebank.order.service.PaymentOrderService;
import com.futurebank.order.service.PaymentOrderUpstreamService;
import com.futurebank.order.service.payin.PayinRefundService;
import com.futurebank.order.service.producer.SendMQService;
import com.futurebank.pojo.base.CodeEunm;
import com.futurebank.pojo.enums.PaymentStatusEnum;
import com.futurebank.pojo.exception.CustomException;
import com.futurebank.pojo.utils.BigDecimalUtils;
import com.futurebank.pojo.utils.DateUtils;
import com.futurebank.pojo.utils.FuturebankUtil;
import com.futurebank.pojo.utils.SingUtils;
import com.futurebank.pojo.vo.klasha.RefundResponse;
import com.futurebank.pojo.vo.notify.NotificationMessage;
import com.futurebank.pojo.vo.notify.NotificationRequestItem;
import com.futurebank.pojo.vo.payment.Amount;
import com.futurebank.pojo.vo.payment.PaymentRequest;
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
import java.util.concurrent.TimeUnit;

import static com.futurebank.pojo.vo.notify.NotificationRequestItem.EVENT_CODE_REFUND;

/**
 * @author ben
 * @date 2024/3/31 23:20
 **/
@Slf4j
@Service("payin-refund-klasha")
class PayinRefundKlashaServiceImpl implements PayinRefundService {
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
    KlashaTokenService klashaTokenService;

    @Autowired
    MerchantChargeService merchantChargeService;

    @Autowired
    MerchantWalletService merchantWalletService;

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

        String payinConfig = paymentProviderEntity.getPayinConfig();

        if (StringUtils.isBlank(payinConfig))
            throw new CustomException(CodeEunm.未配置.getCode(), CodeEunm.未配置.getMsg());
//        long refundNo = idGeneratorService.snowFlake(BusinessIdEnum.退款编号.getCode());
        String postUrl = FuturebankUtil.getConfigValue(payinConfig, "refund.url");
        String xAuthToken = FuturebankUtil.getConfigValue(payinConfig, "securityKey");
        String sharedSecret = FuturebankUtil.getConfigValue(payinConfig, "sharedSecret");
        String initRefundUrl = FuturebankUtil.getConfigValue(payinConfig, "initRefund.url");

        if (postUrl == null)
            throw new CustomException(CodeEunm.未配置.getCode(), CodeEunm.未配置.getMsg());
        if (xAuthToken == null)
            throw new CustomException(CodeEunm.未配置.getCode(), CodeEunm.未配置.getMsg());

        postUrl = postUrl.replace("{txRef}", paymentOrderOri.getPlatformOrderNo());


        Map<String, Object> postMap = new HashMap<>();

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("x-auth-token", xAuthToken);

        String klashaTokenKey = "Klasha:Login:Token";

        String token = redisCache.getCacheObject(klashaTokenKey) == null ? null : redisCache.getCacheObject(klashaTokenKey).toString();

        if (StringUtils.isBlank(token)) {
            token = klashaTokenService.getToken(paymentProviderEntity);
            if (StringUtils.isNotBlank(token))
                throw new CustomException(CodeEunm.获取上游token失败.getCode(), CodeEunm.获取上游token失败.getMsg());
        }
        header.put("Authorization", "Bearer " + token);

        //1 初始化退款
        String initRefundKey = "klasha:refund:init:" + paymentOrderEntity.getPlatformOrderNo();
        if (paymentOrderEntity.getPlatformOrderNo() == null) {
            log.info("klasha refund 订单号为空 : {}", paymentOrderEntity.getPlatformOrderNo());
            throw new CustomException(CodeEunm.其他原因.getCode(), CodeEunm.其他原因.getMsg());
        }
        Object cacheObject = redisCache.getCacheObject(initRefundKey);
        if (cacheObject != null) {
            Map<String, Object> initRefundMap = new HashMap<>();
            String refundType = paymentOrderOri.getOrderAmount().compareTo(paymentOrderEntity.getOrderAmount()) == 0 ? "full" : "partial";
            initRefundMap.put("txRef", paymentOrderOri.getPlatformOrderNo());
            initRefundMap.put("amount", paymentOrderEntity.getOrderAmount());
            initRefundMap.put("refundType", refundType);
            String message = SingUtils.Encrypt3DES(JSON.toJSONString(initRefundMap), sharedSecret.substring(0, 24).getBytes());
            Map<String, Object> encodeMap = new HashMap<>();
            encodeMap.put("message", message);

            String initRefundResponse = httpClientService.postKlasha(initRefundUrl, JSON.toJSONString(encodeMap), header, null);
            log.info("klasha refund initRefundResponse privatename={} refund url={}, header={}, postMap={}, encodeMap={}, response={}", paymentProviderEntity.getProviderName(), initRefundUrl, header, initRefundMap, encodeMap, initRefundResponse);

            redisCache.setCacheObject(initRefundKey, null, 1000 * 3600 * 24 * 7, TimeUnit.SECONDS);
            paymentOrderEntity.setRequestStatus(0);
            paymentOrderService.update(paymentOrderEntity);
            return;
        }

        String response = httpClientService.getKlasha(postUrl, JSON.toJSONString(postMap), header, null);
        log.info("klasha refund response privatename={} refund url={}, postMap={}, response={}", paymentProviderEntity.getProviderName(), postUrl, postMap, response);

        if (StringUtils.isBlank(response))
            throw new CustomException(CodeEunm.远程错误.getCode(), CodeEunm.远程错误.getMsg());

        RefundResponse refundResponse = JSON.parseObject(response, RefundResponse.class);


        PaymentRequest orderRequest = new PaymentRequest();
        orderRequest.setReference(paymentOrderEntity.getDownstreamOrderNo());

        //修改上游记录表
        String status = refundResponse.getData() == null ? "FAILED" : refundResponse.getData().get(0).getStatus();

        //当为返回pending时，重复调用该接口
        if (status.toLowerCase().equals("pending")) {
            paymentOrderEntity.setRequestStatus(0);
            paymentOrderService.update(paymentOrderEntity);
            return;
        }

        String upstreamOrderNo = refundResponse.getData() == null ? null : refundResponse.getData().get(0).getRefundTnxId();
        String message = refundResponse.getMessage() == null ? null : refundResponse.getMessage();
        String error = refundResponse.getError() == null ? null : refundResponse.getError();
        paymentOrderUpstreamService.addPaymentOrderUpstream(orderRequest,
                paymentOrderEntity.getPlatformOrderNo(),
                postMap,
                postUrl,
                header,
                upstreamOrderNo,
                status,
                response,
                null,
                null,
                "successful");

        //修改订单表
        paymentOrderEntity.setUpstreamOrderNo(upstreamOrderNo);
        paymentOrderEntity.setUpstreamStatus(status);

        //失败时，结束订单
        if (status.toLowerCase().equals("failed")) {
            paymentOrderEntity.setOrderStatus(PaymentStatusEnum.交易失败.getStatus());
            paymentOrderEntity.setFailReason(message);
            paymentOrderEntity.setErrorMsg(error);
            paymentOrderEntity.setOrderCompleteTime(new Date());
            if (paymentOrderEntity.getFreezeType().equals(1)) {
                fundsHandleService.refundBack(paymentOrderEntity);
                paymentOrderEntity.setFreezeType(2);
            }
        } else if (status.toLowerCase().equals("successful")) {
            paymentOrderEntity.setOrderStatus(PaymentStatusEnum.交易成功.getStatus());
            paymentOrderEntity.setUpstreamStatus(status);
            paymentOrderEntity.setOrderCompleteTime(new Date());
        } else {
            paymentOrderEntity.setUpstreamStatus(status);
        }
        paymentOrderSercice.update(paymentOrderEntity);

        PaymentOrderDownstreamEntity paymentOrderDownstreamEntity = paymentOrderDownstreamService.getPaymentOrderDownstreamByOrderNo(paymentOrderEntity.getPlatformOrderNo());
        paymentOrderDownstreamEntity.setUpstreamOrderNo(upstreamOrderNo);
        paymentOrderDownstreamService.update(paymentOrderDownstreamEntity);


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
        boolean b = paymentBillingService.add(paymentBillingEntity);
        if (!b) {
            log.info("账单表 订单号={} 插入失败 Data= {}", paymentOrderEntity.getPlatformOrderNo(), JSON.toJSONString(paymentBillingEntity));
        }
    }

}
