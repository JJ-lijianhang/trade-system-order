package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 商户结算表(MerchantUpstreamSettlement)实体类
 *
 * @author ben
 * @since 2024-05-31 20:02:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantUpstreamSettlement", description = "商户结算表")
public class MerchantUpstreamSettlementEntity implements Serializable {
    private static final long serialVersionUID = 578783023834755061L;

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
     * 结算批次号,自动编号
     */
    @ApiModelProperty(value = "结算批次号,自动编号")
    private String settleId;

    /**
     * 商户编号
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantId;

    /**
     * 结算币种
     */
    @ApiModelProperty(value = "结算币种")
    private String currency;

    /**
     * 交易加点利润
     */
    @ApiModelProperty(value = "交易加点利润")
    private BigDecimal txMarkup;

    /**
     * 交易金额
     */
    @ApiModelProperty(value = "交易金额")
    private BigDecimal transactionAmount;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额")
    private BigDecimal settlementAmount;

    /**
     * 手续费
     */
    @ApiModelProperty(value = "手续费")
    private BigDecimal rateFee;

    /**
     * 固定手续费
     */
    @ApiModelProperty(value = "固定手续费")
    private BigDecimal fixFee;

    /**
     * 百分比手续费
     */
    @ApiModelProperty(value = "百分比手续费")
    private BigDecimal percentageFee;

    /**
     * 上游实际金额
     */
    @ApiModelProperty(value = "上游实际金额")
    private BigDecimal upsteamCurrency;

    /**
     * 上游结算金额
     */
    @ApiModelProperty(value = "上游结算金额")
    private BigDecimal upstreamSettlementAmount;

    /**
     * 上游手续费
     */
    @ApiModelProperty(value = "上游手续费")
    private BigDecimal upstreamRateFee;

    /**
     * 上游固定手续费
     */
    @ApiModelProperty(value = "上游固定手续费")
    private BigDecimal upstreamFixFee;

    /**
     * 上游百分比手续费
     */
    @ApiModelProperty(value = "上游百分比手续费")
    private BigDecimal upstreamPercentageFee;

    /**
     * 结算开始时间
     */
    @ApiModelProperty(value = "结算开始时间")
    private Date settleTimeBegin;

    /**
     * 结算截至时间
     */
    @ApiModelProperty(value = "结算截至时间")
    private Date settleTimeEnd;

    /**
     * 结算状态，0=待审核 1=待发布 2=待确认
     */
    @ApiModelProperty(value = "结算状态，0=待审核 1=待发布 2=待确认")
    private Integer settleStatus;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createdTime;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private Date publishTime;

    /**
     * 发布人
     */
    @ApiModelProperty(value = "发布人")
    private String publishOper;

    /**
     * 商户确认人
     */
    @ApiModelProperty(value = "商户确认人")
    private String confirmOper;

    /**
     * 确认时间
     */
    @ApiModelProperty(value = "确认时间")
    private Date confirmTime;

    /**
     * 打款时间
     */
    @ApiModelProperty(value = "打款时间")
    private Date paymentTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
