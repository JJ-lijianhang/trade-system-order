package com.futurebank.order.service.impl.easyline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import com.futurebank.order.entity.*;
import com.futurebank.order.service.*;
import com.futurebank.order.service.payin.PayinRefundService;
import com.futurebank.order.utils.DateUtils;
import com.futurebank.order.utils.FuturebankUtil;
import com.futurebank.order.utils.SignatureTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.futurebank.pojo.vo.notify.NotificationRequestItem.EVENT_CODE_REFUND;

/**
 * @author ben
 * @date 2024/3/31 23:20
 **/
@Slf4j
@Service("payin-refund-Easylink")
class PayinRefundEasyLineServiceImpl implements PayinRefundService {
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
        log.info("Easylink downstreamOrderNo:{}的入参paymentOrderEntity:{}", paymentOrderEntity.getDownstreamOrderNo(), JSONObject.toJSONString(paymentOrderEntity));

        // 1. 获取原始订单
        PaymentOrderEntity paymentOrderOri = paymentOrderSercice.getPaymentOrderByOrderNo(paymentOrderEntity.getReferenceOrderNo());
        if (paymentOrderOri == null){
            log.info("Easylink 原始订单不存在 订单号={}", paymentOrderEntity.getDownstreamOrderNo());
        }

        MerchantPaymentProviderEntity merchantPaymentProviderEntity = merchantPaymentProviderService.getMerchantPaymentProvider(paymentOrderEntity.getMerchantId() , paymentProviderEntity.getProviderName());
        log.info("PayStory downstreamOrderNo:{}的merchantPaymentProviderEntity:{}", paymentOrderEntity.getDownstreamOrderNo(), JSONObject.toJSON(merchantPaymentProviderEntity));

        // 2. 获取渠道配置
        Map<String, String> config = getStringStringMap(paymentOrderEntity.getPaymentMethod(), paymentProviderEntity.getPayinConfig(), merchantPaymentProviderEntity);
        log.info("Easylink downstreamOrderNo:{}的config:{}", paymentOrderEntity.getDownstreamOrderNo(), JSONObject.toJSONString(config));

        // 3. 构建并发送退款请求
        Map<String, Object> params = buildRequestParams(paymentOrderEntity, config, paymentOrderOri.getPlatformOrderNo());
        log.info("Easylink downstreamOrderNo:{}，入参参数params:{}", paymentOrderEntity.getDownstreamOrderNo(), JSONObject.toJSONString(params));

        // 4. 发送支付请求并获取响应（调用简化后的 RestTemplate 方法）
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params);
        String resp = sendPaymentRequest(config.get("refundurl").toString(), httpEntity);
        log.info("Easylink downstreamOrderNo:{}，响应参数resp:{}", paymentOrderEntity.getDownstreamOrderNo(), JSONObject.toJSONString(resp));

        // 5. 处理退款响应
        processRefundResponse(paymentOrderEntity, resp, params, config);

    }

    private void processRefundResponse(PaymentOrderEntity paymentOrderEntity, String resp, Map<String, Object> params, Map<String, String> config) throws Exception {
        String code = FuturebankUtil.getConfigValue(resp, "code");
        if (StringUtils.isBlank(code)) {
            throw new CustomException(CodeEunm.远程错误.getCode(), CodeEunm.远程错误.getMsg());
        }
        // 处理退款完成后的相关操作
        processRefundCompletion(paymentOrderEntity, resp, params, config, code);

        // 发送MQ退款通知
        sendMQRefundNotify(paymentOrderEntity);
    }

    private void processRefundCompletion(PaymentOrderEntity paymentOrderEntity, String resp, Map<String, Object> params, Map<String, String> config, String code) throws Exception {
        // 退款状态
        String status = determineStatus(code, resp);

        // 退款订单号
        String refundId = FuturebankUtil.getConfigValue(resp, "data.channelOrderNo");

        // 更新订单信息
        updateOrder(paymentOrderEntity, refundId, code, status, resp);

        // 添加商户订单上游信息
        addOrderUpstream(paymentOrderEntity, params, config, refundId, status, resp);

        // 更新商户订单下游信息
        updateOrderDownstream(paymentOrderEntity, refundId);

        // 添加支付账单表
        addBill(paymentOrderEntity);
    }

    private void sendMQRefundNotify(PaymentOrderEntity paymentOrderEntity) throws Exception {
        MerchantEntity merchantEntity = merchantService.getMerchant(paymentOrderEntity.getMerchantId() + "");
        if (merchantEntity == null) {
            log.error("Easylink 未找到商户信息 {}", paymentOrderEntity.getDownstreamOrderNo());
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

    private String determineStatus(String code, String resp) {
        String state = FuturebankUtil.getConfigValue(resp, "data.state");
        if ("9999".equals(code)) {
            return "FAILED";
        }
        if ("0".equals(code)) {
            switch (state) {
                case "1":
                    return PaymentStatusEnum.交易处理中.getStatus();
                case "2":
                    return PaymentStatusEnum.交易成功.getStatus();
                default:
                    return PaymentStatusEnum.交易失败.getStatus();
            }
        }
        return "FAILED";
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
    private Map<String, Object> buildRequestParams(PaymentOrderEntity paymentOrderEntity, Map<String, String> config, String downstreamOrderNo) throws Exception {
        String requestId = idGeneratorService.snowFlake(1L) + "";
        // 3. 构建请求参数
        Map<String, Object> params = new TreeMap<>();
        params.put("mchNo", config.get("mchNo"));
        params.put("appId", config.get("appId"));
        params.put("mchOrderNo", downstreamOrderNo);
        Long l = Long.valueOf(BigDecimalUtils.multiply(paymentOrderEntity.getOrderAmount(), paymentOrderEntity.getCurrency()));
        params.put("refundAmount", l); // 单位:分
        params.put("currency", paymentOrderEntity.getCurrency());
        params.put("notifyUrl", config.get("payNotifyUrl")); // 商户退款单号
        params.put("reqTime", String.valueOf(System.currentTimeMillis()));
        params.put("version", "1.0");
        params.put("signType", "MD5");
        params.put("refundReason", paymentOrderEntity.getRefundReason());
        params.put("mchRefundNo", requestId); // 商户退款单号
        log.info("Easylink downstreamOrderNo:{},params{}", downstreamOrderNo, JSON.toJSONString(params));
        // 生成签名
        String sign = SignatureTool.easyLinkSign(params, config.get("appSecret"));
        params.put("sign", sign);
        return params;
    }


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


    private static Map<String, String> getStringStringMap(String paymentMethod,String payinConfig, MerchantPaymentProviderEntity merchantPaymentProviderEntity) {
        if (StringUtils.isEmpty(payinConfig)) {
            throw new CustomException(CodeEunm.未配置.getCode(), "渠道配置" + CodeEunm.未配置.getMsg());
        }
        String accExt = merchantPaymentProviderEntity.getProviderExt();
        JSONObject jsonObject = JSONObject.parseObject(accExt);
        String mchNo = FuturebankUtil.getConfigValue(payinConfig, paymentMethod + ".mchNo");
        String appId = FuturebankUtil.getConfigValue(payinConfig, paymentMethod + ".appId");
        String appSecret = FuturebankUtil.getConfigValue(payinConfig, paymentMethod + ".appSecret");
        String pro = FuturebankUtil.getConfigValue(payinConfig, "pro");
        if ("true".equals(pro)) {
            mchNo = jsonObject.getString("mchNo");
            appId = jsonObject.getString("appId");
            appSecret = jsonObject.getString("appSecret");
        }

        Map<String, String> config = new HashMap<>();
        config.put("mchNo", mchNo);
        config.put("appId", appId);
        config.put("refundurl", FuturebankUtil.getConfigValue(payinConfig, "common.refundurl"));
        config.put("appSecret", appSecret);
        config.put("payNotifyUrl", FuturebankUtil.getConfigValue(payinConfig, "common.pay_notify_url")); // 收集缺失的配置项
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
