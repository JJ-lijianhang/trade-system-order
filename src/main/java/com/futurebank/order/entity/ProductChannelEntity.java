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
 * 产品通道表(ProductChannel)实体类
 *
 * @author ben
 * @since 2024-12-05 14:43:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductChannel", description = "产品通道表")
public class ProductChannelEntity implements Serializable {
    private static final long serialVersionUID = 657905795352551738L;

    /**
     * 产品通道ID
     */
    @ApiModelProperty(value = "产品通道ID")
    private Long id;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 代理id
     */
    @ApiModelProperty(value = "代理id")
    private Long agentId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 产品ID
     */
    @ApiModelProperty(value = "产品ID")
    private Long productId;

    /**
     * 支付通道ID
     */
    @ApiModelProperty(value = "支付通道ID")
    private Long channelId;

    /**
     * 上游id
     */
    @ApiModelProperty(value = "上游id")
    private Long providerId;

    /**
     * 平台id
     */
    @ApiModelProperty(value = "平台id")
    private Long platformId;

    /**
     * 支持卡类型
     */
    @ApiModelProperty(value = "支持卡类型")
    private String supportedCards;

    /**
     * 收单方式 checkout=收银台 direct=直接集成
     */
    @ApiModelProperty(value = "收单方式 checkout=收银台 direct=直接集成")
    private String acquiringMode;

    /**
     * 支持消费的国家 (以逗号分隔的国家编码iso2,Global = 全国)
     */
    @ApiModelProperty(value = "支持消费的国家 (以逗号分隔的国家编码iso2,Global = 全国)")
    private String supportedConsumer;

    /**
     * 支持商户的国家  (以逗号分隔的国家编码iso2,Global = 全国)
     */
    @ApiModelProperty(value = "支持商户的国家  (以逗号分隔的国家编码iso2,Global = 全国)")
    private String supportedMerchant;

    /**
     * 行业 逗号分隔 当为空时支持所有行业
     */
    @ApiModelProperty(value = "行业 逗号分隔 当为空时支持所有行业")
    private String industries;

    /**
     * 处理货币
     */
    @ApiModelProperty(value = "处理货币")
    private String processingCurrencies;

    /**
     * 结算货币
     */
    @ApiModelProperty(value = "结算货币")
    private String settlementCurrencies;

    /**
     * 交易类型 wallet=电子钱包  bankTransfer=银行转帐 localCard=本地卡  cash=现金 installments=分期 buyNowPayLater=先买后付 cryptocurrency=加密货币 prepaidCard=预付卡 recurring=定期支付
     */
    @ApiModelProperty(value = "交易类型 wallet=电子钱包  bankTransfer=银行转帐 localCard=本地卡  cash=现金 installments=分期 buyNowPayLater=先买后付 cryptocurrency=加密货币 prepaidCard=预付卡 recurring=定期支付")
    private String transactionType;

    /**
     * 消费货币
     */
    @ApiModelProperty(value = "消费货币")
    private String consumerCurrencies;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String paymentMethod;

    /**
     * 限额(1-10000)
     */
    @ApiModelProperty(value = "限额(1-10000)")
    private String limitAmount;

    /**
     * 限额币种
     */
    @ApiModelProperty(value = "限额币种")
    private String limitCurrencies;

    /**
     * 支付环节换汇手续费
     */
    @ApiModelProperty(value = "支付环节换汇手续费")
    private BigDecimal paymentExchangeRate;

    /**
     * 固定手续费
     */
    @ApiModelProperty(value = "固定手续费")
    private String fixedFee;

    /**
     * 手续费费率
     */
    @ApiModelProperty(value = "手续费费率")
    private String transactionRate;

    /**
     * 退款手续费
     */
    @ApiModelProperty(value = "退款手续费")
    private String refundFee;

    /**
     * 拒付手续费
     */
    @ApiModelProperty(value = "拒付手续费")
    private String chargebankFee;

    /**
     * 结算手续费
     */
    @ApiModelProperty(value = "结算手续费")
    private String settlementFee;

    /**
     * 换汇手续费
     */
    @ApiModelProperty(value = "换汇手续费")
    private String exchangeRate;

    /**
     * 保证金
     */
    @ApiModelProperty(value = "保证金")
    private String marginRate;

    /**
     * 通道提供商
     */
    @ApiModelProperty(value = "通道提供商")
    private Long provider;

    /**
     * 通道是否激活 0=激活 1=未激活
     */
    @ApiModelProperty(value = "通道是否激活 0=激活 1=未激活")
    private Integer active;

    /**
     * 是否删除 0=未删除；1=已删除
     */
    @ApiModelProperty(value = "是否删除 0=未删除；1=已删除")
    private Integer isDeleted;

    /**
     * 是否支持定期支付 (0=支持 1=不支持)
     */
    @ApiModelProperty(value = "是否支持定期支付 (0=支持 1=不支持)")
    private Integer supportRecurring;

    /**
     * 是否支持一键付款 (0=支持 1=不支持)
     */
    @ApiModelProperty(value = "是否支持一键付款 (0=支持 1=不支持)")
    private Integer supportOneClick;

    /**
     * 退费类型 (0=不支持 1=全退 2=部分 3=多个部分) 多个逗号隔开
     */
    @ApiModelProperty(value = "退费类型 (0=不支持 1=全退 2=部分 3=多个部分) 多个逗号隔开")
    private String refundType;

    /**
     * 退款时间（单位：天）
     */
    @ApiModelProperty(value = "退款时间（单位：天）")
    private Integer refundExpiryTime;

    /**
     * 是否支持拒付 (0=支持 1=不支持)
     */
    @ApiModelProperty(value = "是否支持拒付 (0=支持 1=不支持)")
    private Integer supportChargeback;

    /**
     * 收银台超时时间 (单位：分钟)
     */
    @ApiModelProperty(value = "收银台超时时间 (单位：分钟)")
    private Integer sessionTimeout;

    /**
     * 按该货币计算汇率 当为空时按处理货币计算费率
     */
    @ApiModelProperty(value = "按该货币计算汇率 当为空时按处理货币计算费率")
    private String currency;

    /**
     * 管理费
     */
    @ApiModelProperty(value = "管理费")
    private BigDecimal managementFee;

    /**
     * 付款方式(1级)
     */
    @ApiModelProperty(value = "付款方式(1级)")
    private String paymentType;

    /**
     * 子付款方式名称(2级)
     */
    @ApiModelProperty(value = "子付款方式名称(2级)")
    private String childrenPaymentType;

    /**
     * 付款方式标识(3级)（swift，pobo）
     */
    @ApiModelProperty(value = "付款方式标识(3级)（swift，pobo）")
    private String paymentIdentification;

    /**
     * 换汇源币种
     */
    @ApiModelProperty(value = "换汇源币种")
    private String exchangeSourceCurrencies;

    /**
     * 换汇目标币种
     */
    @ApiModelProperty(value = "换汇目标币种")
    private String exchangeTargetCurrencies;

    /**
     * 换汇汇差
     */
    @ApiModelProperty(value = "换汇汇差")
    private BigDecimal exchangeRateDifference;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private Long operator;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long creator;

    /**
     * 请求手续费 0=请求收取 1=成功收取
     */
    @ApiModelProperty(value = "请求手续费 0=请求收取 1=成功收取")
    private String requestFeeFlag;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    /**
     * 手续费类型 ：1=跟随上游 2=自定义
     */
    @ApiModelProperty(value = "手续费类型 ：1=跟随上游 2=自定义")
    private Integer feeType;

    /**
     * 税费 = 下游通道手续费 * VAT
     */
    @ApiModelProperty(value = "税费 = 下游通道手续费 * VAT")
    private BigDecimal vat;

    @ApiModelProperty(value = "${column.comment}")
    private String gatewayFee;
}
