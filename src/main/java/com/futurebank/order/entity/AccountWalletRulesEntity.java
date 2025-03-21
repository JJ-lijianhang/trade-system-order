package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AccountWalletRules", description = "账户系统-账务入账规则")
public class AccountWalletRulesEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6845702276915629170L;

    /**
     * 账户类型-预留
     */
    @ApiModelProperty(value = "账户类型-预留", example = "TYPE_A")
    private String optType;

    /**
     * 规则名称
     */
    @ApiModelProperty(value = "规则名称", example = "规则1")
    private String title;

    /**
     * 交易类型编码
     */
    @ApiModelProperty(value = "交易类型编码", example = "TRADE_001")
    private String tradeTypeCode;

    /**
     * 钱包编码
     */
    @ApiModelProperty(value = "钱包编码", example = "WALLET_001")
    private String walletCode;

    /**
     * 计算规则
     */
    @ApiModelProperty(value = "计算规则", example = "规则表达式")
    private String calculationRules;

    /**
     * 会计科目
     */
    @ApiModelProperty(value = "会计科目", example = "1001")
    private String subjectCode;

    /**
     * 规则优先级
     */
    @ApiModelProperty(value = "规则优先级", example = "1")
    private Integer index;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", example = "规则的详细描述")
    private String desc;

}