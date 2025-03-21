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
 * 渠道费率表(ChannelRates)实体类
 *
 * @author ben
 * @since 2024-12-05 14:43:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChannelRates", description = "渠道费率表")
public class ChannelRatesEntity implements Serializable {
    private static final long serialVersionUID = 727761436136684518L;

    /**
     * 费率id
     */
    @ApiModelProperty(value = "费率id")
    private Integer id;

    /**
     * 渠道id
     */
    @ApiModelProperty(value = "渠道id")
    private Integer channelId;

    /**
     * 渠道名称
     */
    @ApiModelProperty(value = "渠道名称")
    private String channelName;

    /**
     * 按该货币计算汇率 当为空时按处理货币计算费率
     */
    @ApiModelProperty(value = "按该货币计算汇率 当为空时按处理货币计算费率")
    private String currency;

    /**
     * 交易类型 1=交易 2=退款 3=拒收 4=结算 5=换汇
     */
    @ApiModelProperty(value = "交易类型 1=交易 2=退款 3=拒收 4=结算 5=换汇 ")
    private String transactionType;

    /**
     * 固定费用的控制：1=统一费用 2=动态货币费用
     */
    @ApiModelProperty(value = "固定费用的控制：1=统一费用 2=动态货币费用")
    private String fixedFeeControl;

    /**
     * 交易费率的控制：1=统一费率 2=动态货币费率
     */
    @ApiModelProperty(value = "交易费率的控制：1=统一费率 2=动态货币费率")
    private String transactionRateControl;

    /**
     * 交易固定费用
     */
    @ApiModelProperty(value = "交易固定费用")
    private String fixedFee;

    /**
     * 交易手续费费率
     */
    @ApiModelProperty(value = "交易手续费费率")
    private String transactionRate;

    /**
     * 管理费
     */
    @ApiModelProperty(value = "管理费")
    private BigDecimal managementFee;

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
     * 最低结算金额
     */
    @ApiModelProperty(value = "最低结算金额")
    private Integer minSettlement;

    /**
     * 结算手续费
     */
    @ApiModelProperty(value = "结算手续费")
    private String settlementFee;

    /**
     * 汇率加成表
     */
    @ApiModelProperty(value = "汇率加成表")
    private String exchangeRate;

    /**
     * 滚动保证金
     */
    @ApiModelProperty(value = "滚动保证金")
    private String marginRate;

    /**
     * 滚动保证金天数
     */
    @ApiModelProperty(value = "滚动保证金天数")
    private String marginDays;

    /**
     * del最小交易金额
     */
    @ApiModelProperty(value = "del最小交易金额")
    private BigDecimal minAmount;

    /**
     * del最大交易金额
     */
    @ApiModelProperty(value = "del最大交易金额")
    private BigDecimal maxAmount;

    /**
     * 交易日限额
     */
    @ApiModelProperty(value = "交易日限额")
    private BigDecimal dailyLimit;

    /**
     * del最低交易费（按费率计算交易额<该值，按该值计算交易费用）
     */
    @ApiModelProperty(value = "del最低交易费（按费率计算交易额<该值，按该值计算交易费用）")
    private BigDecimal minTransactionFee;

    /**
     * dek最高交易费
     */
    @ApiModelProperty(value = "dek最高交易费")
    private BigDecimal maxTransactionFee;

    /**
     * 状态 0=启用 1=禁用
     */
    @ApiModelProperty(value = "状态 0=启用 1=禁用")
    private Integer istate;

    /**
     * del费率生效日期
     */
    @ApiModelProperty(value = "del费率生效日期")
    private Date effectiveDate;

    /**
     * del过期时间
     */
    @ApiModelProperty(value = "del过期时间")
    private Date expiryDate;

    /**
     * 是否删除 0=未删除；1=已删除
     */
    @ApiModelProperty(value = "是否删除 0=未删除；1=已删除")
    private Integer isDeleted;

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
     * 子付款方式名称
     */
    @ApiModelProperty(value = "子付款方式名称")
    private String childrenPaymentType;

    /**
     * 付款方式标识（swift，pobo）
     */
    @ApiModelProperty(value = "付款方式标识（swift，pobo）")
    private String paymentIdentification;

    /**
     * 付款方式
     */
    @ApiModelProperty(value = "付款方式")
    private String paymentType;

    /**
     * 税费 = 上游通道手续费 * VAT
     */
    @ApiModelProperty(value = "税费 = 上游通道手续费 * VAT")
    private BigDecimal vat;

    @ApiModelProperty(value = "${column.comment}")
    private String gatewayFee;
}
