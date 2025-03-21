package com.futurebank.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@ApiModel(value = "BalanceDetailsVo", description = "资金流水明细")
public class BalanceDetailsVo implements Serializable {
    private static final long serialVersionUID = 2868351971547761383L;

//    @ApiModelProperty(value = "收单通道提供方编号")
//    private String providerId;
//
//    @ApiModelProperty(value = "支付平台编号")
//    private String platformId;
//
//    @ApiModelProperty(value = "代理商编号")
//    private String agentId;
//
//    @ApiModelProperty(value = "子业务编号")
//    private String bizChildNo;
//
//    @ApiModelProperty(value = "统一业务编号")
//    private String bizUnifyNo;
//
//    @ApiModelProperty(value = "来源系统")
//    private String fromSystem;
//
//    @ApiModelProperty(value = "付款方客户编号")
//    private String payerCustomerNo;
//
//    @ApiModelProperty(value = "付款方货币")
//    private String payerCurrency;
//
//    @ApiModelProperty(value = "付款方金额")
//    private Double payerAmount;
//
//    @ApiModelProperty(value = "付款方手续费")
//    private Double payerAcquiringFee;
//
//    @ApiModelProperty(value = "付款方折扣费")
//    private Double payerDiscountFee;
//
//    @ApiModelProperty(value = "付款方费用描述")
//    private String payerFeeDescription;
//
//    @ApiModelProperty(value = "收款方客户编号")
//    private String recvCustomerNo;
//
//    @ApiModelProperty(value = "收款方货币")
//    private String recvCurrency;
//
//    @ApiModelProperty(value = "收款方金额")
//    private Double recvAmount;
//
//    @ApiModelProperty(value = "收款方手续费")
//    private Double recvAcquiringFee;
//
//    @ApiModelProperty(value = "收款方折扣费")
//    private Double recvDiscountFee;
//
//    @ApiModelProperty(value = "收款方费用描述")
//    private String recvFeeDescription;

    @ApiModelProperty(value = "支付类型")
    private String payType;

    @ApiModelProperty(value = "渠道代码")
    private String channelCode;

    @ApiModelProperty(value = "周期")
    private String period;

    @ApiModelProperty(value = "结算汇总文件名")
    private String settmentFileName;

    @ApiModelProperty(value = "结算汇总文件路径")
    private String settmentFilePath;

    @ApiModelProperty(value = "结算明细文件名称")
    private String settmentDetailsFileName;

    @ApiModelProperty(value = "结算明细文件路径")
    private String settmentDetailsFilePath;

    @ApiModelProperty(value = "凭证号")
    private String voucherNo;

    @ApiModelProperty(value = "业务类型")
    private String bizType;

    @ApiModelProperty(value = "业务类型")
    private String tradeParentCode;

    @ApiModelProperty(value = "业务类型名称")
    private String tradeName;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "交易类型代码")
    private String tradeTypeCode;

    @ApiModelProperty(value = "科目代码")
    private String subjectCode;

    @ApiModelProperty(value = "交易编号")
    private String tradeNo;

    @ApiModelProperty(value = "参考编号")
    private String referenceNo;

    @ApiModelProperty(value = "收费类型")
    private String chargeType;

    @ApiModelProperty(value = "服务类型")
    private String serviceType;

    @ApiModelProperty(value = "钱包类型")
    private String walletType;

    @ApiModelProperty(value = "收费事件类型")
    private String chargeEventType;

    @ApiModelProperty(value = "收费事件名称")
    private String chargeEventName;

    @ApiModelProperty(value = "旧总金额")
    private BigDecimal ioldMoneyTotal;

    @ApiModelProperty(value = "旧金额")
    private BigDecimal ioldMoney;

    @ApiModelProperty(value = "金额")
    private BigDecimal imoney;

    @ApiModelProperty(value = "货币类型")
    private String currency;

    @ApiModelProperty(value = "余额")
    private BigDecimal ibalance;

    @ApiModelProperty(value = "总余额")
    private BigDecimal ibalanceTotal;

    @ApiModelProperty(value = "备注")
    private String cmemo;

    @ApiModelProperty(value = "添加日期")
    private String cadddate;

    @ApiModelProperty(value = "添加日期")
    private String createdTime;

}
