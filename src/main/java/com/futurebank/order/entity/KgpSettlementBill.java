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
@ApiModel(value = "KgpSettlementBill", description = "KGP结算账单明细")
public class KgpSettlementBill extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8946421423620827485L;

    @ApiModelProperty(value = "上游结算时间")
    private Date upstreamSettlementDate;

    @ApiModelProperty(value = "上游结算时间时区TimeZone")
    private String upstreamSettlementTimeZone;

    @ApiModelProperty(value = "合作伙伴付款ID")
    private String partnerPaymentId;

    @ApiModelProperty(value = "付款ID")
    private String paymentId;

    @ApiModelProperty(value = "金额货币代码（泰铢）")
    private String amountCurrencyCodeThb;

    @ApiModelProperty(value = "金额值（泰铢）")
    private BigDecimal amountValueThb;

    @ApiModelProperty(value = "汇率")
    private BigDecimal exchangeRate;

    @ApiModelProperty(value = "金额货币代码（美元）")
    private String amountCurrencyCodeUsd;

    @ApiModelProperty(value = "金额值（美元）")
    private BigDecimal amountValueUsd;

    @ApiModelProperty(value = "资金来源类型")
    private String sourceOfFundType;

    @ApiModelProperty(value = "资金来源状态")
    private String sourceOfFundStatus;

    @ApiModelProperty(value = "支付状态")
    private String paymentStatus;

    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "支付日期")
    private Date paymentDate;

    @ApiModelProperty(value = "支付时间")
    private Date paymentTime;

    @ApiModelProperty(value = "支付处理日期")
    private Date paymentProcessingDate;

    @ApiModelProperty(value = "更新日期")
    private Date updateDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "支付状态")
    private String payoutStatus;

    @ApiModelProperty(value = "退款标志")
    private String refundFlag;

    @ApiModelProperty(value = "费用货币代码（泰铢）")
    private String feeCurrencyCodeThb;

    @ApiModelProperty(value = "费用值（泰铢）")
    private BigDecimal feeValueThb;

    @ApiModelProperty(value = "费用增值税（泰铢）")
    private BigDecimal feeVatThb;

    @ApiModelProperty(value = "费用预扣税（泰铢）")
    private BigDecimal feeWhtThb;

    @ApiModelProperty(value = "总结算金额（泰铢）")
    private BigDecimal totalSettlementThb;

    @ApiModelProperty(value = "费用货币代码（美元）")
    private String feeCurrencyCodeUsd;

    @ApiModelProperty(value = "费用值（美元）")
    private BigDecimal feeValueUsd;

    @ApiModelProperty(value = "费用增值税（美元）")
    private BigDecimal feeVatUsd;

    @ApiModelProperty(value = "费用预扣税（美元）")
    private BigDecimal feeWhtUsd;

    @ApiModelProperty(value = "总结算金额（美元）")
    private BigDecimal totalSettlementUsd;

    @ApiModelProperty(value = "退款状态")
    private String chargebackStatus;

    @ApiModelProperty(value = "支付批次ID")
    private String payoutBatchId;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "合作伙伴订单ID")
    private String partnerOrderId;

    @ApiModelProperty(value = "合作伙伴商户ID")
    private String partnerMerchantId;

    @ApiModelProperty(value = "合作伙伴商店ID")
    private String partnerShopId;

}
