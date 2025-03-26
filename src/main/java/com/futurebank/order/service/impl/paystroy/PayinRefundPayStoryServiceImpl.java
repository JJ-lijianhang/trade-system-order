package com.futurebank.order.service.impl.paystroy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.futurebank.order.entity.MerchantEntity;
import com.futurebank.order.entity.MerchantPaymentProviderEntity;
import com.futurebank.order.entity.PaymentBillingEntity;
import com.futurebank.order.entity.PaymentOrderDownstreamEntity;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.entity.PaymentProviderEntity;
import com.futurebank.order.service.FundsHandleService;
import com.futurebank.order.service.IdGeneratorService;
import com.futurebank.order.service.MerchantPaymentProviderService;
import com.futurebank.order.service.MerchantService;
import com.futurebank.order.service.PaymentBillingService;
import com.futurebank.order.service.PaymentOrderDownstreamService;
import com.futurebank.order.service.PaymentOrderService;
import com.futurebank.order.service.PaymentOrderUpstreamService;
import com.futurebank.order.service.RestTemplateFactory;
import com.futurebank.order.service.payin.PayinRefundService;
import com.futurebank.order.utils.DateUtils;
import com.futurebank.order.utils.FuturebankUtil;
import com.futurebank.order.utils.HttpUtils;
import com.futurebank.pojo.base.CodeEunm;
import com.futurebank.pojo.enums.PaymentStatusEnum;
import com.futurebank.pojo.exception.CustomException;
import com.futurebank.pojo.utils.BigDecimalUtils;
import com.futurebank.pojo.utils.SingUtils;
import com.futurebank.pojo.vo.notify.NotificationMessage;
import com.futurebank.pojo.vo.notify.NotificationRequestItem;
import com.futurebank.pojo.vo.payment.Amount;
import com.futurebank.pojo.vo.payment.PaymentRequest;
import com.futurebank.rocketmq.NotifyEvent;
import com.futurebank.rocketmq.RocketMQProducer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Resource;

import static com.futurebank.pojo.vo.notify.NotificationRequestItem.EVENT_CODE_REFUND;

/**
 * @author ben
 * @date 2024/3/31 23:20
 **/
@Slf4j
@Service("payin-refund-PayStory")
class PayinRefundPayStoryServiceImpl implements PayinRefundService {
    @Resource
    PaymentOrderService paymentOrderSercice;

    @Resource
    IdGeneratorService idGeneratorService;
    @Resource
    PaymentOrderUpstreamService paymentOrderUpstreamService;
    @Resource
    PaymentOrderDownstreamService paymentOrderDownstreamService;

//    @Resource
//    SendMQService sendMQService;

    @Autowired
    private RocketMQProducer rocketMQProducer;

    @Resource
    MerchantService merchantService;
    @Resource
    FundsHandleService fundsHandleService;
    @Resource
    PaymentBillingService paymentBillingService;

    @Resource
    private MerchantPaymentProviderService merchantPaymentProviderService;

    @Resource
    private RestTemplateFactory restTemplateFactory;

    @Override
    public void refund(PaymentOrderEntity paymentOrderEntity, PaymentProviderEntity paymentProviderEntity) throws Exception {
        log.info("PayStory refund downstreamOrderNo:{}的入参paymentOrderEntity:{}", paymentOrderEntity.getDownstreamOrderNo(), JSONObject.toJSONString(paymentOrderEntity));

        // 1. 获取原始订单
        PaymentOrderEntity paymentOrderOri = paymentOrderSercice.getPaymentOrderByOrderNo(paymentOrderEntity.getReferenceOrderNo());
        log.info("PayStory refund 原始订单不存在 订单号={},paymentOrderOri：{}", paymentOrderEntity.getDownstreamOrderNo(), paymentOrderOri);

        MerchantPaymentProviderEntity merchantPaymentProviderEntity = merchantPaymentProviderService.getMerchantPaymentProvider(paymentOrderEntity.getMerchantId() , paymentProviderEntity.getProviderName());
        log.info("PayStory refund downstreamOrderNo:{}的merchantPaymentProviderEntity:{}", paymentOrderEntity.getDownstreamOrderNo(), JSONObject.toJSON(merchantPaymentProviderEntity));

        // 2. 获取渠道配置
        Map<String, String> config = getStringStringMap(paymentOrderEntity.getPaymentMethod(), paymentProviderEntity.getPayinConfig(),merchantPaymentProviderEntity);
        log.info("PayStory refund downstreamOrderNo:{}的config:{}", paymentOrderEntity.getDownstreamOrderNo(), JSONObject.toJSONString(config));

        // 3. 构建并发送退款请求
        Map<String, String> params = buildRequestParams(paymentOrderEntity, config, paymentOrderOri.getUpstreamOrderNo(), paymentOrderOri);
        log.info("PayStory refund downstreamOrderNo:{}，请求参数params:{}", paymentOrderEntity.getDownstreamOrderNo(), JSONObject.toJSON(params));

        // 4. 发送支付请求并获取响应（调用简化后的 RestTemplate 方法）
        String resp = getCommonResp(config.get("refundurl"), params);
        log.info("PayStory refund downstreamOrderNo:{}，响应参数resp:{}", paymentOrderEntity.getDownstreamOrderNo(), JSONObject.toJSON(resp));

        // 5. 处理退款响应
        processRefundResponse(paymentOrderEntity, resp, JSONObject.toJSONString(params), config);

    }

    private String getCommonResp(String url,Map<String, String> params) {
        try {
            // 设置请求头，确保 Content-Type 为 application/x-www-form-urlencoded
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // 将请求参数格式化为 key=value&key=value...
            StringBuilder body = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (body.length() > 0) {
                    body.append("&");
                }
                body.append(entry.getKey()).append("=").append(entry.getValue());
            }

            // 使用 HttpEntity 包装请求体和请求头
            HttpEntity<String> httpEntity = new HttpEntity<>(body.toString(), headers);

            // 使用 RestTemplate 发送 POST 请求
            ResponseEntity<String> response = restTemplateFactory.getRestTemplate(null).exchange(url, HttpMethod.POST, httpEntity, String.class);

            int status = response.getStatusCodeValue();
            if ((status >= 200 && status < 300) || status == 302) {
                return response.getBody();
            }
            return null;
        } catch (Exception e) {
            log.error("发送支付请求异常，url: {}，错误信息: {}", url, e.getMessage(), e);
            throw e;
        }
    }

    public Map<String, String> buildRequestParams(PaymentOrderEntity paymentOrderEntity, Map<String, String> config, String upstreamOrderNo, PaymentOrderEntity paymentOrderOri) throws Exception {
        // 生成一个 10 位的随机数
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(secureRandom.nextInt(10));
        }
        String requestId = sb.toString();

        // 获取本地 IP 地址
        InetAddress ipAddress = InetAddress.getLocalHost();
        String requestIp = ipAddress.getHostAddress();

        // 生成请求时间（格式：yyyyMMddHHmmss）
        String ediDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // 生成 SHA-256 加密数据
        Long cancelAmount = Long.valueOf(BigDecimalUtils.multiply(paymentOrderEntity.getOrderAmount(), paymentOrderEntity.getCurrency()));
        String encData = HttpUtils.generateSHA256(config.get("mid") + ediDate + cancelAmount + config.get("merchantKey"));

        // 获取退款原因
        String reason = paymentOrderEntity.getRefundReason();

        // 创建并返回请求参数的 Map
        Map<String, String> params = new HashMap<>();
        params.put("mid", MERCHANT_ID);
        params.put("tid", upstreamOrderNo);
        params.put("canAmt", cancelAmount.toString());
        params.put("canId", requestId);
        params.put("canMsg", reason);
        params.put("canIp", requestIp);
        BigDecimal orderAmount = paymentOrderOri.getOrderAmount();
        // 比较两个金额
        // 将 cancelAmount 转换为 BigDecimal 进行比较
        BigDecimal cancelAmountBigDecimal = new BigDecimal(cancelAmount.toString());
        int comparisonResult = cancelAmountBigDecimal.compareTo(orderAmount);
        params.put("partCanFlg", (comparisonResult != 0) ? "1" : "0");
        params.put("ediDate", ediDate);
        params.put("encData", encData);

        return params;
    }
    /**
     * 发送支付请求并获取响应
     * 仅使用 url、httpEntity 和 useCert 参数，不需要额外请求头
     */
    private String sendPaymentRequest(String url, HttpEntity<?> httpEntity) throws Exception {
        try {
            ResponseEntity<String> response = restTemplateFactory.getRestTemplate(null).postForEntity(url, httpEntity, String.class);
            int status = response.getStatusCodeValue();
            if ((status >= 200 && status < 300) || status == 302) {
                return response.getBody();
            }
            return null;
        } catch (Exception e) {
            log.error("发送支付请求异常，url: {}，错误信息: {}", url, e.getMessage(), e);
            throw e;
        }
    }


    private static final String CANCEL_URL = "https://pg-tst.minglepay.co.kr/payment.cancel";
    private static final String MERCHANT_ID = "ilkupop02m";
    private static final String MERCHANT_KEY = "oA1EjFwXai/HgMTYHkVAF2+SlkCFKAmRaW25n6DG7tdSvVTO69U1BcPt3IDKFr5xVJlOL8GlBoNULxe4+wG+nA==";

    public void cancelPayment(String tid, String cancelAmount, String reason, String requestIp, boolean isPartial) throws Exception {
        // 生成请求时间（格式：yyyyMMddHHmmss）
        String ediDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // 生成 SHA-256 加密数据
        String encData = HttpUtils.generateSHA256(MERCHANT_ID + ediDate + cancelAmount + MERCHANT_KEY);

        // 构建请求参数
        String postData = String.format(
                "mid=%s&tid=%s&canAmt=%s&canId=admin123&canMsg=%s&canIp=%s&partCanFlg=%s&ediDate=%s&encData=%s",
                MERCHANT_ID, tid, cancelAmount, reason, requestIp, isPartial ? "1" : "0", ediDate, encData
        );

        HttpPost request = new HttpPost(CANCEL_URL);
        request.setEntity(new StringEntity(postData, StandardCharsets.UTF_8));
        request.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request);
             BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))) {

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            log.info("PayStory downstreamOrderNo: result：{}", result.toString());
        }
    }

    private void processRefundResponse(PaymentOrderEntity paymentOrderEntity, String resp, String params, Map<String, String> config) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(resp);

        String code = jsonObject.getString("resultCd");
        if (StringUtils.isBlank(code)) {
            throw new CustomException(CodeEunm.远程错误.getCode(), CodeEunm.远程错误.getMsg());
        }
        // 处理退款完成后的相关操作
        processRefundCompletion(paymentOrderEntity, resp, params, config, code);

        // 发送MQ退款通知
        sendMQRefundNotify(paymentOrderEntity);
    }

    private void processRefundCompletion(PaymentOrderEntity paymentOrderEntity, String resp, String params, Map<String, String> config, String code) throws Exception {
        // 退款状态
        String status = determineStatus(code);
        log.info("PayStory refund downstreamOrderNo:{}，status:{}", paymentOrderEntity.getDownstreamOrderNo(), status);
        // 退款订单号
        String refundId = FuturebankUtil.getConfigValue(resp, "tid");

        Map<String, Object> map  = new HashMap<>();
        map.put("req", params);

        // 更新订单信息
        updateOrder(paymentOrderEntity, refundId, code, status, resp);

        // 添加商户订单上游信息
        addOrderUpstream(paymentOrderEntity, map, config, refundId, status, resp);

        // 更新商户订单下游信息
        updateOrderDownstream(paymentOrderEntity, refundId);

        // 添加支付账单表
        addBill(paymentOrderEntity);
    }

    private void sendMQRefundNotify(PaymentOrderEntity paymentOrderEntity) throws Exception {
        MerchantEntity merchantEntity = merchantService.getMerchant(paymentOrderEntity.getMerchantId() + "");
        if (merchantEntity == null) {
            log.error("PayStory 未找到商户信息 {}", paymentOrderEntity.getDownstreamOrderNo());
        }

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
        String signs = SingUtils.getSign(payinMap, merchantEntity.getPayinSecurityKey());
        refundNotifyMessage.setSign(signs);
        refundNotifyMessage.setWebhookUrl(paymentOrderEntity.getDownstreamNotifyUrl());

        //发送MQ消息，异步通知下游商户退款状态
//        sendMQService.refundNotify(JSON.toJSONString(refundNotifyMessage));

        NotifyEvent event = new NotifyEvent();
        event.setData(refundNotifyMessage);
        event.setKey("RefundNotifyTopic");
        rocketMQProducer.sendMessage(event);
    }

    private void addOrderUpstream(PaymentOrderEntity paymentOrderEntity, Map<String, Object> params, Map<String, String> config, String refundId, String status, String resp) {
        PaymentRequest orderRequest = new PaymentRequest();
        orderRequest.setReference(paymentOrderEntity.getDownstreamOrderNo());
        paymentOrderUpstreamService.addPaymentOrderUpstream(orderRequest, paymentOrderEntity.getPlatformOrderNo(), params, config.get("refundurl"), null, refundId, status, resp, null, null, "SUCCEEDED");
    }

    private void updateOrderDownstream(PaymentOrderEntity paymentOrderEntity, String refundId) {
        PaymentOrderDownstreamEntity paymentOrderDownstreamEntity = paymentOrderDownstreamService.getPaymentOrderDownstreamByOrderNo(paymentOrderEntity.getPlatformOrderNo());
        paymentOrderDownstreamEntity.setUpstreamOrderNo(refundId);
        paymentOrderDownstreamService.update(paymentOrderDownstreamEntity);
    }

    private void updateOrder(PaymentOrderEntity paymentOrderEntity, String refundId, String code, String status, String resp) {
        //修改订单表
        paymentOrderEntity.setUpstreamOrderNo(refundId);

        paymentOrderEntity.setUpstreamStatus(code);

        //失败时，结束订单
        if (PaymentStatusEnum.交易失败.getStatus().equals(status)) {
            paymentOrderEntity.setOrderStatus(PaymentStatusEnum.交易失败.getStatus());
            paymentOrderEntity.setFailReason(code);
            paymentOrderEntity.setErrorMsg(FuturebankUtil.getConfigValue(resp, "msg"));
            paymentOrderEntity.setOrderCompleteTime(new Date());
            if (paymentOrderEntity.getFreezeType().equals(1)) {
                fundsHandleService.refundBack(paymentOrderEntity);
                paymentOrderEntity.setFreezeType(2);
            }
        } else {
            paymentOrderEntity.setOrderStatus(status);
            paymentOrderEntity.setUpstreamStatus(code);
            paymentOrderEntity.setOrderCompleteTime(new Date());
        }
        paymentOrderSercice.update(paymentOrderEntity);
    }

    private String determineStatus(String code) {
        if ("2001".equals(code)) {
            return PaymentStatusEnum.交易成功.getStatus();
        }else {
            return PaymentStatusEnum.退款失败.getStatus();
        }
    }

    private static String sendPaymentRequest(String downstreamOrderNo, String refundurl, String params) throws Exception {
        log.info("PayStory downstreamOrderNo:{}的refundUrl:{}", downstreamOrderNo, refundurl);
        log.info("PayStory downstreamOrderNo:{}的入参params:{}", downstreamOrderNo, params);
        HttpPost request = new HttpPost(refundurl);
        request.setEntity(new StringEntity(params, StandardCharsets.UTF_8));
        request.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        StringBuilder result = new StringBuilder();
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request);
             BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))) {


            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            log.info("PayStory downstreamOrderNo: result：{}", result.toString());
        }
        // 5. 发送退款请求
        //log.info("PayStory downstreamOrderNo:{}的返参respMap:{}", downstreamOrderNo, JSONObject.parseObject(resp));
        return result.toString();
    }

    private void addBill(PaymentOrderEntity paymentOrderEntity) throws Exception {
        PaymentBillingEntity paymentBillingEntity = new PaymentBillingEntity();
        BeanUtils.copyProperties(paymentOrderEntity, paymentBillingEntity);
        boolean b = paymentBillingService.add(paymentBillingEntity);
        if (!b) {
            log.info("账单表 订单号={} 插入失败 Data= {}", paymentOrderEntity.getPlatformOrderNo(), JSON.toJSONString(paymentBillingEntity));
        }
    }

    /**
     * 构建请求参数
     */
    /*private String buildRequestParams(PaymentOrderEntity paymentOrderEntity, Map<String, String> config, String upstreamOrderNo) throws Exception {
        //String requestId = idGeneratorService.snowFlake(1L) + "";
        // 生成一个 10 位的随机数
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(secureRandom.nextInt(10));
        }
        String requestId = sb.toString();
        InetAddress ipAddress = InetAddress.getLocalHost();
        // 生成请求时间（格式：yyyyMMddHHmmss）
        String ediDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // 生成 SHA-256 加密数据
        Long cancelAmount = Long.valueOf(BigDecimalUtils.multiply(paymentOrderEntity.getOrderAmount(), paymentOrderEntity.getCurrency()));
        String encData = HttpUtils.generateSHA256(config.get("mid") + ediDate + cancelAmount + config.get("merchantKey"));
        String reason = paymentOrderEntity.getRefundReason();
        String requestIp = ipAddress.getHostAddress();
        // 构建请求参数
        return String.format(
                "mid=%s&tid=%s&canAmt=%s&canId=%s&canMsg=%s&canIp=%s&partCanFlg=%s&ediDate=%s&encData=%s",
                MERCHANT_ID, upstreamOrderNo, cancelAmount, requestId, reason, requestIp, false ? "1" : "0", ediDate, encData
        );
    }
*/

    /*public static void main(String[] args) throws Exception {

     *//*Map<String, Object> params = new HashMap<>();
        params.put("mchOrderNo", "1889181490458300416"); // 原支付订单号
        params.put("refundReason", "1");
        params.put("reqTime", String.valueOf(System.currentTimeMillis())); // 13位时间戳
        String sign = SignatureTool.easyLinkSign(params, "OLk1p37oSAKGT5tgCfb9q9j56q7FKDF2I0AwMNm3IG0p7oLdkIh4ol83WdC2v4S5416AQVQnNw4SbCgVIvPWp3kL78LSUP5MSkJy7O1DALQuvEI463VjHTY3SCDrcjx1");
        params.put("version", "1.0");
        params.put("appId", "665d4c144fcb85bf6bca9b5f");
        params.put("mchRefundNo", "11111"); // 商户退款单号
        params.put("clientIp", "192.166.1.132"); // 商户退款单号
        params.put("notifyUrl", "https://api.futurepay-develop.com/payin/notify/Easylink"); // 商户退款单号
        params.put("signType", "MD5");
        params.put("refundAmount", 1); // 单位:分
        params.put("currency", "HKD");
        params.put("mchNo", "M1717387055");
        params.put("sign", sign);

        String sign = SignatureTool.easyLinkSign(params, "OLk1p37oSAKGT5tgCfb9q9j56q7FKDF2I0AwMNm3IG0p7oLdkIh4ol83WdC2v4S5416AQVQnNw4SbCgVIvPWp3kL78LSUP5MSkJy7O1DALQuvEI463VjHTY3SCDrcjx1");
*//*




        Map<String, Object> params = new TreeMap<>();
        params.put("mchNo", "M1717387055");
        params.put("appId", "665d4c144fcb85bf6bca9b5f");
        params.put("mchOrderNo", "FCDBD55BAB6646E39090F0CEA4B49C57");
        params.put("refundAmount", 20); // 单位:分
        params.put("currency", "HKD");
        params.put("notifyUrl", "https://api.futurepay-develop.com/payin/notify/Easylink"); // 商户退款单号
        params.put("reqTime", String.valueOf(System.currentTimeMillis()));
        params.put("version", "1.0");
        params.put("signType", "MD5");
        //params.put("wayCode", "WX_H5");
        params.put("refundReason", "1");
        params.put("mchRefundNo", "1889578885021040642"); // 商户退款单号
        // 生成签名
        String sign = SignatureTool.easyLinkSign(params, "OLk1p37oSAKGT5tgCfb9q9j56q7FKDF2I0AwMNm3IG0p7oLdkIh4ol83WdC2v4S5416AQVQnNw4SbCgVIvPWp3kL78LSUP5MSkJy7O1DALQuvEI463VjHTY3SCDrcjx1");
        params.put("sign", sign);
        String resp = sendPaymentRequest("1889181490458300416", "https://ts-api-pay.gnete.com.hk/api/refund/refundOrder", params);
    }*/


    private static Map<String, String> getStringStringMap(String paymentMethod, String payinConfig, MerchantPaymentProviderEntity merchantPaymentProviderEntity) {
        if (StringUtils.isEmpty(payinConfig)) {
            throw new CustomException(CodeEunm.未配置.getCode(), "渠道配置" + CodeEunm.未配置.getMsg());
        }
        String accExt = merchantPaymentProviderEntity.getAccExt();
        JSONObject jsonObject = JSONObject.parseObject(accExt);
        String pro = FuturebankUtil.getConfigValue(payinConfig, "pro");
        String mid = FuturebankUtil.getConfigValue(payinConfig, paymentMethod + ".mid");
        String merchantKey = FuturebankUtil.getConfigValue(payinConfig, paymentMethod + ".merchantKey");
        if ("true".equals(pro)) {
            mid = jsonObject.getString("mid");
            merchantKey = jsonObject.getString("merchantKey");
        }
        Map<String, String> config = new HashMap<>();
        config.put("mid", mid);
        config.put("merchantKey", merchantKey);
        config.put("refundurl", FuturebankUtil.getConfigValue(payinConfig, "common.refundurl"));
        StringBuilder missingConfigs = new StringBuilder();
        for (Map.Entry<String, String> entry : config.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty()) {
                if (missingConfigs.length() > 0) {
                    missingConfigs.append(", ");
                }
                missingConfigs.append(entry.getKey());
            }
        }
        // 如果存在缺失的配置项，抛出异常
        if (missingConfigs.length() > 0) {
            throw new CustomException(CodeEunm.未配置.getCode(), "配置缺失: " + missingConfigs);
        }
        return config;
    }

}
