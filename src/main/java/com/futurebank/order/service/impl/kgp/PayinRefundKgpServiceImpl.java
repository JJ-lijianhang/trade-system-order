package com.futurebank.order.service.impl.kgp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.futurebank.cache.RedisCache;
import com.futurebank.order.entity.MerchantEntity;
import com.futurebank.order.entity.PaymentOrderDownstreamEntity;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.entity.PaymentProviderEntity;
import com.futurebank.order.service.HttpClientService;
import com.futurebank.order.service.IdGeneratorService;
import com.futurebank.order.service.MerchantService;
import com.futurebank.order.service.PaymentOrderDownstreamService;
import com.futurebank.order.service.PaymentOrderService;
import com.futurebank.order.service.payin.PayinRefundQueryService;
import com.futurebank.order.service.payin.PayinRefundService;
import com.futurebank.order.utils.FuturebankUtil;
import com.futurebank.pojo.utils.Unique17DigitIdGeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ben
 * @date 2024/3/31 23:20
 **/
@Slf4j
@Service("payin-refund-kgp")
class PayinRefundKgpServiceImpl implements PayinRefundService {
    @Autowired
    PaymentOrderService paymentOrderSercice;

    @Autowired
    IdGeneratorService idGeneratorService;

    @Autowired
    HttpClientService httpClientService;
    @Autowired
    PaymentOrderDownstreamService paymentOrderDownstreamService;


    @Autowired
    MerchantService merchantService;

    @Autowired
    PaymentOrderService paymentOrderService;

    @Autowired
    RedisCache redisCache;

    @Autowired
    PayinRefundQueryService payinRefundQueryService;

    @Autowired
    OSSClient ossClient;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;


    @Override
    public void refund(PaymentOrderEntity paymentOrderEntity, PaymentProviderEntity paymentProviderEntity) throws Exception {
        MerchantEntity merchantEntity = merchantService.getMerchant(paymentOrderEntity.getMerchantId() + "");

        if (merchantEntity == null) {
            log.error("kgp 未找到商户信息 {}", paymentOrderEntity.getDownstreamOrderNo());
            return;
        }


        log.info("paymentOrderEntity={}", paymentOrderEntity);
        //获取原始订单
        PaymentOrderEntity paymentOrderOri = paymentOrderSercice.getPaymentOrderByOrderNo(paymentOrderEntity.getReferenceOrderNo());
        if (paymentOrderOri == null) {
            log.info("原始订单不存在 订单号={}", paymentOrderEntity.getDownstreamOrderNo());
            return;
        }

        String payinConfig = paymentProviderEntity.getPayinConfig();

        if (StringUtils.isBlank(payinConfig)) {

            log.error("kgp 未找到相关配置 {}", paymentOrderEntity.getDownstreamOrderNo());
            return;
        }

        String url = FuturebankUtil.getConfigValue(payinConfig, "refund.url");
        String PartnerID = FuturebankUtil.getConfigValue(payinConfig, "PartnerID");
        String ProjectID = FuturebankUtil.getConfigValue(payinConfig, "ProjectID");
        String ProjectKey = FuturebankUtil.getConfigValue(payinConfig, "ProjectKey");
        String requestId = idGeneratorService.snowFlake(1L) + "";
        if (StringUtils.isBlank(url) || StringUtils.isBlank(PartnerID) || StringUtils.isBlank(ProjectID) || StringUtils.isBlank(ProjectKey)) {

            log.error("kgp url|PartnerID|PartnerID|PartnerID 配置异常 {}", paymentOrderEntity.getDownstreamOrderNo());
            return;
        }
        if (StringUtils.isBlank(requestId)) {
            log.error("kgp requestId 生成异常 {}", paymentOrderEntity.getDownstreamOrderNo());
            return;
        }


        String klashaTokenKey = "Klasha:Login:Token";

        String token = getToken(paymentProviderEntity);

        if (StringUtils.isBlank(token)) {
            log.error("kgp token 生成异常 {}", paymentOrderEntity.getDownstreamOrderNo());
            return;
        }
        String refundId = null;
        if (StringUtils.isBlank(paymentOrderEntity.getUpstreamOrderNo()))
            refundId = Unique17DigitIdGeneratorUtils.generate17DigitId();
        else refundId = paymentOrderEntity.getUpstreamOrderNo();

        String initRefundKey = "kgp:refund:init:" + paymentOrderEntity.getPlatformOrderNo();
        Object cacheObject = redisCache.getCacheObject(initRefundKey);

        if (cacheObject != null) {
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("content-type", "application/json");
            headerMap.put("Authorization", "Bearer " + token);
            headerMap.put("RequestID", requestId);
            headerMap.put("PartnerID", PartnerID);
            headerMap.put("ProjectID", ProjectID);
            headerMap.put("ProjectKey", ProjectKey);
            Map<String, Object> postMap = new HashMap<>();
//            postMap.put("partnerShopID", "1849273743214452736");
            postMap.put("partnerShopID",merchantEntity.getKgpPartnerShopId());
            postMap.put("partnerPaymentID", paymentOrderOri.getUpstreamOrderNo());
            postMap.put("partnerRefundID", refundId);
            postMap.put("amount", paymentOrderEntity.getOrderAmount().setScale(2));
            postMap.put("refundType", "FULL");
            postMap.put("refundReason", "R0001");
            postMap.put("remark", "refund remark");
            postMap.put("refundCashID", "r0001");

            PaymentOrderDownstreamEntity paymentOrderDownstreamEntity = paymentOrderDownstreamService.getPaymentOrderDownstreamByOrderNo(paymentOrderOri.getPlatformOrderNo());

            if (paymentOrderDownstreamEntity == null) {
                log.error("kgp 未找到下游订单 {}", paymentOrderEntity.getDownstreamOrderNo());
                return;
            }
            log.info("kgp 下游订单信息 {}", paymentOrderDownstreamEntity.getDownstreamRequestMessage());
            if (StringUtils.isBlank(paymentOrderDownstreamEntity.getDownstreamRequestMessage())) {

                log.error("kgp 未找到下游订单信息 {}", paymentOrderEntity.getDownstreamOrderNo());
                return;
            }

            JSONObject json = JSON.parseObject(paymentOrderDownstreamEntity.getDownstreamRequestMessage());
            String bankID = null;
            try {
//                String accountNo = "11111111111";
//                String accountName = json.getJSONObject("paymentMethod").getString("holderName");
                bankID = json.getJSONObject("paymentMethod").getString("bankName");

                if (StringUtils.isNotBlank(bankID) && (bankID.equals("kma") || bankID.equals("scb"))) {


                    Map<String, String> acountMap = new HashMap<>();


                    if (bankID.equals("scb")) {
                        acountMap.put("bankID", "014");
//                        acountMap.put("accountNo", "3612803119");
//                        acountMap.put("accountName", "Tanongsak Soton");
                    } else if (bankID.equals("kma")) {
                        acountMap.put("bankID", "025");
//                        acountMap.put("accountNo", "8004302933");
//                        acountMap.put("accountName", "TEST17 TEST17");
                    }
                    acountMap.put("accountNo",paymentOrderEntity.getRefundAccountNo());
                    acountMap.put("accountName", paymentOrderEntity.getReturnAccountName());
                    acountMap.put("type", "account");
                    postMap.put("refundAccount", acountMap);
                    List<String> documentIDs = new ArrayList<>();
                    documentIDs.add(paymentOrderEntity.getDocumentId());
                    postMap.put("documentIDs", documentIDs);

                    if (StringUtils.isBlank(paymentOrderEntity.getTransRefId())) {
                        log.info("kgp 获取交易id失败 {} ", paymentOrderEntity.getDownstreamOrderNo());
                        return;
                    }
                    String transRef = extractTransRef(paymentOrderEntity.getTransRefId());
                    postMap.put("transRef", transRef);

                }

            } catch (Exception e) {
                log.error("kgp 获取银行名称失败 {}", paymentOrderEntity.getDownstreamOrderNo());
            }


            String requestStr = JSON.toJSONString(postMap);

            log.info("上游请求={}, url={},  token={} data={}", "kgp", url, JSON.toJSONString(headerMap), JSON.toJSONString(postMap));

            String resp = httpClientService.klashaJson(url, requestStr, headerMap, "kgp");
            log.info("kgp refund response  refund url={}, postMap={}, response={}", url, requestStr, resp);

            JSONObject jsonObject = JSON.parseObject(resp);
            String refundStatus = jsonObject.get("refundStatus") == null ? null : jsonObject.getString("refundStatus");
            paymentOrderEntity.setRequestStatus(0);
            if (StringUtils.isBlank(refundStatus)) {
                paymentOrderService.update(paymentOrderEntity);
                return;
            }

            paymentOrderEntity.setUpstreamOrderNo(refundId);
            paymentOrderService.update(paymentOrderEntity);
            redisCache.setCacheObject(initRefundKey, null, 1000 * 3600 * 24 * 7, TimeUnit.SECONDS);
            return;
        }

        payinRefundQueryService.refundQuery(paymentOrderEntity, paymentProviderEntity);

    }

    public void uploadPaymentSlip(PaymentOrderEntity paymentOrderEntity, PaymentProviderEntity paymentProviderEntity) throws Exception {
        String payinConfig = paymentProviderEntity.getPayinConfig();
        String url = FuturebankUtil.getConfigValue(payinConfig, "refund.url");
        String PartnerID = FuturebankUtil.getConfigValue(payinConfig, "PartnerID");
        String ProjectID = FuturebankUtil.getConfigValue(payinConfig, "ProjectID");
        String ProjectKey = FuturebankUtil.getConfigValue(payinConfig, "ProjectKey");
        String requestId = idGeneratorService.snowFlake(1L) + "";

        String token = getToken(paymentProviderEntity);

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("content-type", "application/json");
        headerMap.put("Authorization", "Bearer " + token);
        headerMap.put("RequestID", requestId);
        headerMap.put("PartnerID", PartnerID);
        headerMap.put("ProjectID", ProjectID);
        headerMap.put("ProjectKey", ProjectKey);

        try {
            InputStream inputStream = ossClient.getObject(bucketName, "PaymentSlip/1852287091761872896.png").getObjectContent();
            if (inputStream == null) {
                log.error("kgp 获取凭证失败 {}", paymentOrderEntity.getDownstreamOrderNo());
                return;
            }
            String resp = httpClientService.uploadFile(url, inputStream, headerMap, "1852287091761872896.png", "kgp");
            log.info("kgp refund response  refund url={}, postMap={}, response={}", url, JSON.toJSONString(headerMap), resp);

        } catch (Exception e) {
            log.error("kgp 获取凭证失败 {}", paymentOrderEntity.getDownstreamOrderNo());
            return;
        }


    }

    /**
     * 获取token
     *
     * @param paymentProviderEntity
     */
    public String getToken(PaymentProviderEntity paymentProviderEntity) throws Exception {

        String kgpTokenKey = "KGP:Login:Token";
        String token = redisCache.getCacheObject(kgpTokenKey) == null ? null : redisCache.getCacheObject(kgpTokenKey);

        if (StringUtils.isNotBlank(token)) return token;

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

        if (StringUtils.isNotBlank(xTestMode) && xTestMode.equals("true")) headerMap.put("x-test-mode", xTestMode);

        if (StringUtils.isNotBlank(envId)) headerMap.put("env-id", envId);

        headerMap.put("Content-Type", "application/x-www-form-urlencoded");


        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credentials");

        String response = httpClientService.postWithKgpToken(url, params, headerMap, "kgp");
        log.info("kgpTokenService:getToken:response:{}", response);
        if (response == null) return null;
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject.get("access_token") != null) {
            token = jsonObject.getString("access_token");
            log.info("kgp token {}", token);
            redisCache.setCacheObject(kgpTokenKey, token);
            return token;
        }
        return null;
    }


    /**
     * 从二维码字符串中提取TransRef
     *
     * @param qrString 二维码字符串
     * @return 提取的TransRef
     */
    public static String extractTransRef(String qrString) {
        // 定义 TransRef 在字符串中的起始位置
        int transRefStartIndex = 25;
        // 定义 TransRef 的最大长度
        int transRefMaxLength = 25;

        // 检查二维码字符串的长度是否足够
        if (qrString.length() >= transRefStartIndex + transRefMaxLength) {
            // 截取 TransRef
            return qrString.substring(transRefStartIndex, transRefStartIndex + transRefMaxLength);
        } else if (qrString.length() > transRefStartIndex) {
            // 如果字符串不足以提供25个字符，取剩余的字符
            return qrString.substring(transRefStartIndex);
        } else {
            // 二维码字符串不包含 TransRef
            return null;
        }
    }

    //00460006000001010301402252024110182FWlN2n3x3XRr7QC5102TH9104D52D
//
//52024110182FWlN2n3x3XRr7Q
    public static void main(String[] args) {
        // 示例二维码字符串
//        String qrString = "0040600600000100130010225123456789012345678901234551027H91040BDC";
        String qrString = "00460006000001010301402252024110182FWlN2n3x3XRr7QC5102TH9104D52D";
//        String qrString = "0040600600000100130010225123456789012345678901234551027H91040BDC";

        // 提取 TransRef
        String transRef = extractTransRef(qrString);

        // 输出结果
        if (transRef != null) {
            System.out.println("TransRef: " + transRef);
        } else {
            System.out.println("无法提取 TransRef，二维码字符串长度不足。");
        }
    }
}
