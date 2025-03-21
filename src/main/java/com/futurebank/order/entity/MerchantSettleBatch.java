package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantSettleBatch", description = "结算批次数据")
public class MerchantSettleBatch extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3144213964431603945L;

    /**
     * 结算批次号
     */
    @ApiModelProperty("结算批次号")
    private String batchNo;

    /**
     * 结算批次号状态
     */
    @ApiModelProperty("结算批次状态：0->初始化 1->NOT Ready 100->prepare 200已结算 300转结算")
    private Integer batchStatus;

    /**
     * 商户号
     */
    @ApiModelProperty("商户号")
    private Long merchantId;

    /**
     * 商户名称
     */
    @ApiModelProperty("商户名称")
    private String merchantName;

    /**
     * 渠道id
     */
    @ApiModelProperty("渠道id")
    private Long providerId;

    /**
     * 渠道名称
     */
    @ApiModelProperty("渠道名称")
    private String providerName;

    /**
     * 支付id
     */
    @ApiModelProperty("支付id")
    private Long paymentId;

    /**
     * 支付方式key
     */
    @ApiModelProperty("支付方式key")
    private String paymentMethod;

    /**
     * 支付方式名称
     */
    @ApiModelProperty("支付方式名称")
    private String paymentMethodName;

    /**
     * 商户结算货币
     */
    @ApiModelProperty("商户结算货币")
    private String settlementCurrency;

    /**
     * 商户结算周期（tn）模式
     */
    @ApiModelProperty("商户结算周期（tn）模式")
    private Integer settlementMethod;


    @ApiModelProperty(value = "结算范围，1 工作日(T) 2 自然日(D) 3 自然周(W) 4 自然月(M) ")
    private Integer settlementRange;

    /**
     * 商户最小结算金额限制
     */
    @ApiModelProperty("商户最小结算金额限制")
    private BigDecimal minSettlementAmount;

    /**
     * 商户换汇加点费-配置
     */
    @ApiModelProperty("商户换汇加点费-配置")
    private BigDecimal settlementExchangeMargin;

    /**
     * 消费者换汇加点-配置
     */
    @ApiModelProperty("消费者换汇加点-配置")
    private BigDecimal consumerExchangeMargin;

    /**
     * 对账日期
     */
    @ApiModelProperty("对账日期")
    private Date reconciliationDate;

    /**
     * 对账范围-开始时间
     */
    @ApiModelProperty("对账范围-开始时间")
    private Date reconciliationStartTime;

    /**
     * 对账范围-结束时间
     */
    @ApiModelProperty("对账范围-结束时间")
    private Date reconciliationEndTime;

    /**
     * 结算日期
     */
    @ApiModelProperty("结算日期")
    private Date settlementDate;

    /**
     * 结算日期
     */
    @ApiModelProperty("结算日期-执行结算的时间")
    private Date settlementActureDate;

    /**
     * 结算范围-开始时间
     */
    @ApiModelProperty("结算范围-开始时间")
    private Date settlementStartTime;

    /**
     * 结算范围-结束时间
     */
    @ApiModelProperty("结算范围-结束时间")
    private Date settlementEndTime;

    /**
     * 结算说明
     */
    @ApiModelProperty("结算说明")
    private String settlementRemark;


}
