package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * (MerchantSettmentDetails)实体类
 *
 * @author ben
 * @since 2024-12-04 13:38:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantSettmentDetails", description = "$tableInfo.comment")
public class MerchantSettmentDetailsEntity implements Serializable {
    private static final long serialVersionUID = 854280019947629179L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    @ApiModelProperty(value = "渠道id")
    private Long providerId;

    /**
     * 渠道名称
     */
    @ApiModelProperty(value = "渠道名称")
    private String providerName;

    /**
     * 结算批次号
     */
    @ApiModelProperty(value = "结算批次号")
    private String settlementId;

    /**
     * 下游订单号
     */
    @ApiModelProperty(value = "下游订单号")
    private String downstreamOrderNo;

    /**
     * 上游订单号
     */
    @ApiModelProperty(value = "上游订单号")
    private String platformOrderNo;

    /**
     * 关联订单号
     */
    @ApiModelProperty(value = "关联订单号")
    private String referenceOrderNo;

    /**
     * 交易类型
     */
    @ApiModelProperty(value = "交易类型")
    private String orderType;

    /**
     * 交易状态
     */
    @ApiModelProperty(value = "交易状态")
    private String orderStatus;

    /**
     * 交易创建时间
     */
    @ApiModelProperty(value = "交易创建时间")
    private Date orderCreateTime;

    /**
     * 交易完成时间
     */
    @ApiModelProperty(value = "交易完成时间")
    private Date orderCompleteTime;

    /**
     * 商户号
     */
    @ApiModelProperty(value = "商户号")
    private Long merchantId;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String paymentMethod;

    /**
     * 费用类型
     */
    @ApiModelProperty(value = "费用类型")
    private String feeType;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal merchantOrderAmount;

    /**
     * 处理货币代码
     */
    @ApiModelProperty(value = "处理货币代码")
    private BigDecimal orderAmount;

    /**
     * 处理货币代码
     */
    @ApiModelProperty(value = "处理货币代码")
    private String currency;

    /**
     * 货币
     */
    @ApiModelProperty(value = "货币")
    private String merchantCurrency;

    /**
     * 国家
     */
    @ApiModelProperty(value = "国家")
    private String country;

    /**
     * 结算的汇率
     */
    @ApiModelProperty(value = "结算的汇率")
    private BigDecimal exchange;

    /**
     * 结算的换汇金额
     */
    @ApiModelProperty(value = "结算的换汇金额")
    private BigDecimal exchangeAmount;

    /**
     * 换汇加点
     */
    @ApiModelProperty(value = "换汇加点")
    private BigDecimal markup;

    /**
     * 换汇加点手续费
     */
    @ApiModelProperty(value = "换汇加点手续费")
    private BigDecimal markupAmount;

    /**
     * 固定汇率
     */
    @ApiModelProperty(value = "固定汇率")
    private String downstreamFixedFee;

    /**
     * 百分比费率
     */
    @ApiModelProperty(value = "百分比费率")
    private String downstreamTxRate;

    /**
     * 处理通道手续费
     */
    @ApiModelProperty(value = "处理通道手续费")
    private String downstreamFee;

    @ApiModelProperty(value = "${column.comment}")
    private Date createdTime;

    @ApiModelProperty(value = "${column.comment}")
    private Date updateTime;

    /**
     * 消费金额
     */
    @ApiModelProperty(value = "消费金额")
    private BigDecimal consumerAmount;

    /**
     * 消费货币
     */
    @ApiModelProperty(value = "消费货币")
    private String consumerCurrency;
}
