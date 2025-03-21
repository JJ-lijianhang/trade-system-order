package com.futurebank.order.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.futurebank.order.entity.ChannelRatesEntity;
import com.futurebank.order.entity.MerchantChargeEntity;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.entity.ProductChannelEntity;
import com.futurebank.pojo.enums.BusinessIdEnum;
import com.futurebank.pojo.enums.MerchantChargeEventEnum;
import com.futurebank.pojo.enums.MerchantChargeTypeEnum;
import com.futurebank.pojo.enums.MerchantWalletEnum;
import com.futurebank.pojo.enums.ServiceTypeEnum;
import com.futurebank.pojo.service.ChangebackFixedService;
import com.futurebank.pojo.service.PayinFixedService;
import com.futurebank.pojo.service.PayinRateService;
import com.futurebank.pojo.service.RefundFixedService;
import com.futurebank.pojo.vo.TradeBeanReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ben
 * @date 2024/6/5 12:28
 **/
@Service
@Slf4j
public class FundsHandleService {
    @Autowired
    private MerchantChargeService merchantChargeService;
    @Autowired
    private MerchantWalletService merchantWalletService;
    @Autowired
    private IdGeneratorService idGeneratorService;

    @Autowired
    public final static Map<String, BigDecimal> map = new HashMap<>();

    public void refundCharge(PaymentOrderEntity refundOrder) {
        List<TradeBeanReq> tradeBeanReqs = new ArrayList<>();
        TradeBeanReq out = new TradeBeanReq();
        out.setTradeNo(idGeneratorService.snowFlake(BusinessIdEnum.退款编号.getCode()) + "");
        out.setReferenceNo(refundOrder.getPlatformOrderNo());
        out.setServiceType(ServiceTypeEnum.收单服务.getMsg());
        out.setMerchantId(refundOrder.getMerchantId());
        out.setConsumerId(refundOrder.getBuyerId());
        out.setAgentId(refundOrder.getAgentId());
        out.setPlatformId(refundOrder.getPlatformId());
        out.setProviderId(refundOrder.getProviderId());
        out.setChargeType(MerchantChargeTypeEnum.出账.getCode()); // 0:入账 1：出账
        out.setWalletType(MerchantWalletEnum.收单账户.getCode()); // 1:收单 2：收款
        out.setChargeEventType(MerchantChargeEventEnum.退款_收单_出.getCode()); //交易事件
        out.setCurrency(refundOrder.getMerchantCurrency());
        out.setMoney(refundOrder.getMerchantOrderAmount());
        out.setRemark(MerchantChargeEventEnum.退款_收单_出.getDesc());
        tradeBeanReqs.add(out);
        TradeBeanReq in = new TradeBeanReq();
        in.setTradeNo(idGeneratorService.snowFlake(BusinessIdEnum.退款编号.getCode()) + "");
        in.setReferenceNo(refundOrder.getPlatformOrderNo());
        in.setServiceType(ServiceTypeEnum.收单服务.getMsg());
        in.setMerchantId(refundOrder.getMerchantId());
        in.setConsumerId(refundOrder.getBuyerId());
        in.setAgentId(refundOrder.getAgentId());
        in.setPlatformId(refundOrder.getPlatformId());
        in.setProviderId(refundOrder.getProviderId());
        in.setChargeType(MerchantChargeTypeEnum.进账.getCode()); // 0:进账 1：出账
        in.setWalletType(MerchantWalletEnum.冻结账户.getCode()); // 1:收单 2：收款
        in.setChargeEventType(MerchantChargeEventEnum.退款_冻结_进.getCode()); //交易事件
        in.setCurrency(refundOrder.getMerchantCurrency());
        in.setMoney(refundOrder.getMerchantOrderAmount());
        in.setRemark(MerchantChargeEventEnum.退款_冻结_进.getDesc());
        tradeBeanReqs.add(in);

        //退款手续费

        JSONObject fixFeeFsonObject = refundOrder.getDownstreamFixedFee() == null ? new JSONObject() : JSON.parseObject(refundOrder.getDownstreamFixedFee());


        for (Map.Entry<String, Object> entry : fixFeeFsonObject.entrySet()) {

            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            BigDecimal money = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_DOWN);
            TradeBeanReq outFee = new TradeBeanReq();
            outFee.setTradeNo(idGeneratorService.snowFlake(BusinessIdEnum.退款编号.getCode()) + "");
            outFee.setReferenceNo(refundOrder.getPlatformOrderNo());
            outFee.setServiceType(ServiceTypeEnum.收单服务.getMsg());
            outFee.setMerchantId(refundOrder.getMerchantId());
            outFee.setConsumerId(refundOrder.getBuyerId());
            outFee.setAgentId(refundOrder.getAgentId());
            outFee.setPlatformId(refundOrder.getPlatformId());
            outFee.setProviderId(refundOrder.getProviderId());
            outFee.setChargeType(MerchantChargeTypeEnum.出账.getCode()); // 0:入账 1：出账
            outFee.setWalletType(MerchantWalletEnum.收单账户.getCode()); // 1:收单 2：收款
            outFee.setChargeEventType(MerchantChargeEventEnum.退款手续费_收单_出.getCode()); //交易事件
            outFee.setCurrency(currency);
            outFee.setMoney(money);
            outFee.setRemark(MerchantChargeEventEnum.退款手续费_收单_出.getDesc());
            tradeBeanReqs.add(outFee);
            TradeBeanReq inFee = new TradeBeanReq();
            inFee.setTradeNo(idGeneratorService.snowFlake(BusinessIdEnum.退款编号.getCode()) + "");
            inFee.setReferenceNo(refundOrder.getPlatformOrderNo());
            inFee.setServiceType(ServiceTypeEnum.收单服务.getMsg());
            inFee.setMerchantId(refundOrder.getMerchantId());
            inFee.setConsumerId(refundOrder.getBuyerId());
            inFee.setAgentId(refundOrder.getAgentId());
            inFee.setPlatformId(refundOrder.getPlatformId());
            inFee.setProviderId(refundOrder.getProviderId());
            inFee.setChargeType(MerchantChargeTypeEnum.进账.getCode()); // 0:进账 1：出账
            inFee.setWalletType(MerchantWalletEnum.冻结账户.getCode()); // 1:收单 2：收款
            inFee.setChargeEventType(MerchantChargeEventEnum.退款手续费_冻结_进.getCode()); //交易事件
            inFee.setCurrency(currency);
            inFee.setMoney(money);
            inFee.setRemark(MerchantChargeEventEnum.退款手续费_冻结_进.getDesc());
            tradeBeanReqs.add(inFee);

        }
        merchantWalletService.merchantMoneyChangeTransaction(tradeBeanReqs);
    }

    /**
     * 退款返还金额
     *
     * @param paymentOrderEntity
     */
    @Autowired
    PaymentOrderService paymentOrderService;

    public void refundBack(PaymentOrderEntity paymentOrderEntity) {

        List<MerchantChargeEntity> merchantChargeEntitys = merchantChargeService.getMerchantChargeByOrderNo(paymentOrderEntity.getPlatformOrderNo(), MerchantChargeTypeEnum.进账.getCode(), MerchantWalletEnum.冻结账户.getCode(), MerchantChargeEventEnum.退款_冻结_进.getCode());

//        List<MerchantChargeEntity> merchantChargeEntitys = merchantChargeService.getMerchantChargeByOrderNo(paymentOrderEntity.getPlatformOrderNo(), MerchantChargeTypeEnum.进账.getCode(), MerchantWalletEnum.冻结账户.getCode());
        List<TradeBeanReq> tradeBeanReqs = new ArrayList<>();
        for (MerchantChargeEntity merchantChargeEntity : merchantChargeEntitys) {
            TradeBeanReq out = new TradeBeanReq();
            out.setTradeNo(idGeneratorService.snowFlake(BusinessIdEnum.退款编号.getCode()) + "");
            out.setReferenceNo(merchantChargeEntity.getReferenceNo());
            out.setServiceType(ServiceTypeEnum.收单服务.getMsg());
            out.setMerchantId(merchantChargeEntity.getMerchantId());
            out.setConsumerId(merchantChargeEntity.getConsumerId());
            out.setAgentId(merchantChargeEntity.getAgentId());
            out.setPlatformId(merchantChargeEntity.getPlatformId());
            out.setProviderId(merchantChargeEntity.getProviderId());
            out.setChargeType(MerchantChargeTypeEnum.出账.getCode()); // 0:入账 1：出账
            out.setWalletType(MerchantWalletEnum.冻结账户.getCode()); // 1:收单 2：收款 3：充值 4：冻结
            out.setChargeEventType(MerchantChargeEventEnum.退款_冻结_出.getCode()); //交易事件
            out.setCurrency(merchantChargeEntity.getCurrency());
            out.setMoney(merchantChargeEntity.getImoney());
            out.setRemark(MerchantChargeEventEnum.退款_冻结_出.getDesc());
            tradeBeanReqs.add(out);
            TradeBeanReq in = new TradeBeanReq();
            in.setTradeNo(idGeneratorService.snowFlake(BusinessIdEnum.退款编号.getCode()) + "");
            in.setReferenceNo(merchantChargeEntity.getReferenceNo());
            in.setServiceType(ServiceTypeEnum.收单服务.getMsg());
            in.setMerchantId(merchantChargeEntity.getMerchantId());
            in.setConsumerId(merchantChargeEntity.getConsumerId());
            in.setAgentId(merchantChargeEntity.getAgentId());
            in.setPlatformId(merchantChargeEntity.getPlatformId());
            in.setProviderId(merchantChargeEntity.getProviderId());
            in.setChargeType(MerchantChargeTypeEnum.进账.getCode()); // 0:进账 1：出账
            in.setWalletType(MerchantWalletEnum.收单账户.getCode()); // 1:收单 2：收款
            in.setChargeEventType(MerchantChargeEventEnum.退款_收单_进.getCode()); //交易事件
            in.setCurrency(merchantChargeEntity.getCurrency());
            in.setMoney(merchantChargeEntity.getImoney());
            in.setRemark(MerchantChargeEventEnum.退款_收单_进.getDesc());
            tradeBeanReqs.add(in);
        }

        //退款手续费原路返还
        List<MerchantChargeEntity> merchantChargeEntityList = merchantChargeService.getMerchantChargeByOrderNo(paymentOrderEntity.getPlatformOrderNo(), MerchantChargeTypeEnum.进账.getCode(), MerchantWalletEnum.冻结账户.getCode(), MerchantChargeEventEnum.退款手续费_冻结_进.getCode());

        for (MerchantChargeEntity merchantChargeEntity : merchantChargeEntityList) {
            TradeBeanReq out = new TradeBeanReq();
            out.setTradeNo(idGeneratorService.snowFlake(BusinessIdEnum.退款编号.getCode()) + "");
            out.setReferenceNo(merchantChargeEntity.getReferenceNo());
            out.setServiceType(ServiceTypeEnum.收单服务.getMsg());
            out.setMerchantId(merchantChargeEntity.getMerchantId());
            out.setConsumerId(merchantChargeEntity.getConsumerId());
            out.setAgentId(merchantChargeEntity.getAgentId());
            out.setPlatformId(merchantChargeEntity.getPlatformId());
            out.setProviderId(merchantChargeEntity.getProviderId());
            out.setChargeType(MerchantChargeTypeEnum.出账.getCode()); // 0:入账 1：出账
            out.setWalletType(MerchantWalletEnum.冻结账户.getCode()); // 1:收单 2：收款 3：充值 4：冻结
            out.setChargeEventType(MerchantChargeEventEnum.退款手续费_冻结_出.getCode()); //交易事件
            out.setCurrency(merchantChargeEntity.getCurrency());
            out.setMoney(merchantChargeEntity.getImoney());
            out.setRemark(MerchantChargeEventEnum.退款手续费_冻结_出.getDesc());
            tradeBeanReqs.add(out);
            TradeBeanReq in = new TradeBeanReq();
            in.setTradeNo(idGeneratorService.snowFlake(BusinessIdEnum.退款编号.getCode()) + "");
            in.setReferenceNo(merchantChargeEntity.getReferenceNo());
            in.setServiceType(ServiceTypeEnum.收单服务.getMsg());
            in.setMerchantId(merchantChargeEntity.getMerchantId());
            in.setConsumerId(merchantChargeEntity.getConsumerId());
            in.setAgentId(merchantChargeEntity.getAgentId());
            in.setPlatformId(merchantChargeEntity.getPlatformId());
            in.setProviderId(merchantChargeEntity.getProviderId());
            in.setChargeType(MerchantChargeTypeEnum.进账.getCode()); // 0:进账 1：出账
            in.setWalletType(MerchantWalletEnum.收单账户.getCode()); // 1:收单 2：收款
            in.setChargeEventType(MerchantChargeEventEnum.退款手续费_收单_进.getCode()); //交易事件
            in.setCurrency(merchantChargeEntity.getCurrency());
            in.setMoney(merchantChargeEntity.getImoney());
            in.setRemark(MerchantChargeEventEnum.退款手续费_收单_进.getDesc());
            tradeBeanReqs.add(in);
        }
        merchantWalletService.merchantMoneyChangeTransaction(tradeBeanReqs);

    }


    public BigDecimal refundUpstreamEstimatedFee(PaymentOrderEntity paymentOrderEntity) {
        //TODO 计算预估上游游结算手续费
        // upstreamEstimatedFee = downstreamFixedFee + downstreamFee


        JSONObject fixFeeFsonObject = paymentOrderEntity.getUpstreamFixedFee() == null ? new JSONObject() : JSON.parseObject(paymentOrderEntity.getUpstreamFixedFee());
        JSONObject percentageFeeJsonObject = paymentOrderEntity.getUpstreamFee() == null ? new JSONObject() : JSON.parseObject(paymentOrderEntity.getUpstreamFee());
        BigDecimal totalFee = BigDecimal.ZERO;


        //固定手续费
        for (Map.Entry<String, Object> entry : fixFeeFsonObject.entrySet()) {
            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            BigDecimal fixFee = exchange(new BigDecimal(amount), currency, "USD");

            totalFee = totalFee.add(fixFee);
            log.info("上游-固定手续费 order {} amount {} currency {}  after {} total {}", paymentOrderEntity.getPlatformOrderNo(), amount, currency, fixFee, totalFee);
        }


        return totalFee;
    }

    public BigDecimal upstreamEstimatedFee(PaymentOrderEntity paymentOrderEntity) {
        //TODO 计算预估上游游结算手续费
        // upstreamEstimatedFee = downstreamFixedFee + downstreamFee


        JSONObject fixFeeFsonObject = paymentOrderEntity.getUpstreamFixedFee() == null ? new JSONObject() : JSON.parseObject(paymentOrderEntity.getUpstreamFixedFee());
        JSONObject percentageFeeJsonObject = paymentOrderEntity.getUpstreamFee() == null ? new JSONObject() : JSON.parseObject(paymentOrderEntity.getUpstreamFee());
        BigDecimal totalFee = BigDecimal.ZERO;


        //固定手续费
        for (Map.Entry<String, Object> entry : fixFeeFsonObject.entrySet()) {
            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            BigDecimal fixFee = exchange(new BigDecimal(amount), currency, "USD");

            totalFee = totalFee.add(fixFee);
            log.info("上游-固定手续费 order {} amount {} currency {}  after {} total {}", paymentOrderEntity.getPlatformOrderNo(), amount, currency, fixFee, totalFee);
        }

        //百分比手续费
        for (Map.Entry<String, Object> entry : percentageFeeJsonObject.entrySet()) {
            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            BigDecimal percentageFee = exchange(new BigDecimal(amount), paymentOrderEntity.getCurrency(), "USD");
            totalFee = totalFee.add(percentageFee);
            log.info("上游-百分比手续费 order{} amount {} currency {}  after {} total {}", paymentOrderEntity.getPlatformOrderNo(), amount, paymentOrderEntity.getCurrency(), percentageFee, totalFee);
        }


        return totalFee;
    }

    @Autowired
    HttpClientService httpClientService;

    public BigDecimal estimatedAmount(PaymentOrderEntity paymentOrderEntity) {
        if (paymentOrderEntity.getCurrency().equals("USD"))
            return paymentOrderEntity.getOrderAmount();

        String currencyPair = paymentOrderEntity.getCurrency() + "USD";
        if (map.get(currencyPair) != null)
            return map.get(currencyPair).multiply(paymentOrderEntity.getOrderAmount());

        String exRate = httpClientService.exchangeRate(paymentOrderEntity.getCurrency(), "USD");
        if (StringUtils.isNotBlank(exRate)) {
            JSONObject jsonObject = JSON.parseObject(exRate);
            JSONObject rate = jsonObject.getJSONObject("quotes");
            if (rate.get(currencyPair) == null) {
                log.info("货币对 {} 不支持", currencyPair);
            }
            map.put(currencyPair, rate.getBigDecimal(currencyPair).setScale(6, BigDecimal.ROUND_HALF_UP));
            return rate.getBigDecimal(currencyPair).multiply(paymentOrderEntity.getOrderAmount());
        }
        return null;

    }

    @Autowired
    PayinFixedService payinFixedService;
    @Autowired
    RefundFixedService refundFixedService;
    @Autowired
    ChangebackFixedService changebackFixedService;
    @Autowired
    PayinRateService payinRateService;

    private String getFixedFee(PaymentOrderEntity paymentOrderEntity, ChannelRatesEntity productChannelEntity) {
        BigDecimal totalFee = BigDecimal.ZERO;

        if (StringUtils.isBlank(productChannelEntity.getFixedFee()))
            return null;


        Map<String, String> payinFixedMap = payinFixedService.calculate(paymentOrderEntity.getCurrency(), paymentOrderEntity.getOrderAmount() + "", productChannelEntity.getFixedFee());

        if (payinFixedMap == null)
            return null;


        return JSON.toJSONString(payinFixedMap);
    }

    private String getFixedFee(PaymentOrderEntity paymentOrderEntity, ProductChannelEntity productChannelEntity) {
        BigDecimal totalFee = BigDecimal.ZERO;

        if (StringUtils.isBlank(productChannelEntity.getFixedFee()))
            return null;


        Map<String, String> payinFixedMap = payinFixedService.calculate(paymentOrderEntity.getCurrency(), paymentOrderEntity.getOrderAmount() + "", productChannelEntity.getFixedFee());

        if (payinFixedMap == null)
            return null;


        return JSON.toJSONString(payinFixedMap);
    }

    public String getRefundFixedFee(PaymentOrderEntity paymentOrderEntity, ProductChannelEntity productChannelEntity) {
        BigDecimal totalFee = BigDecimal.ZERO;

        if (StringUtils.isBlank(productChannelEntity.getRefundFee()))
            return null;


        Map<String, String> payinFixedMap = refundFixedService.calculate(paymentOrderEntity.getCurrency(), paymentOrderEntity.getOrderAmount() + "", productChannelEntity.getRefundFee());

        if (payinFixedMap == null)
            return null;


        return JSON.toJSONString(payinFixedMap);
    }

    public String getChangebackFixedFee(PaymentOrderEntity paymentOrderEntity, ProductChannelEntity productChannelEntity) {
        BigDecimal totalFee = BigDecimal.ZERO;

        if (StringUtils.isBlank(productChannelEntity.getChargebankFee()))
            return null;

        if (paymentOrderEntity.getCurrency() == null || paymentOrderEntity.getOrderAmount() == null)
            return null;

        Map<String, String> payinFixedMap = changebackFixedService.calculate(paymentOrderEntity.getCurrency(),
                paymentOrderEntity.getOrderAmount() + "",
                productChannelEntity.getChargebankFee());

        if (payinFixedMap == null)
            return null;


        return JSON.toJSONString(payinFixedMap);
    }

    public String getRefundFixedFee(PaymentOrderEntity paymentOrderEntity, ChannelRatesEntity productChannelEntity) {
        BigDecimal totalFee = BigDecimal.ZERO;

        if (StringUtils.isBlank(productChannelEntity.getRefundFee()))
            return null;


        Map<String, String> payinFixedMap = refundFixedService.calculate(paymentOrderEntity.getCurrency(), paymentOrderEntity.getOrderAmount() + "", productChannelEntity.getRefundFee());

        if (payinFixedMap == null)
            return null;


        return JSON.toJSONString(payinFixedMap);
    }

    public String getChangebackFixedFee(PaymentOrderEntity paymentOrderEntity, ChannelRatesEntity productChannelEntity) {
        BigDecimal totalFee = BigDecimal.ZERO;
        if (productChannelEntity == null)
            return null;
        if (StringUtils.isBlank(productChannelEntity.getChargebankFee()))
            return null;
        if (paymentOrderEntity.getCurrency() == null || paymentOrderEntity.getOrderAmount() == null)
            return null;

        Map<String, String> payinFixedMap = changebackFixedService.calculate(paymentOrderEntity.getCurrency(), paymentOrderEntity.getOrderAmount() + "", productChannelEntity.getChargebankFee());

        if (payinFixedMap == null)
            return null;


        return JSON.toJSONString(payinFixedMap);
    }

    private String percentageConfig(PaymentOrderEntity paymentOrderEntity, ProductChannelEntity productChannelEntity) {

        if (StringUtils.isBlank(productChannelEntity.getTransactionRate()))
            return null;

        Map<String, String> payinFixedMap = payinRateService.config(paymentOrderEntity.getCurrency(), paymentOrderEntity.getOrderAmount() + "", false, productChannelEntity.getTransactionRate());
        if (payinFixedMap == null)
            return null;

        return JSON.toJSONString(payinFixedMap);
    }


    private String percentageFee(PaymentOrderEntity paymentOrderEntity, ProductChannelEntity productChannelEntity) {
        BigDecimal totalFee = BigDecimal.ZERO;

        if (StringUtils.isBlank(productChannelEntity.getTransactionRate()))
            return null;

        Map<String, String> payinFixedMap = payinRateService.calculate(paymentOrderEntity.getCurrency(), paymentOrderEntity.getOrderAmount() + "", false, productChannelEntity.getTransactionRate());
        if (payinFixedMap == null)
            return null;

        return JSON.toJSONString(payinFixedMap);
    }

    private String percentageFee(PaymentOrderEntity paymentOrderEntity, ChannelRatesEntity productChannelEntity) {
        BigDecimal totalFee = BigDecimal.ZERO;

        if (StringUtils.isBlank(productChannelEntity.getTransactionRate()))
            return null;

        Map<String, String> payinFixedMap = payinRateService.calculate(paymentOrderEntity.getCurrency(), paymentOrderEntity.getOrderAmount() + "", false, productChannelEntity.getTransactionRate());
        if (payinFixedMap == null)
            return null;

        return JSON.toJSONString(payinFixedMap);
    }

    private String consumerCurrencyPercentageFee(PaymentOrderEntity paymentOrderEntity, ProductChannelEntity productChannelEntity) {
        BigDecimal totalFee = BigDecimal.ZERO;

        if (StringUtils.isBlank(productChannelEntity.getTransactionRate()))
            return null;

        Map<String, String> payinFixedMap = payinRateService.calculate(paymentOrderEntity.getMerchantCurrency(), paymentOrderEntity.getMerchantOrderAmount() + "", false, productChannelEntity.getTransactionRate());
        if (payinFixedMap == null)
            return null;

        return JSON.toJSONString(payinFixedMap);
    }


    public BigDecimal refundDownstreamEstimatedFee(PaymentOrderEntity paymentOrderEntity) {
        //TODO 计算预估下游结算手续费
        // downstreamEstimatedFee = downstreamFixedFee + downstreamFee + paymentTxMarkup


        JSONObject fixFeeFsonObject = paymentOrderEntity.getDownstreamFixedFee() == null ? new JSONObject() : JSON.parseObject(paymentOrderEntity.getDownstreamFixedFee());

        BigDecimal totalFee = BigDecimal.ZERO;


        //固定手续费
        for (Map.Entry<String, Object> entry : fixFeeFsonObject.entrySet()) {
            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            BigDecimal fixFee = exchange(new BigDecimal(amount), currency, "USD");
            totalFee = totalFee.add(fixFee);
            log.info("下游-固定手续费 order {} amount {} currency {}  after {} total {}", paymentOrderEntity.getPlatformOrderNo(), amount, currency, fixFee, totalFee);
        }

        return totalFee;
    }

    public BigDecimal downstreamEstimatedFee(PaymentOrderEntity paymentOrderEntity) {
        //TODO 计算预估下游结算手续费
        // downstreamEstimatedFee = downstreamFixedFee + downstreamFee + paymentTxMarkup


        JSONObject fixFeeFsonObject = paymentOrderEntity.getDownstreamFixedFee() == null ? new JSONObject() : JSON.parseObject(paymentOrderEntity.getDownstreamFixedFee());
        JSONObject percentageFeeJsonObject = paymentOrderEntity.getDownstreamFee() == null ? new JSONObject() : JSON.parseObject(paymentOrderEntity.getDownstreamFee());

        BigDecimal totalFee = BigDecimal.ZERO;


        //固定手续费
        for (Map.Entry<String, Object> entry : fixFeeFsonObject.entrySet()) {
            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            BigDecimal fixFee = exchange(new BigDecimal(amount), currency, "USD");
            totalFee = totalFee.add(fixFee);
            log.info("下游-固定手续费 order {} amount {} currency {}  after {} total {}", paymentOrderEntity.getPlatformOrderNo(), amount, currency, fixFee, totalFee);
        }

        //百分比手续费
        for (Map.Entry<String, Object> entry : percentageFeeJsonObject.entrySet()) {
            String amount = entry.getValue().toString().replaceAll("[^0-9.]", "");
            String currency = entry.getValue().toString().replaceAll("[^A-Za-z]", "");
            BigDecimal percentageFee = exchange(new BigDecimal(amount), paymentOrderEntity.getCurrency(), "USD");
            totalFee = totalFee.add(percentageFee);
            log.info("下游百分比手续费 order{} amount {} currency {}  after {} total {}", paymentOrderEntity.getPlatformOrderNo(), amount, paymentOrderEntity.getCurrency(), percentageFee, totalFee);
        }

        BigDecimal markup = exchange(paymentOrderEntity.getPaymentTxMarkup(), paymentOrderEntity.getCurrency(), "USD");
        totalFee = totalFee.add(markup == null ? BigDecimal.ZERO : markup);
        log.info("换汇加点 order{} amount {} currency {}  after {} total {}", paymentOrderEntity.getPlatformOrderNo(), paymentOrderEntity.getPaymentTxMarkup(), paymentOrderEntity.getCurrency(), markup, totalFee);
        return totalFee;
    }


    private String percentageConfig(PaymentOrderEntity paymentOrderEntity, ChannelRatesEntity productChannelEntity) {

        if (StringUtils.isBlank(productChannelEntity.getTransactionRate()))
            return null;

        Map<String, String> payinFixedMap = payinRateService.config(paymentOrderEntity.getCurrency(), paymentOrderEntity.getOrderAmount() + "", false, productChannelEntity.getTransactionRate());
        if (payinFixedMap == null)
            return null;

        return JSON.toJSONString(payinFixedMap);
    }

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
