package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 商户资金账户表(MerchantWallet)实体类
 *
 * @author ben
 * @since 2024-06-02 11:14:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantWallet", description = "商户资金账户表")
public class MerchantWalletEntity implements Serializable {
    private static final long serialVersionUID = 768442603643741953L;

    /**
     * 唯一id
     */
    @ApiModelProperty(value = "唯一id")
    private Long id;

    /**
     * 钱包状态
     */
    @ApiModelProperty(value = "钱包状态")
    private Long istate;
    /**
     * 商户编号
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantId;

    /**
     * 代理商编号(预留)
     */
    @ApiModelProperty(value = "代理商编号(预留)")
    private Long agentId;

    /**
     * 通道提供商编号
     */
    @ApiModelProperty(value = "通道提供商编号")
    private Long platformId;

    /**
     * 支付平台编号
     */
    @ApiModelProperty(value = "支付平台编号")
    private Long providerId;

    /**
     * 货币编码
     */
    @ApiModelProperty(value = "货币编码")
    private String currency;

    /**
     * 账户余额
     */
    @ApiModelProperty(value = "账户余额")
    private BigDecimal balance;

    /**
     * 充值余额（充值钱包）
     */
    @ApiModelProperty(value = "充值余额（充值钱包）")
    private BigDecimal rechargeBalance;

    /**
     * 收单余额（收单钱包）
     */
    @ApiModelProperty(value = "收单余额（收单钱包）")
    private BigDecimal acquiringBalance;

    /**
     * 收付款余额（收付款钱包）
     */
    @ApiModelProperty(value = "收付款钱包")
    private BigDecimal collectionBalance;

    /**
     * 收付款余额（收付款钱包）
     */
    @ApiModelProperty(value = "在途入账资金")
    private BigDecimal pendingPayIn;

    /**
     * 收付款余额（收付款钱包）
     */
    @ApiModelProperty(value = "在途结算资金")
    private BigDecimal pendingSettlement;

    /**
     * 收付款余额（收付款钱包）
     */
    @ApiModelProperty(value = "在途支付资金")
    private BigDecimal pendingPaying;

    /**
     * 收付款余额（收付款钱包）
     */
    @ApiModelProperty(value = "在途换汇资金")
    private BigDecimal pendingExchange;

    /**
     * 在途资金
     */
    @ApiModelProperty(value = "在途资金")
    private BigDecimal pendingBalance;

    /**
     * 冻结金额
     */
    @ApiModelProperty(value = "冻结金额")
    private BigDecimal freezeAmount;

    /**
     * 提现总额
     */
    @ApiModelProperty(value = "提现总额")
    private BigDecimal withdrawalAmount;

    /**
     * 版本控制
     */
    @ApiModelProperty(value = "版本控制")
    private Long version;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "${column.comment}")
    private Date updateTime;
}
