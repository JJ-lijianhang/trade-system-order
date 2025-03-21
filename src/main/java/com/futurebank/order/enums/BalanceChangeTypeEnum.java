package com.futurebank.order.enums;

import lombok.Getter;

public enum BalanceChangeTypeEnum {

    COLLECTION("Collection", "收款"),
    COLLECTION_FEE("Collection Fee", "收款手续费"),
    PAYMENT("Payment", "付款"),
    PAYMENT_FEE("Payment Fee", "付款手续费"),
    TRANSFER("Transfer", "转账"),
    TRANSFER_FEE("Transfer Fee", "转账手续费"),
    CONVERT("Convert", "换汇"),
    CONVERT_FEE("Convert Fee", "换汇手续费"),
    ACQUIRING_SETTLEMENT("Acquiring Settlement", "收单结算金额");

    @Getter
    private final String code;

    @Getter
    private final String description;

    BalanceChangeTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static BalanceChangeTypeEnum getByCode(String code) {
        for (BalanceChangeTypeEnum type : BalanceChangeTypeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }


}
