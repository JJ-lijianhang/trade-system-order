package com.futurebank.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MerchantRefundDetailsVo", description = "商户退款明细")
public class MerchantRefundDetailsVo {
    @ApiModelProperty(value = "Merchant Refund ID (商户退款订单号)", example = "REF123456789")
    private String merchantRefundID;

    @ApiModelProperty(value = "Platform Refund ID (平台退款订单号)", example = "PSP987654321")
    private String pspRefundID;

    @ApiModelProperty(value = "Payment Method (支付方式)", example = "Credit Card")
    private String paymentMethod;

    @ApiModelProperty(value = "Payment Method Type (支付方式类型)", example = "VISA")
    private String paymentMethodType;

    @ApiModelProperty(value = "Consumer Country Code (消费者所属国家)", example = "US")
    private String countryCode;

    @ApiModelProperty(value = "Refund Amount (退款金额)", example = "50.00")
    private String refundAmount;

    @ApiModelProperty(value = "Refund Currency (退款币种)", example = "USD")
    private String refundCurrency;

    @ApiModelProperty(value = "Refund Fee Amount (退款手续费金额)", example = "2.00")
    private String refundFeeAmount;

    @ApiModelProperty(value = "Refund Fee Currency (退款手续费币种)", example = "USD")
    private String refundFeeCurrency;

    @ApiModelProperty(value = "Refund State (退款类型)", example = "PARTIAL")
    private String refundState;

    @ApiModelProperty(value = "Refund Funds State (退款资金状态)", example = "SETTLED")
    private String fundsState;

    @ApiModelProperty(value = "Settlement Timestamp (预计结算时间)", example = "2024-11-28T10:00:00Z")
    private String settlementTimestamp;

    @ApiModelProperty(value = "Refund Succeeded Timestamp (退款成功时间戳)", example = "2024-11-27T15:00:00Z")
    private String refundSucceededTimestamp;

    @ApiModelProperty(value = "Refund Failed Timestamp (退款失败时间戳)", example = "2024-11-27T15:05:00Z")
    private String refundFailedTimestamp;

    @ApiModelProperty(value = "Funds Sent Timestamp (资金发送时间戳)", example = "2024-11-27T16:00:00Z")
    private String fundsSentTimestamp;

    @ApiModelProperty(value = "Funds Rejected Timestamp (资金拒绝时间戳)", example = "2024-11-27T16:05:00Z")
    private String fundsRejectedTimestamp;

    @ApiModelProperty(value = "Refund Error Description (退款错误描述)", example = "Invalid account details")
    private String refundErrorDescription;

    @ApiModelProperty(value = "商户固定手续费", example = "0.325")
    private String downstreamFixedFee;
}
