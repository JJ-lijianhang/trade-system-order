package com.futurebank.order.enums;

import lombok.Getter;

/**
 * 账户方向
 */
public enum AccountSide {

    /**
     * 规则：
     *  借方账户-账户资金增加
     */
    DEBIT("D",  "借方账户"),

    /**
     * 规则：
     *  贷方账户-账户资金减少
     */
    CREDIT("C",  "贷方账户");

    @Getter
    private String code;
    @Getter
    private String description;

    AccountSide(String code, String description) {
        this.code = code;
        this.description = description;
    }



}
