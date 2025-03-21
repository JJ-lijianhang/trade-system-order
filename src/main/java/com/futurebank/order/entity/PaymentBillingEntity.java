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
 * 支付账单表(new)(PaymentBilling)实体类
 *
 * @author ben
 * @since 2024-12-05 14:42:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentBilling", description = "支付账单表(new)")
public class PaymentBillingEntity implements Serializable {
    private static final long serialVersionUID = -64604090014459517L;

    /**
     * 唯一id
     */
    @ApiModelProperty(value = "唯一id")
    private Long billId;

    /**
     * 账单号
     */
    @ApiModelProperty(value = "账单号")
    private String billNo;

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
     * 支付平台编号
     */
    @ApiModelProperty(value = "支付平台编号")
    private Long platformId;

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
     * 订单类型：transaction=交易 refund=退款 chargeback=拒付 transfers=转账 withdrawals=同名提现 recharges=充值 exchange=换汇 dispute=争议
     */
    @ApiModelProperty(value = "订单类型：transaction=交易 refund=退款 chargeback=拒付 transfers=转账 withdrawals=同名提现 recharges=充值 exchange=换汇 dispute=争议")
    private String orderType;

    /**
     * 交易类型 wallet=电子钱包  bankTransfer=银行转帐 localCard=本地卡  cash=现金 installments=分期 buyNowPayLater=先买后付 cryptocurrency=加密货币 prepaidCard=预付卡 recurring=定期支付
     */
    @ApiModelProperty(value = "交易类型 wallet=电子钱包  bankTransfer=银行转帐 localCard=本地卡  cash=现金 installments=分期 buyNowPayLater=先买后付 cryptocurrency=加密货币 prepaidCard=预付卡 recurring=定期支付")
    private String transactionType;

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
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String paymentMethod;

    /**
     * 处理货币代码
     */
    @ApiModelProperty(value = "处理货币代码")
    private String currency;

    /**
     * 结算货币代码
     */
    @ApiModelProperty(value = "结算货币代码")
    private String settlementCurrency;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

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
     * 订单状态 PENDING=交易处理中 FAILED=交易失败 SUCCEED=交易成功 CANCEL=交易已取消 EXPIRED=交易已过期 REFUSED=交易被拒绝 PENDING=退款处理中 REFUNDED=已退款 FAILED=退款失败 REFUND_REVOKE=退款撤销 REFUND_REFUSED=退款拒绝 CHARGEBACK=拒付 CHARGEBACK_REVERSED=拒付撤销 DISPUTE=争议
     */
    @ApiModelProperty(value = "订单状态 PENDING=交易处理中 FAILED=交易失败 SUCCEED=交易成功 CANCEL=交易已取消 EXPIRED=交易已过期 REFUSED=交易被拒绝 PENDING=退款处理中 REFUNDED=已退款 FAILED=退款失败 REFUND_REVOKE=退款撤销 REFUND_REFUSED=退款拒绝 CHARGEBACK=拒付 CHARGEBACK_REVERSED=拒付撤销 DISPUTE=争议")
    private String orderStatus;

    /**
     * 上游返回状态
     */
    @ApiModelProperty(value = "上游返回状态")
    private String upstreamStatus;

    /**
     * 关联订单号
     */
    @ApiModelProperty(value = "关联订单号")
    private String referenceOrderNo;

    /**
     * 下游订单号
     */
    @ApiModelProperty(value = "下游订单号")
    private String downstreamOrderNo;

    /**
     * 平台订单号
     */
    @ApiModelProperty(value = "平台订单号")
    private String platformOrderNo;

    /**
     * 上游订单号
     */
    @ApiModelProperty(value = "上游订单号")
    private String upstreamOrderNo;

    /**
     * 订单创建时间
     */
    @ApiModelProperty(value = "订单创建时间")
    private Date orderCreateTime;

    /**
     * 订单完成时间
     */
    @ApiModelProperty(value = "订单完成时间")
    private Date orderCompleteTime;

    /**
     * 订单通知时间
     */
    @ApiModelProperty(value = "订单通知时间")
    private Date orderNotifyTime;

    /**
     * 预估支付金额（USD）
     */
    @ApiModelProperty(value = "预估支付金额（USD）")
    private BigDecimal estimatedAmount;

    /**
     * 预估USD，换汇的汇率
     */
    @ApiModelProperty(value = "预估USD，换汇的汇率")
    private String estimatedExchangeRate;

    /**
     * 商户交易固定手续费
     */
    @ApiModelProperty(value = "商户交易固定手续费")
    private String downstreamFixedFee;

    /**
     * 商户交易手续费费率
     */
    @ApiModelProperty(value = "商户交易手续费费率")
    private String downstreamTxRate;

    /**
     * 处理货币通道手续费：格式：费用类型:手续费货币
     */
    @ApiModelProperty(value = "处理货币通道手续费：格式：费用类型:手续费货币")
    private String downstreamFee;

    /**
     * 消费货币通道手续费：
     */
    @ApiModelProperty(value = "消费货币通道手续费：")
    private String downstreamFeeC;

    /**
     * 预估结算金额（USD）
     */
    @ApiModelProperty(value = "预估结算金额（USD）")
    private BigDecimal downstreamEstimatedAmount;

    /**
     * 预估手续费金额(USD)
     */
    @ApiModelProperty(value = "预估手续费金额(USD)")
    private BigDecimal downstreamEstimatedFee;

    /**
     * 当前费率（暂停使用）
     */
    @ApiModelProperty(value = "当前费率（暂停使用）")
    private BigDecimal downstreamEstimatedExchange;

    /**
     * 换汇加点（暂停使用）
     */
    @ApiModelProperty(value = "换汇加点（暂停使用）")
    private BigDecimal downstreamEstimatedExchangeRate;

    /**
     * 对账状态 0：待对账 1：成功对账 2：对账异常
     */
    @ApiModelProperty(value = "对账状态 0：待对账 1：成功对账 2：对账异常")
    private Integer downstreamReconciliationStatus;

    /**
     * 商户对账时间
     */
    @ApiModelProperty(value = "商户对账时间")
    private Date downstreamReconciliationDate;

    /**
     * 结算状态 0 ：待结算 1：结算成功 2：结算异常
     */
    @ApiModelProperty(value = "结算状态 0 ：待结算 1：结算成功 2：结算异常")
    private Integer downstreamSettlementStatus;

    /**
     * 结算结算周期 T=工作日 D=自然日
     */
    @ApiModelProperty(value = "结算结算周期 T=工作日 D=自然日")
    private String settlementCycle;

    /**
     * 商户结算时间
     */
    @ApiModelProperty(value = "商户结算时间")
    private Date downstreamSettlementDate;

    /**
     * 商户结算金额
     */
    @ApiModelProperty(value = "商户结算金额")
    private BigDecimal downstreamSettlementAmount;

    /**
     * 商户总手续费
     */
    @ApiModelProperty(value = "商户总手续费")
    private BigDecimal downstreamTotalFee;

    /**
     * 上游交易固定手续费
     */
    @ApiModelProperty(value = "上游交易固定手续费")
    private String upstreamFixedFee;

    /**
     * 上游交易手续费费率
     */
    @ApiModelProperty(value = "上游交易手续费费率")
    private String upstreamTxRate;

    /**
     * 通道手续费：格式：费用类型:手续费货币单位
     */
    @ApiModelProperty(value = "通道手续费：格式：费用类型:手续费货币单位")
    private String upstreamFee;

    /**
     * 上游实际扣除的手续费
     */
    @ApiModelProperty(value = "上游实际扣除的手续费")
    private String upstreamActualFee;

    /**
     * 预估金额
     */
    @ApiModelProperty(value = "预估金额")
    private BigDecimal upstreamEstimatedAmount;

    /**
     * 预估手续费金额
     */
    @ApiModelProperty(value = "预估手续费金额")
    private BigDecimal upstreamEstimatedFee;

    /**
     * 当前费率
     */
    @ApiModelProperty(value = "当前费率")
    private String upstreamActualExchange;

    /**
     * 换汇加点
     */
    @ApiModelProperty(value = "换汇加点")
    private String upstreamActualExchangeRate;

    /**
     * 换汇费用
     */
    @ApiModelProperty(value = "换汇费用")
    private BigDecimal upstreamActualExchangeFee;

    /**
     * 上游对账状态
     */
    @ApiModelProperty(value = "上游对账状态")
    private Integer upstreamReconciliationStatus;

    /**
     * 上游对账日期
     */
    @ApiModelProperty(value = "上游对账日期")
    private Date upstreamReconciliationDate;

    /**
     * 上游结算状态
     */
    @ApiModelProperty(value = "上游结算状态")
    private Integer upstreamSettlementStatus;

    /**
     * 上游结算日期
     */
    @ApiModelProperty(value = "上游结算日期")
    private Date upstreamSettlementDate;

    /**
     * 上游总费用
     */
    @ApiModelProperty(value = "上游总费用")
    private BigDecimal upstreamTotalFee;

    /**
     * 通道结算金额
     */
    @ApiModelProperty(value = "通道结算金额")
    private BigDecimal upstreamSettlementAmount;

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
     * 支付换汇加点利润
     */
    @ApiModelProperty(value = "支付换汇加点利润")
    private BigDecimal paymentTxMarkup;

    /**
     * 利润(USD)
     */
    @ApiModelProperty(value = "利润(USD)")
    private BigDecimal profit;

    /**
     * 账单修复状态 1：原始账单 2：修复账单
     */
    @ApiModelProperty(value = "账单修复状态 1：原始账单 2：修复账单")
    private Integer repairStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 下游初始订单号
     */
    @ApiModelProperty(value = "下游初始订单号")
    private String downstreamOrderNoOrigin;

    /**
     * 加点手续费(USD)
     */
    @ApiModelProperty(value = "加点手续费(USD)")
    private BigDecimal paymentTxMarkupUsd;

    /**
     * 下游固定费用(USD)
     */
    @ApiModelProperty(value = "下游固定费用(USD)")
    private BigDecimal downstreamFixedFeeUsd;

    /**
     * 上游税率
     */
    @ApiModelProperty(value = "上游税率")
    private BigDecimal upstreamVat;

    /**
     * 下游游税率
     */
    @ApiModelProperty(value = "下游游税率")
    private BigDecimal downstreamVat;

    /**
     * 上游税费 = 上游游通道手续费 * VAT
     */
    @ApiModelProperty(value = "上游税费 = 上游游通道手续费 * VAT")
    private BigDecimal estimatedUpstreamVatFee;

    /**
     * 下游税费 = 下游通道手续费 * VAT
     */
    @ApiModelProperty(value = "下游税费 = 下游通道手续费 * VAT")
    private BigDecimal estimatedDownstreamVatFee;

    @ApiModelProperty(value = "${column.comment}")
    private String downstreamGatewayFee;

    /**
     * 网关手续费
     */
    @ApiModelProperty(value = "网关手续费")
    private String upstreamGatewayFee;
}
