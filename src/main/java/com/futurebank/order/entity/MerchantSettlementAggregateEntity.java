package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * (MerchantSettlementAggregate)实体类
 *
 * @author ben
 * @since 2024-06-11 12:34:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantSettlementAggregate", description = "$tableInfo.comment")
public class MerchantSettlementAggregateEntity implements Serializable {
    private static final long serialVersionUID = 448430967688516801L;

    /**
     * 唯一id
     */
    @ApiModelProperty(value = "唯一id")
    private Long id;

    @ApiModelProperty(value = "渠道id")
    private Long providerId;

    /**
     * 渠道名称
     */
    @ApiModelProperty(value = "渠道名称")
    private String providerName;

    /**
     * 结算编号
     */
    @ApiModelProperty(value = "结算编号")
    private String settlementId;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
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
     * 费用条数
     */
    @ApiModelProperty(value = "费用条数")
    private Integer feeCount;

    /**
     * 汇总金额
     */
    @ApiModelProperty(value = "汇总金额")
    private BigDecimal aggregateAmount;

    /**
     * 货币
     */
    @ApiModelProperty(value = "货币")
    private String currency;

    /**
     * 汇率
     */
    @ApiModelProperty(value = "汇率")
    private BigDecimal exchange;

    /**
     * 换汇金额
     */
    @ApiModelProperty(value = "换汇金额")
    private BigDecimal exchangeAmount;

    /**
     * 换汇加点
     */
    @ApiModelProperty(value = "换汇加点")
    private BigDecimal markup;

    /**
     * 换汇加点的费用
     */
    @ApiModelProperty(value = "换汇加点的费用")
    private BigDecimal markupFee;

    /**
     * 目标货币
     */
    @ApiModelProperty(value = "目标货币")
    private String tiggerCurrency;

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
}
