package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AccountBizTradeDetails", description = "账户系统-外围系统交易流水")
public class AccountBizTradeDetailsEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -8668777839417494720L;

    /**
     * 收单通道提供方编号
     */
    @ApiModelProperty(value = "收单通道提供方编号")
    private Long providerId;

    /**
     * 支付平台编号
     */
    @ApiModelProperty(value = "支付平台编号")
    private Long platformId;

    /**
     * 代理商编号
     */
    @ApiModelProperty(value = "代理商编号")
    private Long agentId;

//    /**
//     * 交易货币
//     */
//    @ApiModelProperty(value = "交易货币")
//    private String currency;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String bizNo;

    /**
     * 子业务订单号
     */
    @ApiModelProperty(value = "子业务订单号")
    private String bizChildNo;

    /**
     * 统一订单号
     */
    @ApiModelProperty(value = "统一订单号")
    private String bizUnifyNo;

    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    private String bizType;

    /**
     * 系统来源
     */
    @ApiModelProperty(value = "系统来源")
    private String fromSystem;

    /**
     * 付方信息-客户号
     */
    @ApiModelProperty(value = "付方信息-客户号")
    private Long payerCustomerNo;

    /**
     * 付方信息-金额
     */
    @ApiModelProperty(value = "付方信息-金额")
    private BigDecimal payerAmount;

    /**
     * 付方信息-金额
     */
    @ApiModelProperty(value = "付方信息-货币类型")
    private String payerCurrency;

    /**
     * 付方信息-固定手续费
     */
    @ApiModelProperty(value = "付方信息-固定手续费")
    private BigDecimal payerAcquiringFee;

    /**
     * 付方信息-百分百比手续费
     */
    @ApiModelProperty(value = "付方信息-百分百比手续费")
    private BigDecimal payerDiscountFee;

    /**
     * 付方信息-一次性费用
     */
    @ApiModelProperty(value = "付方信息-一次性费用")
    private BigDecimal payerOneTimesFee;

    /**
     *第三方信息-手续费说明
     */
    @ApiModelProperty(value = "第三方信息-手续费说明",example = "手续费说明")
    private String payerFeeDescription;

//    /**
//     * 付方信息-其他费用
//     */
//    @ApiModelProperty(value = "付方信息-其他费用")
//    private BigDecimal payerExtFee1;
//
//    /**
//     * 付方信息-其他费用
//     */
//    @ApiModelProperty(value = "付方信息-其他费用")
//    private BigDecimal payerExtFee2;
//
//    /**
//     * 付方信息-其他费用
//     */
//    @ApiModelProperty(value = "付方信息-其他费用")
//    private BigDecimal payerExtFee3;

    /**
     * 收方信息
     */
    @ApiModelProperty(value = "收方信息")
    private Long recvCustomerNo;

    /**
     * 收方信息-金额
     */
    @ApiModelProperty(value = "收方信息-金额")
    private BigDecimal recvAmount;

    /**
     * 收方信息-金额
     */
    @ApiModelProperty(value = "收方信息-货币类型")
    private String recvCurrency;


    /**
     * 收方信息-固定手续费
     */
    @ApiModelProperty(value = "收方信息-固定手续费")
    private BigDecimal recvAcquiringFee;

    /**
     * 收方信息-百分百比手续费
     */
    @ApiModelProperty(value = "收方信息-百分百比手续费")
    private BigDecimal recvDiscountFee;

    /**
     * 付方信息-一次性费用
     */
    @ApiModelProperty(value = "付方信息-一次性费用")
    private BigDecimal recvOneTimesFee;


    /**
     * 收方信息-百分百比手续费
     */
    @ApiModelProperty(value = "收方信息-网关费",example = "0.000001")
    private BigDecimal recvGatewayFee;


    /**
     * 收方信息-百分百比手续费
     */
    @ApiModelProperty(value = "收方信息-手续费说明",example = "手续费说明")
    private String recvFeeDescription;

//    /**
//     * 其他费用
//     */
//    @ApiModelProperty(value = "其他费用")
//    private BigDecimal recvExtFee1;
//
//    /**
//     * 其他费用
//     */
//    @ApiModelProperty(value = "其他费用")
//    private BigDecimal recvExtFee2;
//
//    /**
//     * 其他费用
//     */
//    @ApiModelProperty(value = "其他费用")
//    private BigDecimal recvExtFee3;

    /**
     * 第三方信息
     */
    @ApiModelProperty(value = "第三方信息")
    private Long threeCustomerNo;

    /**
     * 第三方信息-金额
     */
    @ApiModelProperty(value = "第三方信息-金额")
    private BigDecimal threeAmount;


    /**
     * 第三方信息-金额
     */
    @ApiModelProperty(value = "第三方信息-货币类型")
    private String threeCurrency;


    /**
     * 第三方信息-固定手续费
     */
    @ApiModelProperty(value = "第三方信息-固定手续费")
    private BigDecimal threeAcquiringFee;

    /**
     * 第三方信息-百分百比手续费
     */
    @ApiModelProperty(value = "第三方信息-百分百比手续费")
    private BigDecimal threeDiscountFee;

    /**
     *第三方信息-手续费说明
     */
    @ApiModelProperty(value = "第三方信息-手续费说明",example = "手续费说明")
    private String threeFeeDescription;

//    /**
//     * 其他费用
//     */
//    @ApiModelProperty(value = "其他费用")
//    private BigDecimal threeExtFee1;
//
//    /**
//     * 其他费用
//     */
//    @ApiModelProperty(value = "其他费用")
//    private BigDecimal threeExtFee2;
//
//    /**
//     * 其他费用
//     */
//    @ApiModelProperty(value = "其他费用")
//    private BigDecimal threeExtFee3;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String payType;

    /**
     * 支付渠道
     */
    @ApiModelProperty(value = "支付渠道")
    private String channelCode;

    /**
     * 账期
     */
    @ApiModelProperty(value = "账期")
    private String period;

     @ApiModelProperty(value = "结算汇总文件名")
    private String settmentFileName;

    @ApiModelProperty(value = "结算汇总文件路径")
    private String settmentFilePath;

    @ApiModelProperty(value = "结算明细文件名称")
    private String settmentDetailsFileName;

    @ApiModelProperty(value = "结算明细文件路径")
    private String settmentDetailsFilePath;

    /**
     * 凭证号
     */
    @ApiModelProperty(value = "凭证号")
    private String voucherNo;

    /**
     * 冲账标志
     */
    @ApiModelProperty(value = "冲账标志")
    private String reverseFlag;

    /**
     * 冲账时间
     */
    @ApiModelProperty(value = "冲账时间")
    private String reverseTime;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String remark;

}
