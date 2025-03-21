package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 商户结算表(MerchantSettlement)实体类
 *
 * @author ben
 * @since 2024-06-09 17:41:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantSettlement", description = "商户结算表")
public class MerchantSettlementEntity implements Serializable {
    private static final long serialVersionUID = 873045579716443806L;

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
     * 目标货币：暂定全部换成USD
     */
    @ApiModelProperty(value = "目标货币：暂定全部换成USD")
    private String tiggerCurrency;

    /**
     * 汇率
     */
    @ApiModelProperty(value = "汇率")
    private BigDecimal exchange;

    /**
     * 换汇后金额
     */
    @ApiModelProperty(value = "换汇后金额")
    private BigDecimal exchangeAmount;

    /**
     * 换汇加点（服务加点）
     */
    @ApiModelProperty(value = "换汇加点（服务加点）")
    private BigDecimal markup;

    /**
     * 加点费用
     */
    @ApiModelProperty(value = "加点费用")
    private BigDecimal markFee;

    /**
     * 实际结算金额
     */
    @ApiModelProperty(value = "实际结算金额")
    private BigDecimal settlementAmount;

    /**
     * 结算金额
     */
    @ApiModelProperty(value = "结算金额")
    private BigDecimal transactionAmount;

    /**
     * 总手续费
     */
    @ApiModelProperty(value = "总手续费")
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
     * 结算状态，0=审核中 1=发布 2=确认 3=确认已审核 4=已打款
     */
    @ApiModelProperty(value = "结算状态，0=审核中 1=发布 2=确认 3=确认已审核 4=已打款")
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
