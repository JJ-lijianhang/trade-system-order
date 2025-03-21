package com.futurebank.order.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * AccountWalletRulesVo
 * 钱包规则
 */
@Data
@NoArgsConstructor
public class AccountWalletRulesVo implements Serializable {
    private static final long serialVersionUID = 3826273221524023452L;
    /**
     * 交易代码
     */
    private String tradeCode;

    /**
     * 交易名称
     */
    private String tradeName;

    /**
     * 规则索引
     */
    private Integer index;

    /**
     * 规则标题
     */
    private String title;

    /**
     * 操作对像
     */
    private String optType;

    /**
     * 钱包代码
     */
    private String walletCode;

    /**
     * 计算规则
     */
    private String calculationRules;

    /**
     * 备注规则
     */
    private String remarkRules;

    /**
     * 货币精度保留
     */
    private Integer precision;

    /**
     * 舍入规则
     */
    private Integer roundingRules;

    /**
     * 科目代码
     */
    private Integer subjectCode;

    /**
     * 科目类型
     */
    private String subjectType;

    /**
     * 科目枚举
     */
    private String subjectEnum;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 科目描述
     */
    private String subjectDesc;

}
