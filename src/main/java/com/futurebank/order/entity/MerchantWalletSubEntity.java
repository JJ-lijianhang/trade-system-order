package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantWalletSub", description = "商户资金账户表-子表")
public class MerchantWalletSubEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3343675622349628456L;

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
     * 钱包名称
     */
    @ApiModelProperty(value = "钱包名称", example = "在途收款钱包")
    private String walletName;


    /**
     * 账户类型; pay：在途支付；payIn:在途入账；settlement：在途结算
     */
    @ApiModelProperty(value = "账户类型; pay：在途支付；payIn:在途入账；settlement：在途结算", required = true, example = "pay")
    private String walletType;

    /**
     * 钱包编码
     */
    @ApiModelProperty(value = "钱包编码", required = true, example = "pending:pay;pending:payIn;pending:settlement;")
    private String walletCode;

    /**
     * 货币编码
     */
    @ApiModelProperty(value = "货币编码", required = true, example = "CNY")
    private String currency;

    /**
     * 账户余额
     */
    @ApiModelProperty(value = "账户余额", required = true, example = "0.000000")
    private BigDecimal balance;

    /**
     * 扩展字段
     */
    @ApiModelProperty(value = "扩展字段1", example = "extra_info1")
    private String extFields1;

    /**
     * 扩展字段
     */
    @ApiModelProperty(value = "扩展字段2", example = "extra_info2")
    private String extFields2;

    /**
     * 扩展字段
     */
    @ApiModelProperty(value = "扩展字段3", example = "extra_info3")
    private String extFields3;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", example = "描述")
    private String desc;

}
