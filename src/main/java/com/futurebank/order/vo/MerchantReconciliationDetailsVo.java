package com.futurebank.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "MerchantReconciliationDetailsVo", description = "商户对账明细")
public class MerchantReconciliationDetailsVo implements Serializable {
    private static final long serialVersionUID = -646533868059567729L;

    @ApiModelProperty(value = "Merchant Transaction ID (商户订单号)", example = "123456789")
    private String merchantTransactionID;

    @ApiModelProperty(value = "Platform Transaction ID (平台订单号)", example = "987654321")
    private String pspReference;

    @ApiModelProperty(value = "Payment Method (支付方式)", example = "Credit Card")
    private String paymentMethod;

    @ApiModelProperty(value = "Payment Method Type (支付方式类型)", example = "VISA")
    private String paymentMethodType;

    @ApiModelProperty(value = "Consumer Country Code (消费者所属国家)", example = "US")
    private String countryCode;

    @ApiModelProperty(value = "Transaction Amount (交易金额)", example = "100.00")
    private String transactionAmount;

    @ApiModelProperty(value = "Transaction Currency (交易币种)", example = "USD")
    private String transactionCurrency;

    @ApiModelProperty(value = "Processing Amount (请求金额)", example = "100.00")
    private String processingAmount;

    @ApiModelProperty(value = "Processing Currency (请求币种)", example = "USD")
    private String processingCurrency;

    @ApiModelProperty(value = "Transaction Timestamp (创建时间)", example = "2024-11-27T10:00:00Z")
    private String transactionTimestamp;

    @ApiModelProperty(value = "Succeeded Timestamp (成功时间)", example = "2024-11-27T10:05:00Z")
    private String succeededTimestamp;

    @ApiModelProperty(value = "Failed Timestamp (失败时间)", example = "2024-11-27T10:10:00Z")
    private String failedTimestamp;

    @ApiModelProperty(value = "Order Status (订单状态)", example = "COMPLETED")
    private String orderStatus;

    @ApiModelProperty(value = "Funds Status (资金状态)", example = "SETTLED")
    private String fundsStatus;

    @ApiModelProperty(value = "Gateway Fee Amount (支付网关费)", example = "1.50")
    private String gatewayFeeAmount;

    @ApiModelProperty(value = "Gateway Fee Currency (支付网关费币种)", example = "USD")
    private String gatewayFeeCurrency;

    @ApiModelProperty(value = "Percentage Fee Amount (百分比费用)", example = "2.5")
    private String percentageFeeAmount;

    @ApiModelProperty(value = "Percentage Fee Currency (百分比费用币种)", example = "USD")
    private String percentageFeeCurrency;

    @ApiModelProperty(value = "Fixed Fee Amount (固定手续费)", example = "0.30")
    private String fixedFeeAmount;

    @ApiModelProperty(value = "Fixed Fee Currency (固定手续费币种)", example = "USD")
    private String fixedFeeCurrency;

    @ApiModelProperty(value = "Net Fee Amount (手续费净额)", example = "1.80")
    private String netFeeAmount;

    @ApiModelProperty(value = "Net Fee Currency (手续费净额币种)", example = "USD")
    private String netFeeCurrency;

    @ApiModelProperty(value = "Settlement Timestamp (预计结算时间)", example = "2024-11-28T10:00:00Z")
    private String settlementTimestamp;

    @ApiModelProperty(value = "Additional Data (附加信息)", example = "Extra details about the transaction")
    private String additionalData;

    @ApiModelProperty(value = "Error Description (错误信息)", example = "Insufficient funds")
    private String errorDescription;

    @ApiModelProperty(value = "商户固定手续费", example = "0.325")
    private String downstreamFixedFee;

    @ApiModelProperty(value = "商户百分比手续费", example = "3.25%")
    private String downstreamTxRate;

    @ApiModelProperty(value = "商户百分比手续费", example = "3.25")
    private String  downstreamFee;

    @ApiModelProperty(value = "网关费", example = "3.25")
    private String downstreamGatewayFee;
}
