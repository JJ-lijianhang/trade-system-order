package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantSettmEntity", description = "商户对账查詢")
public class MerchantSettmEntity implements Serializable {
    private static final long serialVersionUID = 7261550887253619717L;

    /**
     * 账户号
     */
    private Long merchantId;

    /**
     * 结算期数
     */
    private String period;

    /**
     * 金额类型
     */
    private String feeType;


    /**
     * 币种
     */
    private String currency;

    /**
     * 汇总数据
     */
    private BigDecimal totalVolume;

    /**
     * 汇总数据
     */
    private BigDecimal totalDueVolume;

    /**
     * 换汇目标货币
     */
    private String tiggerCurrency;

    /**
     * 换汇汇率
     */
    private BigDecimal exchangeRate;

    /**
     * 换汇汇率
     */
    private BigDecimal markupRate;

    /**
     * 换汇金额
     */
    private BigDecimal exchangeAmount;

    /**
     * 换汇手续费
     */
    private BigDecimal markupFee;


    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 支付方式类型
     */
    private String paymentMethodType;

    /**
     * 国家代码
     */
    private String countryCode;

    /**
     * 商户交易币种
     */
    private String merchantOrderAmount;

    /**
     * 交易笔数
     */
    private int eventCount;

    /**
     * 手续费
     */
    private String downstreamTxRate;

    /**
     * 手续费固定
     */
    private String  downstreamFixedFee;

    /**
     * 渠道商List
     */
    private List<Long> providerIds;

}
