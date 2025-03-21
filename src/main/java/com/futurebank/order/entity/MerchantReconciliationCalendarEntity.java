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
@ApiModel(value = "MerchantReconciliationCalendarEntity", description = "商户对账汇总数据")
public class MerchantReconciliationCalendarEntity extends BaseEntity implements Serializable {

    /**
     * 渠道信息
     */
    @ApiModelProperty(value = "Channel information ID")
    private Long providerId;

    /**
     * 渠道名称
     */
    @ApiModelProperty(value = "Channel name")
    private String providerName;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "Merchant ID")
    private Long merchantId;

    /**
     * 交易货币
     */
    @ApiModelProperty(value = "Transaction currency")
    private String currency;

    /**
     * 商户结算日期
     */
    @ApiModelProperty(value = "Merchant settlement date")
    private String estimatedSettlementDate;

    /**
     * 对账日期
     */
    @ApiModelProperty(value = "Reconciliation date")
    private String reconDate;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "Payment method")
    private String paymentMethod;

    /**
     * 交易金额
     */
    @ApiModelProperty(value = "Transaction amount")
    private BigDecimal transactionAmount;

    /**
     * 交易笔数
     */
    @ApiModelProperty(value = "Number of transactions")
    private Integer transactionCount;

    /**
     * 成功金额
     */
    @ApiModelProperty(value = "Successful transaction amount")
    private BigDecimal successAmount;

    /**
     * 成功笔数
     */
    @ApiModelProperty(value = "Number of successful transactions")
    private Integer successCount;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "Refund amount")
    private BigDecimal refundAmount;

    /**
     * 退款笔数
     */
    @ApiModelProperty(value = "Number of refunds")
    private Integer refundCount;

    /**
     * 拒付金额
     */
    @ApiModelProperty(value = "Rejected transaction amount")
    private BigDecimal rejectedAmount;

    /**
     * 拒付笔数
     */
    @ApiModelProperty(value = "Number of rejected transactions")
    private Integer rejectedCount;

    /**
     * 网关费用
     */
    @ApiModelProperty(value = "Gateway fee")
    private BigDecimal networkFee;

    @ApiModelProperty(value = "Gateway fee currency")
    private String networkFeeCcy;

    /**
     * 固定费用
     */
    @ApiModelProperty(value = "Fixed fee")
    private BigDecimal fixedFee;

    @ApiModelProperty(value = "Gateway fee currency")
    private String fixedFeeCcy;


    /**
     * 百分比费用
     */
    @ApiModelProperty(value = "Percentage-based fee")
    private BigDecimal percentageFee;

    @ApiModelProperty(value = "Gateway fee currency")
    private String percentageFeeCcy;

    /**
     * 退款手续费
     */
    @ApiModelProperty(value = "Refund handling fee")
    private BigDecimal refundFee;

    @ApiModelProperty(value = "Refund handling fee")
    private String refundFeeCcy;

    /**
     * 拒付手续费
     */
    @ApiModelProperty(value = "Rejection handling fee")
    private BigDecimal rejectedFee;

    @ApiModelProperty(value = "Rejection handling fee")
    private String rejectedFeeCcy;


}
