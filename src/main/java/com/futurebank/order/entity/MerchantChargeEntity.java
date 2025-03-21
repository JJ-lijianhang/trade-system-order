package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 商户资金流水表(MerchantCharge)实体类
 *
 * @author ben
 * @since 2024-06-12 20:12:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantCharge", description = "商户资金流水表")
public class MerchantChargeEntity implements Serializable {
    private static final long serialVersionUID = 776683204073895028L;

    /**
     * 唯一标识
     */
    @ApiModelProperty(value = "唯一标识")
    private Long id;

    /**
     * 收单通道提供方编号
     */
    @ApiModelProperty(value = "收单通道提供方编号")
    private Long providerId;

    /**
     * 支付平台编号
     */
    @ApiModelProperty(value = "支付平台编号")
    private Long platformId;

    /**
     * 代理商编号
     */
    @ApiModelProperty(value = "代理商编号")
    private Long agentId;

    /**
     * 商户编号
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantId;

    /**
     * 消费者编号
     */
    @ApiModelProperty(value = "消费者编号")
    private String consumerId;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String paymentMethod;

    /**
     * 结算期数
     */
    @ApiModelProperty(value = "结算期数")
    private String period;

    /**
     * 交易编号
     */
    @ApiModelProperty(value = "交易编号")
    private String tradeNo;

    /**
     * 关联id
     */
    @ApiModelProperty(value = "关联id")
    private String referenceNo;

    /**
     * 类型 (0 进 1 出)
     */
    @ApiModelProperty(value = "类型 (0 进 1 出)")
    private Integer chargeType;

    /**
     * 服务类型
     */
    @ApiModelProperty(value = "服务类型")
    private String serviceType;

    /**
     * 交易类型
     */
    @ApiModelProperty(value = "交易类型")
    private String bizCode;

    /**
     * 账号类型 1=收单账户 2=收款账户 3=充值账户
     */
    @ApiModelProperty(value = "账号类型 1=收单账户 2=收款账户 3=充值账户")
    private Integer walletType;

    /**
     * 交易类型 (详见枚举)
     */
    @ApiModelProperty(value = "交易类型 (详见枚举)")
    private Integer chargeEventType;

    /**
     * 交易事件名（详见枚举）
     */
    @ApiModelProperty(value = "交易事件名（详见枚举）")
    private String chargeEventName;

    /**
     * 交易货币
     */
    @ApiModelProperty(value = "交易货币")
    private String currency;

    /**
     * 变化前总余额
     */
    @ApiModelProperty(value = "变化前总余额")
    private BigDecimal ioldmoneytotal;

    /**
     * 变化前余额
     */
    @ApiModelProperty(value = "变化前余额")
    private BigDecimal ioldmoney;

    /**
     * 流水金额
     */
    @ApiModelProperty(value = "流水金额")
    private BigDecimal imoney;

    /**
     * 变化后此账户余额
     */
    @ApiModelProperty(value = "变化后此账户余额")
    private BigDecimal ibalance;

    /**
     * 变化后总余额
     */
    @ApiModelProperty(value = "变化后总余额")
    private BigDecimal ibalancetotal;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String cmemo;

    /**
     * 流水时间
     */
    @ApiModelProperty(value = "流水时间")
    private Date cadddate;

    /**
     * 流水时间-时间戳
     */
    @ApiModelProperty(value = "流水时间-时间戳")
    private Long bizTimestamp;
}
