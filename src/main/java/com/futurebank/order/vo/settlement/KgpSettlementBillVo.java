package com.futurebank.order.vo.settlement;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class KgpSettlementBillVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "合作伙伴付款ID")
    private String partnerPaymentID;

    @ApiModelProperty(value = "付款ID")
    private String paymentID;

    @ApiModelProperty(value = "金额货币代码（泰铢）")
    private String amountCurrencyCodeTHB;

    @ApiModelProperty(value = "金额值（泰铢）")
    private String amountValueTHB;

    @ApiModelProperty(value = "汇率")
    private String exchangeRate;

    @ApiModelProperty(value = "金额货币代码（美元）")
    private String amountCurrencyCodeUSD;

    @ApiModelProperty(value = "金额值（美元）")
    private String amountValueUSD;

    @ApiModelProperty(value = "资金来源类型")
    private String sourceOfFundType;

    @ApiModelProperty(value = "资金来源状态")
    private String sourceOfFundStatus;

    @ApiModelProperty(value = "支付状态")
    private String paymentStatus;

    @ApiModelProperty(value = "创建日期")
    private String createDate;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "支付日期")
    private String paymentDate;

    @ApiModelProperty(value = "支付时间")
    private String paymentTime;

    @ApiModelProperty(value = "支付处理日期")
    private String paymentProcessingDate;

    @ApiModelProperty(value = "更新日期")
    private String updateDate;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty(value = "支付状态")
    private String payoutStatus;

    @ApiModelProperty(value = "退款标志")
    private String refundFlag;

    @ApiModelProperty(value = "费用货币代码（泰铢）")
    private String feeCurrencyCodeTHB;

    @ApiModelProperty(value = "费用值（泰铢）")
    private String feeValueTHB;

    @ApiModelProperty(value = "费用增值税（泰铢）")
    private String feeVATTHB;

    @ApiModelProperty(value = "费用预扣税（泰铢）")
    private String feeWHTTHB;

    @ApiModelProperty(value = "总结算金额（泰铢）")
    private String totalSettlementTHB;

    @ApiModelProperty(value = "费用货币代码（美元）")
    private String feeCurrencyCodeUSD;

    @ApiModelProperty(value = "费用值（美元）")
    private String feeValueUSD;

    @ApiModelProperty(value = "费用增值税（美元）")
    private String feeVATUSD;

    @ApiModelProperty(value = "费用预扣税（美元）")
    private String feeWHTUSD;

    @ApiModelProperty(value = "总结算金额（美元）")
    private String totalSettlementUSD;

    @ApiModelProperty(value = "退款状态")
    private String chargebackStatus;

    @ApiModelProperty(value = "支付批次ID")
    private String payoutBatchID;

    @ApiModelProperty(value = "项目ID")
    private String projectID;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "合作伙伴订单ID")
    private String partnerOrderID;

    @ApiModelProperty(value = "合作伙伴商户ID")
    private String partnerMerchantID;

    @ApiModelProperty(value = "合作伙伴商店ID")
    private String partnerShopID;
}
