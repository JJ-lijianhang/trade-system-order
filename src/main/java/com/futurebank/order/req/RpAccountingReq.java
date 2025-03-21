package com.futurebank.order.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ApiModel(value = "RpAccountingReq", description = "统一入账请求参数")
public class RpAccountingReq implements Serializable {
    private static final long serialVersionUID = -5207470535228322785L;

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
    @NotNull
    @ApiModelProperty(value = "统一订单号",required = true, example = "外围系统业务单号必填")
    private String bizUnifyNo;

    /**
     * 业务类型
     */
    @NotNull
    @ApiModelProperty(value = "业务类型")
    private String bizType;

    /**
     * 系统来源
     */
    @NotNull
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
    @ApiModelProperty(value = "付方信息-金额",example = "0.000001")
    private String payerAmount;

    /**
     * 付方信息-货币类型
     */
    @ApiModelProperty(value = "付方信息-货币类型")
    private String payerCurrency;


    /**
     * 付方信息-固定手续费
     */
    @ApiModelProperty(value = "付方信息-固定手续费",example = "0.000001")
    private String payerAcquiringFee;

    /**
     * 付方信息-百分百比手续费
     */
    @ApiModelProperty(value = "付方信息-百分百比手续费",example = "0.000001")
    private String payerDiscountFee;

    /**
     * 付方信息-百分百比手续费
     */
    @ApiModelProperty(value = "付方信息-一次性手续费",example = "0.000001")
    private String payerOneTimesFee;


    /**
     * 付方信息-百分百比手续费
     */
    @ApiModelProperty(value = "付方信息-手续费说明",example = "手续费说明")
    private String payerFeeDescription;

    /**
     * 收方信息
     */
    @ApiModelProperty(value = "收方信息")
    private Long recvCustomerNo;

    /**
     * 收方信息-金额
     */
    @ApiModelProperty(value = "收方信息-金额",example = "0.000001")
    private String recvAmount;

    /**
     * 收方信息-货币类型
     */
    @ApiModelProperty(value = "收方信息-货币类型")
    private String recvCurrency;

    /**
     * 收方信息-固定手续费
     */
    @ApiModelProperty(value = "收方信息-固定手续费",example = "0.000001")
    private String recvAcquiringFee;

    /**
     * 收方信息-百分百比手续费
     */
    @ApiModelProperty(value = "收方信息-百分百比手续费",example = "0.000001")
    private String recvDiscountFee;


    /**
     * 收方信息-百分百比手续费
     */
    @ApiModelProperty(value = "收方信息-一次性手续费",example = "0.000001")
    private String recvOneTimesFee;

    /**
     * 收方信息-百分百比手续费
     */
    @ApiModelProperty(value = "收方信息-网关费",example = "0.000001")
    private String recvGatewayFee;

    /**
     * 收方信息-百分百比手续费
     */
    @ApiModelProperty(value = "收方信息-手续费说明",example = "手续费说明")
    private String recvFeeDescription;

    /**
     * 第三方信息
     */
    @ApiModelProperty(value = "第三方信息")
    private Long threeCustomerNo;

    /**
     * 第三方信息-金额
     */
    @ApiModelProperty(value = "第三方信息-金额",example = "0.000001")
    private String threeAmount;

    /**
     * 第三方信息-货币类型
     */
    @ApiModelProperty(value = "第三方信息-货币类型")
    private String threeCurrency;

    /**
     * 第三方信息-固定手续费
     */
    @ApiModelProperty(value = "第三方信息-固定手续费",example = "0.000001")
    private String threeAcquiringFee;

    /**
     * 第三方信息-百分百比手续费
     */
    @ApiModelProperty(value = "第三方信息-百分百比手续费",example = "0.000001")
    private String threeDiscountFee;

    /**
     *第三方信息-手续费说明
     */
    @ApiModelProperty(value = "第三方信息-手续费说明",example = "手续费说明")
    private String threeFeeDescription;

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
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String remark;
}
