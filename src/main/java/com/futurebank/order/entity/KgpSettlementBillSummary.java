package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "KgpSettlementBillSummary", description = "KGP结算账单明细汇总")
public class KgpSettlementBillSummary extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -7343740440334132323L;

    @ApiModelProperty(value = "付款来源合作伙伴ID")
    private String settlementFrom;

    @ApiModelProperty(value = "付款目标合作伙伴ID")
    private String settlementTo;

    @ApiModelProperty(value = "结算日期")
    private String settlementDate;

    @ApiModelProperty(value = "结算日期时区")
    private String settlementTimeZone;

    @ApiModelProperty(value = "付款日期范围")
    private String paymentDateRange;

    @ApiModelProperty(value = "付款开始日期")
    private Date paymentDateStart;

    @ApiModelProperty(value = "付款结束日期")
    private Date paymentDateEnd;

    @ApiModelProperty(value = "货币代码")
    private String currency;

    @ApiModelProperty(value = "汇率")
    private BigDecimal exchangeRate;

    @ApiModelProperty(value = "货币对")
    private String currencyPair;

    @ApiModelProperty(value = "付款收入")
    private BigDecimal paymentIncomeThb;

    @ApiModelProperty(value = "付款手续费")
    private BigDecimal paymentFeeThb;

    @ApiModelProperty(value = "手动退款金额")
    private BigDecimal manualRefundThb;

    @ApiModelProperty(value = "付款手续费")
    private BigDecimal payoutFeeThb;

    @ApiModelProperty(value = "净结算金额")
    private BigDecimal netSettlementAmountThb;

    @ApiModelProperty(value = "付款收入")
    private BigDecimal paymentIncomeUsd;

    @ApiModelProperty(value = "付款手续费")
    private BigDecimal paymentFeeUsd;

    @ApiModelProperty(value = "手动退款金额")
    private BigDecimal manualRefundUsd;

    @ApiModelProperty(value = "付款手续费")
    private BigDecimal payoutFeeUsd;

    @ApiModelProperty(value = "净结算金额")
    private BigDecimal netSettlementAmountUsd;

}
