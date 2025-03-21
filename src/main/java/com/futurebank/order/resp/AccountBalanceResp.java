package com.futurebank.order.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@ApiModel(value = "AccountBalanceResp", description = "账户资金查询响应")
public class AccountBalanceResp implements Serializable {
    private static final long serialVersionUID = -8086542429703429777L;

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
     * 商戶id
     */
    @ApiModelProperty(value = "商戶id")
    private Long merchantId;

    /**
     * 主钱包状态
     */
    @ApiModelProperty(value = "主钱包状态")
    private Integer istate;

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
     * 可用余额（账户余额-冻结余额）
     */
    @ApiModelProperty(value = "可用余额（收单余额钱包+收付款钱包）")
    private BigDecimal  availableBalance;

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
    @ApiModelProperty(value = "在途资金总额")
    private BigDecimal pendingBalanceTotal;

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


}
