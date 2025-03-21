package com.futurebank.order.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.futurebank.cache.RedisCache;
import com.futurebank.order.entity.MerchantEntity;
import com.futurebank.order.entity.MerchantSettlementEntity;
import com.futurebank.order.entity.MerchantSettmentDetailsEntity;
import com.futurebank.order.entity.MerchantUpstreamSettlementEntity;
import com.futurebank.order.entity.PaymentBillingEntity;
import com.futurebank.order.entity.PaymentProviderEntity;
import com.futurebank.order.entity.ProductChannelEntity;
import com.futurebank.order.mapper.MerchantMapper;
import com.futurebank.order.mapper.MerchantSettlementMapper;
import com.futurebank.order.mapper.MerchantSettmentDetailsMapper;
import com.futurebank.order.mapper.MerchantUpstreamSettlementMapper;
import com.futurebank.order.pojo.CurrencyRecord;
import com.futurebank.pojo.enums.MerchantChargeEventEnum;
import com.futurebank.pojo.enums.OrderTypeEnum;
import com.futurebank.pojo.enums.PaymentStatusEnum;
import com.futurebank.pojo.service.PayinFixedService;
import com.futurebank.pojo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ben
 * @date 2024/5/27 19:12
 **/
@Service
@Slf4j
public class MerchantSettlementHandleService {
    @Autowired
    private PaymentBillingService paymentBillingService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    HttpClientService httpClientService;
    private static final Map<String, BigDecimal> map = new HashMap<>();
    @Autowired
    ProductChannelService productChannelService;

    @Autowired
    MerchantSettlementService merchantSettlementService;
    @Autowired
    RedisCache redisCache;
    @Autowired
    MerchantUpstreamSettlementService merchantUpstreamSettlementService;
    @Autowired
    MerchantMapper merchantMapper;
    @Autowired
    MerchantSettlementMapper merchantSettlementMapper;
    @Autowired
    MerchantSettmentDetailsMapper merchantSettmentDetailsMapper;
    @Autowired
    MerchantUpstreamSettlementMapper merchantUpstreamSettlementMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    Map<String, BigDecimal> settlementExchangeMap = new HashMap<>();

    @Transactional(rollbackFor = Exception.class)
    public void settlement() {
        //TODO 获取商户列表
        List<MerchantEntity> merchantEntityList = merchantService.getMerchantEntityList();


        for (MerchantEntity merchantEntity : merchantEntityList) {
            String settlementDate = DateUtils.dateToStr(new Date(), "yyyyMMdd");
            merchantSettmentDetailsMapper.deleteBySettlementDate(merchantEntity.getId(), settlementDate);
            merchantSettlementMapper.deleteBySettlementDate(merchantEntity.getId(), settlementDate);
            merchantUpstreamSettlementMapper.deleteBySettlementDate(merchantEntity.getId(), settlementDate);

            List<PaymentBillingEntity> paymentBillingEntities = paymentBillingService.getPaymentBillingEntity(merchantEntity.getId());

            String key = "Futurepay:merchant:settlement:" + merchantEntity.getId() + "_" + DateUtils.dateToStr(new Date(), "yyyyMMdd");
            String upstreamKey = "Futurepay:merchant:upstream:settlement:" + merchantEntity.getId() + "_" + DateUtils.dateToStr(new Date(), "yyyyMMdd");
            redisCache.deleteObject(key);
            redisCache.deleteObject(upstreamKey);
            for (PaymentBillingEntity paymentBillingEntity : paymentBillingEntities) {
                ProductChannelEntity productChannelEntity = productChannelService.getProductChannel(paymentBillingEntity.getProductChannelId());
                if (productChannelEntity == null)
                    continue;

                if (Integer.valueOf(1).equals(paymentBillingEntity.getDownstreamSettlementStatus())) {
                    continue;
                }

                if (!Integer.valueOf(1).equals(paymentBillingEntity.getUpstreamSettlementStatus())) {
                    continue;
                }


                paymentBillingEntity.setDownstreamSettlementStatus(1);
                paymentBillingEntity.setDownstreamSettlementDate(new Date());
                paymentBillingService.update(paymentBillingEntity);


                realAmount(paymentBillingEntity, key, settlementDate);
                realAmountUpstream(paymentBillingEntity, upstreamKey, settlementDate);
                //商户的消费货币 计算 固定费用和百分比费用
                merchantFix(paymentBillingEntity, key, settlementDate);
                merchantPercentage(paymentBillingEntity, key, settlementDate, productChannelEntity);
                //商户的处理货币计算 固定费用和百分比费用
                upstreamFix(paymentBillingEntity, upstreamKey, settlementDate);
                upstreamPercentage(paymentBillingEntity, upstreamKey, settlementDate);
                upstreamFee(paymentBillingEntity, upstreamKey, settlementDate);
                markupProfit(paymentBillingEntity, upstreamKey, settlementDate);
            }


            Map<String, Double> upstreamCurrencyMap = redisCache.getCacheMap(upstreamKey);
            List<CurrencyRecord> upstreamRecords = convertMapToRecords(upstreamCurrencyMap);
            for (CurrencyRecord record : upstreamRecords) {
//                System.out.println(record);
                BigDecimal transactionAmount = record.getAmount() == null ? new BigDecimal("0") : new BigDecimal(record.getAmount());
                BigDecimal fixFee = record.getMerchantFixFee() == null ? new BigDecimal("0") : new BigDecimal(record.getMerchantFixFee());
                BigDecimal percentageFee = record.getMerchantPercentageFee() == null ? new BigDecimal("0") : new BigDecimal(record.getMerchantPercentageFee());
                BigDecimal rateFee = fixFee.add(percentageFee);
                BigDecimal markup = record.getMarkup() == null ? new BigDecimal("0") : new BigDecimal(record.getMarkup());
                BigDecimal settlementAmount = transactionAmount.add(rateFee).add(markup);

                BigDecimal upstreamRateFee = record.getUpstreamFee() == null ? new BigDecimal("0") : new BigDecimal(record.getUpstreamFee());

                MerchantUpstreamSettlementEntity merchantUpstreamSettlementEntity = new MerchantUpstreamSettlementEntity();
                merchantUpstreamSettlementEntity.setSettleId(settlementDate);
                merchantUpstreamSettlementEntity.setMerchantId(merchantEntity.getId());
                merchantUpstreamSettlementEntity.setCurrency(record.getCurrency());
                merchantUpstreamSettlementEntity.setTransactionAmount(transactionAmount);
                merchantUpstreamSettlementEntity.setSettlementAmount(settlementAmount);
                merchantUpstreamSettlementEntity.setRateFee(rateFee);
                merchantUpstreamSettlementEntity.setFixFee(fixFee);
                merchantUpstreamSettlementEntity.setPercentageFee(percentageFee);
                merchantUpstreamSettlementEntity.setUpstreamRateFee(upstreamRateFee);
                merchantUpstreamSettlementEntity.setTxMarkup(markup);
                merchantUpstreamSettlementEntity.setSettleStatus(0);
                merchantUpstreamSettlementEntity.setCreatedTime(new Date());
                merchantUpstreamSettlementEntity.setPublishTime(new Date());
                merchantUpstreamSettlementService.add(merchantUpstreamSettlementEntity);
            }
            merchantMapper.updateMerchantSettlementDate(merchantEntity.getId());

            List<Map<String, Object>> list = merchantSettlementMapper.aggregateMerchantSettlement(merchantEntity.getId(), settlementDate);


            for (Map<String, Object> map : list) {
                MerchantSettlementEntity merchantSettlementEntity = new MerchantSettlementEntity();
                merchantSettlementEntity.setSettleId(settlementDate);
                merchantSettlementEntity.setMerchantId((Long) map.get("merchantId"));
                merchantSettlementEntity.setCurrency((String) map.get("merchantCurrency"));
                BigDecimal transactionAmount = map.get("transactionAmount") == null ? BigDecimal.ZERO : (BigDecimal) map.get("transactionAmount");

                BigDecimal rateFee = map.get("rateFee") == null ? BigDecimal.ZERO : (BigDecimal) map.get("rateFee");
                BigDecimal settlementAmount = map.get("realAmount") == null ? BigDecimal.ZERO : (BigDecimal) map.get("realAmount");
                merchantSettlementEntity.setSettlementAmount(settlementAmount);
                merchantSettlementEntity.setTransactionAmount(transactionAmount);
                merchantSettlementEntity.setRateFee(rateFee);
                merchantSettlementEntity.setSettleStatus(0);
                merchantSettlementEntity.setCreatedTime(new Date());
                merchantSettlementService.add(merchantSettlementEntity);
            }
        }


    }



    public static List<CurrencyRecord> convertMapToRecords(Map<String, Double> currencyMap) {
        Map<String, CurrencyRecord> recordMap = new HashMap<>();

        for (Map.Entry<String, Double> entry : currencyMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();

            String[] parts = key.split("_");
            if (parts.length < 2) continue;

            String currency = parts[0];
            String type = parts[1];
            String feeType = parts.length > 2 ? parts[2] : null;

            CurrencyRecord record = recordMap.getOrDefault(currency, new CurrencyRecord(currency, 0.0, 0.0, 0.0, 0.0, 0.0));

            if ("amount".equals(type)) {
                record.setAmount(value);
            } else if ("merchant".equals(type)) {
                if ("fixfee".equals(feeType)) {
                    record.setMerchantFixFee(value);
                } else if ("percentagefee".equals(feeType)) {
                    record.setMerchantPercentageFee(value);
                }
            } else if ("upstream".equals(type) && "fee".equals(feeType)) {
                record.setUpstreamFee(value);
            } else if ("markup".equals(type)) {
                record.setMarkup(value);
            }

            recordMap.put(currency, record);
        }

        return new ArrayList<>(recordMap.values());
    }

    public void markupProfit(PaymentBillingEntity paymentBillingEntity, String key, String settlementDate) {
        if (paymentBillingEntity.getPaymentTxMarkup() == null)
            return;
        //缓存处理部分
        if (paymentBillingEntity.getOrderStatus().equals(PaymentStatusEnum.交易成功.getStatus())
                && paymentBillingEntity.getOrderType().equals(OrderTypeEnum.交易.getValue())) {

            BigDecimal amountTotal = paymentBillingEntity.getPaymentTxMarkup().setScale(6, BigDecimal.ROUND_HALF_DOWN);
            amountTotal = amountTotal == null ? BigDecimal.ZERO : amountTotal;
            amountTotal = amountTotal.multiply(new BigDecimal("-1")).setScale(6, BigDecimal.ROUND_HALF_DOWN);

            redisTemplate.opsForHash().increment(key, paymentBillingEntity.getCurrency() + "_markup", amountTotal.doubleValue());


//            RedisCache.hIncrByFloat(key, paymentBillingEntity.getCurrency() + "_markup", amountTotal.doubleValue());
        }

    }


    public void realAmount(PaymentBillingEntity paymentBillingEntity, String key, String settlementDate) {

        //缓存处理部分
        if (paymentBillingEntity.getOrderStatus().equals(PaymentStatusEnum.交易成功.getStatus())) {

            BigDecimal amountTotal = paymentBillingEntity.getMerchantOrderAmount();
//            BigDecimal amountTotal = paymentBillingEntity.getOrderAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN);
            switch (paymentBillingEntity.getOrderType()) {
                case "chargeback":
                    amountTotal = amountTotal.multiply(new BigDecimal("-1"));
                    break;
                case "refund":
                    amountTotal = amountTotal.multiply(new BigDecimal("-1"));
                    break;
            }
//            RedisCache.hIncrByFloat(key, paymentBillingEntity.getCurrency() + "_amount", amountTotal.doubleValue());
//            settlementDetails(paymentBillingEntity, getFeeType(paymentBillingEntity.getOrderType()), amountTotal.toString(), paymentBillingEntity.getCurrency(), settlementDate);
//            RedisCache.hIncrByFloat(key, paymentBillingEntity.getMerchantCurrency() + "_amount", amountTotal.doubleValue());

            redisTemplate.opsForHash().increment(key, paymentBillingEntity.getMerchantCurrency() + "_amount", amountTotal.doubleValue());


            settlementDetails(paymentBillingEntity, getFeeType(paymentBillingEntity.getOrderType()), amountTotal.toString(), paymentBillingEntity.getMerchantCurrency(), settlementDate);
        }

    }

    public String getFeeType(String type) {
        switch (type) {
            case "transaction":
                return MerchantChargeEventEnum.交易.getName();
            case "chargeback":
                return MerchantChargeEventEnum.拒付.getName();
            case "refund":
                return MerchantChargeEventEnum.退款.getName();
        }
        return null;
    }


    @Autowired
    PaymentProviderService paymentProviderService;

    public void merchantFix(PaymentBillingEntity paymentBillingEntity, String key, String settlementDate) {
        JSONObject fixFeeFsonObject = JSON.parseObject(paymentBillingEntity.getDownstreamFixedFee());
        if (fixFeeFsonObject == null){
            log.error(">>>>>>>> 商户固定费用为空 , paymentBillingEntity{}" , paymentBillingEntity);
            return;
        }
        for (Map.Entry<String, Object> entry : fixFeeFsonObject.entrySet()) {
            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            if (entry.getKey().equals(MerchantChargeEventEnum.固定费用.getName())) {
                if (!paymentBillingEntity.getOrderType().equals(OrderTypeEnum.交易.getValue()) && !paymentBillingEntity.getOrderStatus().equals(PaymentStatusEnum.交易成功.getStatus()))
                    continue;

                //TODO 判断是否支持网关
                PaymentProviderEntity paymentProviderEntity = paymentProviderService.getPaymentProvider(paymentBillingEntity.getProviderId());

                if (paymentProviderEntity == null)
                    continue;

                if (paymentProviderEntity.getSupportGateway().equals(0) && !paymentBillingEntity.getOrderStatus().equals(PaymentStatusEnum.交易成功.getStatus())) {
                    continue;
                }

            } else {
                if (!paymentBillingEntity.getOrderStatus().equals(PaymentStatusEnum.交易成功.getStatus()))
                    continue;
            }


            amount = amount == null ? "0" : amount;
            amount = new BigDecimal(amount).multiply(new BigDecimal("-1")).setScale(6, BigDecimal.ROUND_HALF_DOWN).toString();

//            RedisCache.hIncrByFloat(key, currency + "_fixFee", new BigDecimal(amount).setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue());
            redisTemplate.opsForHash().increment(key, currency + "_fixFee", new BigDecimal(amount).setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue());


            settlementDetails(paymentBillingEntity, MerchantChargeEventEnum.固定费用.getName(), new BigDecimal(amount).setScale(6, BigDecimal.ROUND_HALF_DOWN).toString(), currency, settlementDate);
        }
    }


    public void merchantPercentage(PaymentBillingEntity paymentBillingEntity, String key, String settlementDate, ProductChannelEntity productChannelEntity) {
        JSONObject percentageFeeJsonObject = null;
        if (productChannelEntity.getFeeType().equals(1)) {
            percentageFeeJsonObject = JSON.parseObject(paymentBillingEntity.getDownstreamFee());
        }
        if (productChannelEntity.getFeeType().equals(2)) {
            percentageFeeJsonObject = JSON.parseObject(paymentBillingEntity.getDownstreamFeeC());
        }
        if (percentageFeeJsonObject == null)
            return;

        //百分比手续费
        for (Map.Entry<String, Object> entry : percentageFeeJsonObject.entrySet()) {

            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            if (paymentBillingEntity.getOrderStatus().equals(PaymentStatusEnum.交易成功.getStatus())) {
                amount = amount == null ? "0" : amount;
                amount = new BigDecimal(amount).multiply(new BigDecimal("-1")).setScale(6, BigDecimal.ROUND_HALF_DOWN).toString();

//                RedisCache.hIncrByFloat(key, paymentBillingEntity.getCurrency() + "_percentageFee", new BigDecimal(amount).setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue());
                redisTemplate.opsForHash().increment(key, paymentBillingEntity.getCurrency() + "_percentageFee", new BigDecimal(amount).setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue());


                settlementDetails(paymentBillingEntity, MerchantChargeEventEnum.百分比费用.getName(), new BigDecimal(amount).setScale(6, BigDecimal.ROUND_HALF_DOWN).toString(), currency, settlementDate);
//                RedisCache.hIncrByFloat(key, paymentBillingEntity.getMerchantCurrency() + "_percentageFee", new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
//                settlementDetails(paymentBillingEntity, MerchantChargeEventEnum.百分比费用.getName(), new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString(), paymentBillingEntity.getMerchantCurrency(), settlementDate);

            }
        }

    }


    public void realAmountUpstream(PaymentBillingEntity paymentBillingEntity, String key, String settlementDate) {
        //缓存处理部分
        if (paymentBillingEntity.getOrderStatus().equals(PaymentStatusEnum.交易成功.getStatus())) {

            BigDecimal amountTotal = paymentBillingEntity.getOrderAmount();
            switch (paymentBillingEntity.getOrderType()) {
                case "chargeback":
                    amountTotal = amountTotal.multiply(new BigDecimal("-1"));
                    break;
                case "refund":
                    amountTotal = amountTotal.multiply(new BigDecimal("-1"));
                    break;
            }
//            RedisCache.hIncrByFloat(key, paymentBillingEntity.getCurrency() + "_amount", amountTotal.doubleValue());
            redisTemplate.opsForHash().increment(key, paymentBillingEntity.getCurrency() + "_amount", amountTotal.doubleValue());

        }

    }


    public void upstreamFix(PaymentBillingEntity paymentBillingEntity, String key, String settlementDate) {
        JSONObject fixFeeFsonObject = JSON.parseObject(paymentBillingEntity.getDownstreamFixedFee());

        if (fixFeeFsonObject == null) return;

        for (Map.Entry<String, Object> entry : fixFeeFsonObject.entrySet()) {
            if (!entry.getKey().equals("fixed") && !paymentBillingEntity.getOrderStatus().equals(PaymentStatusEnum.交易成功.getStatus()))
                continue;
            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            amount = amount == null ? "0" : amount;
            amount = new BigDecimal(amount).multiply(new BigDecimal("-1")).setScale(6, BigDecimal.ROUND_HALF_DOWN).toString();

//            RedisCache.hIncrByFloat(key, currency + "_merchant_fixfee", new BigDecimal(amount).setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue());
            redisTemplate.opsForHash().increment(key, currency + "_merchant_fixfee", new BigDecimal(amount).setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue());

        }
    }


    public void upstreamPercentage(PaymentBillingEntity paymentBillingEntity, String key, String settlementDate) {
        JSONObject percentageFeeJsonObject = JSON.parseObject(paymentBillingEntity.getDownstreamFee());
        if (percentageFeeJsonObject == null) return;
        //百分比手续费
        for (Map.Entry<String, Object> entry : percentageFeeJsonObject.entrySet()) {
            if (paymentBillingEntity.getOrderStatus().equals(PaymentStatusEnum.交易成功.getStatus())) {
                String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
                String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
                amount = amount == null ? "0" : amount;
                amount = new BigDecimal(amount).multiply(new BigDecimal("-1")).setScale(6, BigDecimal.ROUND_HALF_DOWN).toString();
//                log.info(">>> upstreamPercentage : billing currency={},orderAmount={},downStreamTxRate={}" ,
//                        paymentBillingEntity.getCurrency() ,
//                        paymentBillingEntity.getOrderAmount() + "*" + paymentBillingEntity.getDownstreamTxRate(),
//                        amount);
//                RedisCache.hIncrByFloat(key, paymentBillingEntity.getCurrency() + "_merchant_percentagefee", new BigDecimal(amount).setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue());
                redisTemplate.opsForHash().increment(key, paymentBillingEntity.getCurrency() + "_merchant_percentagefee", new BigDecimal(amount).setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue());
            }
        }
    }

    /**
     * 上游扣除我们的真实手续费
     *
     * @param paymentBillingEntity
     * @param key
     * @param settlementDate
     */
    public void upstreamFee(PaymentBillingEntity paymentBillingEntity, String key, String settlementDate) {
        JSONObject upstreamActualFeeJsonObject = JSON.parseObject(paymentBillingEntity.getUpstreamActualFee());
        if (upstreamActualFeeJsonObject == null) return;
        for (Map.Entry<String, Object> entry : upstreamActualFeeJsonObject.entrySet()) {
            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            amount = amount == null ? "0" : amount;
            amount = new BigDecimal(amount).multiply(new BigDecimal("-1")).setScale(6, BigDecimal.ROUND_HALF_DOWN).toString();
//            RedisCache.hIncrByFloat(key, currency + "_upstream_fee", new BigDecimal(amount).setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue());
            redisTemplate.opsForHash().increment(key, currency + "_upstream_fee", new BigDecimal(amount).setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue());
        }

    }


    @Autowired
    MerchantSettmentDetailsService merchantSettmentDetailsService;

    public void settlementDetails(PaymentBillingEntity paymentBillingEntity, String feeType, String amount, String currency, String settlementDate) {
        MerchantSettmentDetailsEntity merchantSettmentDetailsEntity = new MerchantSettmentDetailsEntity();

        merchantSettmentDetailsEntity.setSettlementId(settlementDate);
        merchantSettmentDetailsEntity.setDownstreamOrderNo(paymentBillingEntity.getDownstreamOrderNo());
        merchantSettmentDetailsEntity.setPlatformOrderNo(paymentBillingEntity.getPlatformOrderNo());
        merchantSettmentDetailsEntity.setOrderType(paymentBillingEntity.getOrderType());
        merchantSettmentDetailsEntity.setOrderStatus(paymentBillingEntity.getOrderStatus());
        merchantSettmentDetailsEntity.setOrderCreateTime(paymentBillingEntity.getOrderCreateTime());
        merchantSettmentDetailsEntity.setOrderCompleteTime(paymentBillingEntity.getOrderCompleteTime());
        merchantSettmentDetailsEntity.setMerchantId(paymentBillingEntity.getMerchantId());
        merchantSettmentDetailsEntity.setPaymentMethod(paymentBillingEntity.getPaymentMethod());
        merchantSettmentDetailsEntity.setFeeType(feeType);
        BigDecimal amountB = amount == null ? BigDecimal.ZERO : new BigDecimal(amount);
        // 保存 处理货币金额
        BigDecimal amountA = paymentBillingEntity.getOrderAmount() == null ? BigDecimal.ZERO : paymentBillingEntity.getOrderAmount();

        //处理货币及金额
        merchantSettmentDetailsEntity.setMerchantOrderAmount(amountB);
        merchantSettmentDetailsEntity.setMerchantCurrency(currency);
        //消费货币及金额
        merchantSettmentDetailsEntity.setConsumerAmount(paymentBillingEntity.getMerchantOrderAmount() == null ? BigDecimal.ZERO : paymentBillingEntity.getMerchantOrderAmount().setScale(2, BigDecimal.ROUND_DOWN));
        merchantSettmentDetailsEntity.setConsumerCurrency(paymentBillingEntity.getMerchantCurrency());

        merchantSettmentDetailsEntity.setOrderAmount(amountA);
        merchantSettmentDetailsEntity.setCurrency(paymentBillingEntity.getCurrency());
        merchantSettmentDetailsEntity.setCountry(paymentBillingEntity.getCountry());
        merchantSettmentDetailsEntity.setReferenceOrderNo(paymentBillingEntity.getReferenceOrderNo());

        merchantSettmentDetailsEntity.setDownstreamFixedFee(paymentBillingEntity.getDownstreamFixedFee());
        merchantSettmentDetailsEntity.setDownstreamTxRate(paymentBillingEntity.getDownstreamTxRate());
        merchantSettmentDetailsEntity.setDownstreamFee(paymentBillingEntity.getDownstreamFee());
        merchantSettmentDetailsEntity.setCreatedTime(new Date());
        merchantSettmentDetailsEntity.setUpdateTime(new Date());
        merchantSettmentDetailsService.add(merchantSettmentDetailsEntity);

    }

    @Autowired
    PayinFixedService payinFixedService;

    public BigDecimal upstreamEstimatedFee(PaymentBillingEntity paymentBillingEntity) {
        //TODO 计算预估上游游结算手续费
        // upstreamEstimatedFee = downstreamFixedFee + downstreamFee


        JSONObject fixFeeFsonObject = paymentBillingEntity.getUpstreamFixedFee() == null ? new JSONObject() : JSON.parseObject(paymentBillingEntity.getUpstreamFixedFee());
        JSONObject percentageFeeJsonObject = paymentBillingEntity.getUpstreamFee() == null ? new JSONObject() : JSON.parseObject(paymentBillingEntity.getUpstreamFee());

        BigDecimal totalFee = BigDecimal.ZERO;


        //固定手续费
        for (Map.Entry<String, Object> entry : fixFeeFsonObject.entrySet()) {
            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            BigDecimal fixFee = merchantSettlementHandleService.exchange(new BigDecimal(amount), currency, "USD");

            totalFee = totalFee.add(fixFee);
            log.info("上游-固定手续费 order {} amount {} currency {}  after {} total {}", paymentBillingEntity.getPlatformOrderNo(), amount, currency, fixFee, totalFee);
        }

        //百分比手续费
        for (Map.Entry<String, Object> entry : percentageFeeJsonObject.entrySet()) {
            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            BigDecimal percentageFee = merchantSettlementHandleService.exchange(new BigDecimal(amount), paymentBillingEntity.getCurrency(), "USD");
            totalFee = totalFee.add(percentageFee);
            log.info("上游-百分比手续费 order{} amount {} currency {}  after {} total {}", paymentBillingEntity.getPlatformOrderNo(), amount, paymentBillingEntity.getCurrency(), percentageFee, totalFee);
        }


        return totalFee;
    }


    @Autowired
    MerchantSettlementHandleService merchantSettlementHandleService;




    public BigDecimal exchange(BigDecimal amount, String source, String target) {
        if (StringUtils.isBlank(source) || amount == null)
            return null;

        if (StringUtils.isBlank(target))
            target = "USD";

        if (source.equals(target))
            return amount;

        if (source.equals(target))
            return null;

        String currencyPair = source + target;
        if (map.get(currencyPair) != null)
            return map.get(currencyPair).multiply(amount);

        String exRate = httpClientService.exchangeRate(source, target);
        if (StringUtils.isNotBlank(exRate)) {
            JSONObject jsonObject = JSON.parseObject(exRate);
            JSONObject rate = jsonObject.getJSONObject("quotes");
            if (rate.get(currencyPair) == null) {
                log.info("货币对 {} 不支持", currencyPair);
            }
            map.put(currencyPair, rate.getBigDecimal(currencyPair).setScale(6, BigDecimal.ROUND_HALF_UP));
            return rate.getBigDecimal(currencyPair).multiply(amount);
        }
        return null;

    }

}
