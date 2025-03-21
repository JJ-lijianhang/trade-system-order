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
 * 支付结算信息表，用于存储不同支付服务提供商的结算配置(PaymentSettlementConf)实体类
 *
 * @author ben
 * @since 2024-12-04 16:11:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentSettlementConf", description = "支付结算信息表，用于存储不同支付服务提供商的结算配置")
public class PaymentSettlementConfEntity implements Serializable {
    private static final long serialVersionUID = -44998864436756752L;

    /**
     * 唯一标识
     */
    @ApiModelProperty(value = "唯一标识")
    private Long id;

    @ApiModelProperty(value = "${column.comment}")
    private Long merchantId;

    @ApiModelProperty(value = "${column.comment}")
    private String merchantName;

    /**
     * 支付服务提供商的编号
     */
    @ApiModelProperty(value = "支付服务提供商的编号")
    private Long providerId;

    /**
     * 支付服务提供商的名称
     */
    @ApiModelProperty(value = "支付服务提供商的名称")
    private String providerName;

    /**
     * 结算方式=结算周期
     */
    @ApiModelProperty(value = "结算方式=结算周期  ")
    private Integer settlementMethod;

    /**
     * 结算范围，1 工作日(T) 2 自然日(D) 3 自然周(W) 4 自然月(M)
     */
    @ApiModelProperty(value = "结算范围，1 工作日(T) 2 自然日(D) 3 自然周(W) 4 自然月(M) ")
    private String settlementRange;

    /**
     * 结算的货币类型，例如 USD 美元
     */
    @ApiModelProperty(value = "结算的货币类型，例如 USD 美元")
    private String settlementCurrency;

    /**
     * 最低结算金额，保留两位小数以表示货币
     */
    @ApiModelProperty(value = "最低结算金额，保留两位小数以表示货币")
    private BigDecimal minSettlementAmount;

    /**
     * 最低余额金额，保留两位小数
     */
    @ApiModelProperty(value = "最低余额金额，保留两位小数")
    private BigDecimal minBalanceAmount;

    /**
     * 结算汇率加点，表示交易时汇率的额外加成
     */
    @ApiModelProperty(value = "结算汇率加点，表示交易时汇率的额外加成")
    private BigDecimal settlementExchangeMargin;

    /**
     * 消费者汇率加点，消费者支付时的汇率额外加成
     */
    @ApiModelProperty(value = "消费者汇率加点，消费者支付时的汇率额外加成")
    private BigDecimal consumerExchangeMargin;

    /**
     * 消费货币
     */
    @ApiModelProperty(value = "消费货币")
    private String consumerCurrency;

    /**
     * 消费类型 1 跟随支付方式 2 单一币种
     */
    @ApiModelProperty(value = "消费类型 1 跟随支付方式 2 单一币种")
    private String consumerType;

    /**
     * 商户货币
     */
    @ApiModelProperty(value = "商户货币")
    private String merchantCurrency;

    /**
     * 商户类型 1 跟随支付方式 2 单一币种
     */
    @ApiModelProperty(value = "商户类型 1 跟随支付方式 2 单一币种")
    private String merchantType;

    @ApiModelProperty(value = "${column.comment}")
    private Date createTime;

    @ApiModelProperty(value = "${column.comment}")
    private Date updateTime;

    /**
     * 对账日期
     */
    @ApiModelProperty(value = "对账日期")
    private Date reconciliationDate;

    /**
     * 结算日期
     */
    @ApiModelProperty(value = "结算日期")
    private Date settlementDate;
}
