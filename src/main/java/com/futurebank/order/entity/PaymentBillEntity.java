package com.futurebank.order.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter;
import lombok.Setter;


/**
 * 支付账单表(PaymentBill)实体类
 *
 * @author ben
 * @since 2024-05-25 11:43:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentBill", description = "支付账单表")
public class PaymentBillEntity implements Serializable {
    private static final long serialVersionUID = 997433135239949389L;

    /**
     * 唯一id
     */
    @ApiModelProperty(value = "唯一id")
    private Long billId;

    /**
     * 关联订单号
     */
    @ApiModelProperty(value = "关联订单号")
    private String referenceOrderNo;

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 支付平台提供商编号
     */
    @ApiModelProperty(value = "支付平台提供商编号")
    private Long providerId;

    /**
     * 渠道编号
     */
    @ApiModelProperty(value = "渠道编号")
    private Long channelId;

    /**
     * 渠道费率编号
     */
    @ApiModelProperty(value = "渠道费率编号")
    private Integer channelRatesId;

    /**
     * 产品编号
     */
    @ApiModelProperty(value = "产品编号")
    private Long productId;

    /**
     * 产品渠道编号
     */
    @ApiModelProperty(value = "产品渠道编号")
    private Long productChannelId;

    /**
     * 账单号
     */
    @ApiModelProperty(value = "账单号")
    private String billNo;

    /**
     * 平台订单号
     */
    @ApiModelProperty(value = "平台订单号")
    private String platformOrderNo;

    /**
     * 下游订单号
     */
    @ApiModelProperty(value = "下游订单号")
    private String downstreamOrderNo;

    /**
     * 上游订单号
     */
    @ApiModelProperty(value = "上游订单号")
    private String upstreamOrderNo;

    /**
     * 账单日期
     */
    @ApiModelProperty(value = "账单日期")
    private Date billDate;

    /**
     * 账单周期开始日期
     */
    @ApiModelProperty(value = "账单周期开始日期")
    private Date billPeriodStart;

    /**
     * 账单周期结束日期
     */
    @ApiModelProperty(value = "账单周期结束日期")
    private Date billPeriodEnd;

    /**
     * 处理货币代码
     */
    @ApiModelProperty(value = "处理货币代码")
    private String currency;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

    /**
     * 账单类型 transaction=交易 refund=退款 chargeback=拒付 transfers=转账 withdrawals=同名提现 recharges=充值 exchange=换汇
     */
    @ApiModelProperty(value = "账单类型 transaction=交易 refund=退款 chargeback=拒付 transfers=转账 withdrawals=同名提现 recharges=充值 exchange=换汇")
    private String billType;

    /**
     * 支付状态 pending=待支付 succeeded=成功 failed=失败 refunded=退费 partialRefund=部分退费 cancelled=取消 disputed=争议 installment=分期 chargeback=拒付 recurring=定期付款
     */
    @ApiModelProperty(value = "支付状态 pending=待支付 succeeded=成功 failed=失败 refunded=退费 partialRefund=部分退费 cancelled=取消 disputed=争议 installment=分期 chargeback=拒付 recurring=定期付款 ")
    private String paymentStatus;

    /**
     * 交易类型 wallet=电子钱包  bankTransfer=银行转帐 localCard=本地卡  cash=现金 installments=分期 buyNowPayLater=先买后付 cryptocurrency=加密货币 prepaidCard=预付卡 recurring=定期支付
     */
    @ApiModelProperty(value = "交易类型 wallet=电子钱包  bankTransfer=银行转帐 localCard=本地卡  cash=现金 installments=分期 buyNowPayLater=先买后付 cryptocurrency=加密货币 prepaidCard=预付卡 recurring=定期支付")
    private String transactionType;

    /**
     * 交易时间
     */
    @ApiModelProperty(value = "交易时间")
    private Date transactionDate;

    /**
     * 交易地点
     */
    @ApiModelProperty(value = "交易地点")
    private String transactionLocation;

    /**
     * 结算金额
     */
    @ApiModelProperty(value = "结算金额")
    private BigDecimal settlementAmount;

    /**
     * 结算货币
     */
    @ApiModelProperty(value = "结算货币")
    private String settlementCurrency;

    /**
     * 结算周期
     */
    @ApiModelProperty(value = "结算周期")
    private String settlementCycle;

    /**
     * 结算日期
     */
    @ApiModelProperty(value = "结算日期")
    private Date settlementDate;

    /**
     * 账单备注
     */
    @ApiModelProperty(value = "账单备注")
    private String billRemarks;

    /**
     * 账单生成日期
     */
    @ApiModelProperty(value = "账单生成日期")
    private Date billGeneratedAt;

    /**
     * 实际扣费金额
     */
    @ApiModelProperty(value = "实际扣费金额")
    private BigDecimal actualDeduction;

    /**
     * 商户交易固定手续费
     */
    @ApiModelProperty(value = "商户交易固定手续费")
    private String merchantFixedFee;

    /**
     * 商户交易手续费费率
     */
    @ApiModelProperty(value = "商户交易手续费费率")
    private String merchantTxRate;

    /**
     * 商户总手续费
     */
    @ApiModelProperty(value = "商户总手续费")
    private BigDecimal merchantTotalFee;

    /**
     * 商户结算金额
     */
    @ApiModelProperty(value = "商户结算金额")
    private BigDecimal merchantSettlementAmount;

    /**
     * 代理交易固定手续费
     */
    @ApiModelProperty(value = "代理交易固定手续费")
    private String agentFixedFee;

    /**
     * 代理交易手续费费率
     */
    @ApiModelProperty(value = "代理交易手续费费率")
    private BigDecimal agentTxRate;

    /**
     * 代理商总手续费
     */
    @ApiModelProperty(value = "代理商总手续费")
    private BigDecimal agentTotalFee;

    /**
     * 代理商结算金额
     */
    @ApiModelProperty(value = "代理商结算金额")
    private BigDecimal agentSettlementAmount;

    /**
     * 代理商利润
     */
    @ApiModelProperty(value = "代理商利润")
    private BigDecimal agentProfit;

    /**
     * 通道交易固定手续费
     */
    @ApiModelProperty(value = "通道交易固定手续费")
    private String channelFixedFee;

    /**
     * 通道交易手续费费率
     */
    @ApiModelProperty(value = "通道交易手续费费率")
    private String channelTxRate;

    /**
     * 通道总费用
     */
    @ApiModelProperty(value = "通道总费用")
    private BigDecimal channelTotalFee;

    /**
     * 通道结算金额
     */
    @ApiModelProperty(value = "通道结算金额")
    private BigDecimal channelSettlementAmount;

    /**
     * 利润
     */
    @ApiModelProperty(value = "利润")
    private BigDecimal profit;

    /**
     * 对账状态 0：待对账 1：成功对账 2：对账异常
     */
    @ApiModelProperty(value = "对账状态 0：待对账 1：成功对账 2：对账异常")
    private Integer reconciliationStatus;

    /**
     * 结算状态 0 ：待结算 1：结算成功 2：结算异常
     */
    @ApiModelProperty(value = "结算状态 0 ：待结算 1：结算成功 2：结算异常")
    private Integer settlementStatus;

    /**
     * 支付完成时间
     */
    @ApiModelProperty(value = "支付完成时间")
    private Date paymentCompleteTime;

    /**
     * 支付平台编号
     */
    @ApiModelProperty(value = "支付平台编号")
    private Long platformId;

    /**
     * 代理ID
     */
    @ApiModelProperty(value = "代理ID")
    private Long agentId;

    /**
     * 代理名称
     */
    @ApiModelProperty(value = "代理名称")
    private String agentName;

    /**
     * 订单类型：transaction=交易 refund=退款 chargeback=拒付 transfers=转账 withdrawals=同名提现 recharges=充值 exchange=换汇 dispute=争议
     */
    @ApiModelProperty(value = "订单类型：transaction=交易 refund=退款 chargeback=拒付 transfers=转账 withdrawals=同名提现 recharges=充值 exchange=换汇 dispute=争议")
    private String orderType;

    /**
     * 收单方式 checkout=收银台 direct=直接集成
     */
    @ApiModelProperty(value = "收单方式 checkout=收银台 direct=直接集成")
    private String acquiringMode;

    /**
     * 国家
     */
    @ApiModelProperty(value = "国家")
    private String country;

    /**
     * 支付换汇汇率
     */
    @ApiModelProperty(value = "支付换汇汇率")
    private BigDecimal paymentExchange;

    /**
     * 支付换汇手续费
     */
    @ApiModelProperty(value = "支付换汇手续费")
    private BigDecimal paymentExchangeRate;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String paymentMethod;

    /**
     * 商户货币代码
     */
    @ApiModelProperty(value = "商户货币代码")
    private String merchantCurrency;

    /**
     * 商户的支付金额
     */
    @ApiModelProperty(value = "商户的支付金额")
    private BigDecimal merchantOrderAmount;

    /**
     * 通道手续费：格式：费用类型:手续费 货币
     */
    @ApiModelProperty(value = "通道手续费：格式：费用类型:手续费 货币")
    private String channelFee;

    @ApiModelProperty(value = "${column.comment}")
    private Date channelReconciliationDate;

    @ApiModelProperty(value = "${column.comment}")
    private Date channelSettlementDate;

    @ApiModelProperty(value = "${column.comment}")
    private Integer channelSettlementStatus;

    @ApiModelProperty(value = "${column.comment}")
    private Integer channelReconciliationStatus;
}
