package com.futurebank.order.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter;
import lombok.Setter;


/**
 * (ReconciliationKlasha)实体类
 *
 * @author ben
 * @since 2024-11-05 16:55:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ReconciliationKlasha", description = "$tableInfo.comment")
public class ReconciliationKlashaEntity implements Serializable {
    private static final long serialVersionUID = 205779097230148010L;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private String amount;

    /**
     * 商家ID
     */
    @ApiModelProperty(value = "商家ID")
    private String businessid;

    /**
     * 外币
     */
    @ApiModelProperty(value = "外币")
    private String foreigncurrency;

    /**
     * 源货币
     */
    @ApiModelProperty(value = "源货币")
    private String sourcecurrency;

    /**
     * 拆分类型
     */
    @ApiModelProperty(value = "拆分类型")
    private String splittype;

    /**
     * 外币金额
     */
    @ApiModelProperty(value = "外币金额")
    private String foreignamount;

    /**
     * 已结算源金额
     */
    @ApiModelProperty(value = "已结算源金额")
    private String settledsourceamount;

    /**
     * 已结算状态
     */
    @ApiModelProperty(value = "已结算状态")
    private String settledstatus;

    /**
     * 货币
     */
    @ApiModelProperty(value = "货币")
    private String currency;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private String id;

    /**
     * 交易订单ID
     */
    @ApiModelProperty(value = "交易订单ID")
    private String transactionOrderid;

    /**
     * 交易费用
     */
    @ApiModelProperty(value = "交易费用")
    private String transactionFee;

    /**
     * 交易渠道
     */
    @ApiModelProperty(value = "交易渠道")
    private String transactionChannel;

    /**
     * 交易收费金额
     */
    @ApiModelProperty(value = "交易收费金额")
    private String transactionChargedamount;

    /**
     * 回调地址
     */
    @ApiModelProperty(value = "回调地址")
    private String transactionCallback;

    /**
     * 源货币
     */
    @ApiModelProperty(value = "源货币")
    private String transactionSourcecurrency;

    /**
     * 结算汇率
     */
    @ApiModelProperty(value = "结算汇率")
    private String transactionSettlementrate;

    /**
     * 交易参考
     */
    @ApiModelProperty(value = "交易参考")
    private String transactionTnxref;

    /**
     * 支付类型
     */
    @ApiModelProperty(value = "支付类型")
    private String transactionPaymenttype;

    /**
     * 是否发送Webhook
     */
    @ApiModelProperty(value = "是否发送Webhook")
    private String transactionWebhooksent;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String transactionCreatedat;

    /**
     * 交易参考ID
     */
    @ApiModelProperty(value = "交易参考ID")
    private String transactionKtnxref;

    /**
     * 网关代码
     */
    @ApiModelProperty(value = "网关代码")
    private String transactionGatewaycode;

    /**
     * 汇率
     */
    @ApiModelProperty(value = "汇率")
    private String transactionRate;

    /**
     * 提供商
     */
    @ApiModelProperty(value = "提供商")
    private String transactionProvider;

    /**
     * 源剩余金额
     */
    @ApiModelProperty(value = "源剩余金额")
    private String transactionSourceremainingamount;

    /**
     * 结算参考
     */
    @ApiModelProperty(value = "结算参考")
    private String transactionSettlementreference;

    /**
     * 额外信息
     */
    @ApiModelProperty(value = "额外信息")
    private String transactionExtra;

    /**
     * 源金额
     */
    @ApiModelProperty(value = "源金额")
    private String transactionSourceamount;

    /**
     * 交易货币
     */
    @ApiModelProperty(value = "交易货币")
    private String transactionCurrency;

    /**
     * 交易ID
     */
    @ApiModelProperty(value = "交易ID")
    private String transactionId;

    /**
     * 最后更新时间
     */
    @ApiModelProperty(value = "最后更新时间")
    private String transactionUpdatedat;

    /**
     * 源货币费用
     */
    @ApiModelProperty(value = "源货币费用")
    private String transactionSourcecurrencyfee;

    /**
     * 交易金额
     */
    @ApiModelProperty(value = "交易金额")
    private String transactionAmount;

    /**
     * 支付计划
     */
    @ApiModelProperty(value = "支付计划")
    private String transactionPaymentplan;

    /**
     * 商家
     */
    @ApiModelProperty(value = "商家")
    private String transactionBusiness;

    /**
     * IP地址
     */
    @ApiModelProperty(value = "IP地址")
    private String transactionIp;

    /**
     * 增值税
     */
    @ApiModelProperty(value = "增值税")
    private String transactionVat;

    /**
     * 消息
     */
    @ApiModelProperty(value = "消息")
    private String transactionMessage;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String transactionUserid;

    /**
     * 已结算金额
     */
    @ApiModelProperty(value = "已结算金额")
    private String transactionSettledamount;

    /**
     * 已结算货币
     */
    @ApiModelProperty(value = "已结算货币")
    private String transactionSettledcurrency;

    /**
     * 使用的卡
     */
    @ApiModelProperty(value = "使用的卡")
    private String transactionCardused;

    /**
     * 网关参考
     */
    @ApiModelProperty(value = "网关参考")
    private String transactionGateref;

    /**
     * 拆分状态
     */
    @ApiModelProperty(value = "拆分状态")
    private String transactionSplitstatus;

    /**
     * 叙述
     */
    @ApiModelProperty(value = "叙述")
    private String transactionNarration;

    /**
     * 失败原因
     */
    @ApiModelProperty(value = "失败原因")
    private String transactionFailurereason;

    /**
     * 是否记住我
     */
    @ApiModelProperty(value = "是否记住我")
    private String transactionRememberme;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String transactionStatus;

    /**
     * 客户创建时间
     */
    @ApiModelProperty(value = "客户创建时间")
    private String customerCreatedat;

    /**
     * 客户电话
     */
    @ApiModelProperty(value = "客户电话")
    private String customerPhone;

    /**
     * 客户姓名
     */
    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户ID")
    private String customerId;

    /**
     * 客户邮箱
     */
    @ApiModelProperty(value = "客户邮箱")
    private String customerEmail;

    /**
     * 客户更新时间
     */
    @ApiModelProperty(value = "客户更新时间")
    private String customerUpdatedat;

    /**
     * 交易ID
     */
    @ApiModelProperty(value = "交易ID")
    private String transactionTxnid;

    /**
     * 最后更新时间
     */
    @ApiModelProperty(value = "最后更新时间")
    private String updatedat;

    /**
     * 是否已处理
     */
    @ApiModelProperty(value = "是否已处理")
    private Integer isProcessed;

    /**
     * 解决代码冲突
     */
    @ApiModelProperty(value = "支付状态")
    private String paymentStatus;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long rid;
}
