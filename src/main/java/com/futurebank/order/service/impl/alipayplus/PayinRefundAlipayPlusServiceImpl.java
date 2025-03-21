package com.futurebank.order.service.impl.alipayplus;

import com.alibaba.fastjson.JSON;
import com.futurebank.pojo.base.CodeEunm;
import com.futurebank.pojo.enums.PaymentStatusEnum;
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
import com.futurebank.order.enums.ResultStatusType;
import com.futurebank.order.service.*;
import com.futurebank.order.service.payin.PayinRefundService;
import com.futurebank.order.service.producer.SendMQService;
import com.futurebank.order.utils.GsonUtils;
import com.futurebank.order.utils.SignatureTool;
import com.futurebank.order.vo.alipayplus.RefundResponse;
import com.futurebank.order.vo.alipayplus.RequestHeader;
import com.futurebank.order.vo.alipayplus.constants.URIConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.futurebank.pojo.vo.notify.NotificationRequestItem.EVENT_CODE_REFUND;

/**
 * @author ben
 * @date 2024/3/31 23:20
 **/
@Slf4j
@Service("payin-refund-AlipayPlus")
class PayinRefundAlipayPlusServiceImpl implements PayinRefundService {
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
    PaymentBillingService paymentBillingService;

    @Override
    public void refund(PaymentOrderEntity paymentOrderEntity, PaymentProviderEntity paymentProviderEntity) throws Exception {
        MerchantEntity merchantEntity = merchantService.getMerchant(paymentOrderEntity.getMerchantId() + "");

        if (merchantEntity == null)
            log.error("AlipayPlus 未找到商户信息 {}", paymentOrderEntity.getDownstreamOrderNo());


        log.info("paymentOrderEntity={}", paymentOrderEntity);
        //获取原始订单
        PaymentOrderEntity paymentOrderOri = paymentOrderSercice.getPaymentOrderByOrderNo(paymentOrderEntity.getReferenceOrderNo());
        if (paymentOrderOri == null) log.info("原始订单不存在 订单号={}", paymentOrderEntity.getDownstreamOrderNo());

        String payinConfig = paymentProviderEntity.getPayinConfig();

        if (StringUtils.isBlank(payinConfig))
            log.error("AlipayPlus 未找到相关配置 {}", paymentOrderEntity.getDownstreamOrderNo());

        String postUrl = FuturebankUtil.getConfigValue(payinConfig, "refund.url");
        if (StringUtils.isBlank(postUrl)) {
            throw new CustomException(CodeEunm.未配置.getCode(), CodeEunm.未配置.getMsg());
        }
        String publicKey = FuturebankUtil.getConfigValue(payinConfig, "publicKey");
        if (StringUtils.isBlank(publicKey)) {
            throw new CustomException(CodeEunm.未配置.getCode(), CodeEunm.未配置.getMsg());
        }
        String clientId = FuturebankUtil.getConfigValue(payinConfig, "clientId");
        if (StringUtils.isBlank(clientId)) {
            throw new CustomException(CodeEunm.未配置.getCode(), CodeEunm.未配置.getMsg());
        }
        String privateKey = FuturebankUtil.getConfigValue(payinConfig, "privateKey");
        if (StringUtils.isBlank(privateKey)) {
            throw new CustomException(CodeEunm.未配置.getCode(), CodeEunm.未配置.getMsg());
        }
        String requestId = idGeneratorService.snowFlake(1L) + "";
        if (StringUtils.isBlank(requestId))
            log.error("AlipayPlus requestId 生成异常 {}", paymentOrderEntity.getDownstreamOrderNo());


        //设置AlipayPlus退款请求参数
        Amount refundAmount = new Amount();
        refundAmount.setCurrency(paymentOrderEntity.getCurrency());
        refundAmount.setValue(Long.valueOf(BigDecimalUtils.multiply(paymentOrderEntity.getOrderAmount(), paymentOrderEntity.getCurrency())));
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("refundRequestId", requestId);
        postMap.put("paymentId", paymentOrderOri.getUpstreamOrderNo());
        postMap.put("refundAmount", refundAmount);
        //设置退款请求头
        HttpHeaders headers = new HttpHeaders();
        this.composeHttpRequestHeader(postMap, headers, clientId, privateKey);


        log.info(" refund request providerName={},url={}, postMap={}", paymentProviderEntity.getProviderName(), postUrl, JSON.toJSONString(postMap));

        String resp = httpClientService.alipayPlusHttpPost(postUrl, GsonUtils.toJSONString(postMap), headers, null, clientId, HttpMethod.POST.name(), URIConstants.REFUND, publicKey);

        log.info(" refund response privatename={} refund url={}, postMap={}, respMap={}", paymentProviderEntity.getProviderName(), postUrl, postMap, resp);

        if (resp == null) return;
        PaymentRequest orderRequest = new PaymentRequest();
        orderRequest.setReference(paymentOrderEntity.getDownstreamOrderNo());

        RefundResponse apsResponse = GsonUtils.toJavaObject(resp, RefundResponse.class);
        ResultStatusType resultStatus = apsResponse.getResult().getResultStatus();
        if (resultStatus == null) {
            log.error(
                    "[ALIPAYPLUS_LOG][INVALID_RESULT_STATUS] result.resultStatus is unknown , resultStatus={}",
                    resultStatus);
            throw new CustomException(CodeEunm.远程错误.getCode(), CodeEunm.远程错误.getMsg());
        }

        //如调用Alipay+退款接口返回未知状态，则重试5次，每次间隔2秒，重试5次后抛出异常
        int maxRetries = 5;
        long retryIntervalMillis = 2000;
        int retryCount = 0;
        if (resultStatus == ResultStatusType.U) {
            while (retryCount < maxRetries) {
                retryCount++;
                log.info("[ALIPAYPLUS_LOG][REFUND_RETRY] Retrying refund status check... Attempt {}/{}", retryCount, maxRetries);
                Thread.sleep(retryIntervalMillis);
                log.info(" refund request providerName={},url={}, postMap={}", paymentProviderEntity.getProviderName(), postUrl, JSON.toJSONString(postMap));
                resp = httpClientService.alipayPlusHttpPost(postUrl, GsonUtils.toJSONString(postMap), headers, null, clientId, HttpMethod.POST.name(), URIConstants.REFUND, publicKey);
                log.info(" refund response privatename={} refund url={}, postMap={}, respMap={}", paymentProviderEntity.getProviderName(), postUrl, postMap, resp);
                if (resp == null) {
                    log.error("Retry refund inquiry failed, response is null. Attempt {}/{}", retryCount, maxRetries);
                    continue;
                }
                RefundResponse retryResponse = GsonUtils.toJavaObject(resp, RefundResponse.class);
                ResultStatusType retryResultStatus = retryResponse.getResult().getResultStatus();

                if (retryResultStatus != ResultStatusType.U) {
                    resultStatus = retryResultStatus;
                    apsResponse = retryResponse;
                    break;
                }
            }
        }

        if (resultStatus == ResultStatusType.U) {
            log.error("[ALIPAYPLUS_LOG][REFUND_RETRY_FAILED] Refund status unresolved after {} retries.", maxRetries);
            throw new CustomException(CodeEunm.远程错误.getCode(), CodeEunm.远程错误.getMsg());
        }


        String refundId = apsResponse.getRefundId();
        String status = resultStatus == ResultStatusType.S ? "SUCCEEDED" : "";
        //修改上游记录表
        paymentOrderUpstreamService.addPaymentOrderUpstream(orderRequest, paymentOrderEntity.getPlatformOrderNo(), postMap, postUrl, null, refundId, status, resp, null, null, "SUCCEEDED");

        //修改订单表
        paymentOrderEntity.setUpstreamOrderNo(refundId);
        paymentOrderEntity.setUpstreamStatus(apsResponse.getResult().getResultCode());

        //失败时，结束订单
        if (resultStatus != ResultStatusType.S) {
            paymentOrderEntity.setOrderStatus(PaymentStatusEnum.交易失败.getStatus());
            paymentOrderEntity.setFailReason(apsResponse.getResult().getResultCode());
            paymentOrderEntity.setErrorMsg(apsResponse.getResult().getResultMessage());
            paymentOrderEntity.setOrderCompleteTime(new Date());
            if (paymentOrderEntity.getFreezeType().equals(1)) {
                fundsHandleService.refundBack(paymentOrderEntity);
                paymentOrderEntity.setFreezeType(2);
            }
        } else {
            paymentOrderEntity.setOrderStatus(PaymentStatusEnum.交易成功.getStatus());
            paymentOrderEntity.setUpstreamStatus(apsResponse.getResult().getResultCode());
            paymentOrderEntity.setOrderCompleteTime(new Date());
        }
        paymentOrderSercice.update(paymentOrderEntity);

        PaymentOrderDownstreamEntity paymentOrderDownstreamEntity = paymentOrderDownstreamService.getPaymentOrderDownstreamByOrderNo(paymentOrderEntity.getPlatformOrderNo());
        paymentOrderDownstreamEntity.setUpstreamOrderNo(refundId);
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

    private void composeHttpRequestHeader(Map<String, Object> postMap, HttpHeaders headers, String clientId, String privateKey) {
        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setClientId(clientId);
        String signature;
        try {
            signature = SignatureTool.sign(HttpMethod.POST.name(), URIConstants.REFUND,
                    clientId, requestHeader.getRequestTime(),
                    GsonUtils.toJSONString(postMap), privateKey);
        } catch (Exception e) {
            log.error(String.format(
                    "SignException: [httpMethod=[%s]], requestURI=[%s], clientId=[%s], requestTime=[%s], requestJson=[%s], privateKey=[%s]",
                    HttpMethod.POST, URIConstants.REFUND,
                    clientId, requestHeader.getRequestTime(),
                    GsonUtils.toJSONString(postMap), privateKey), e);

            throw new CustomException(CodeEunm.请求头错误.getCode(), CodeEunm.请求头错误.getMsg());
        }
        requestHeader.getSignature().setSignature(signature);
        //Step1.3: Set header info into httpPost
        for (Map.Entry<String, String> e : requestHeader.toMap().entrySet()) {
            headers.set(e.getKey(), e.getValue());
        }
    }


}
