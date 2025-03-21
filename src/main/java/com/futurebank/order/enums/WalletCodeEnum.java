package com.futurebank.order.enums;

import lombok.Getter;

public enum WalletCodeEnum {

    ACQUIRING_BALANCE("acquiring_balance", 1,"收单余额（收单钱包）"),
    COLLECTION_BALANCE("collection_balance", 2,"收款余额（收单钱包）"),
    RECHARGE_BALANCE("recharge_balance",3, "充值余额（充值钱包）"),
    FREEZE_AMOUNT("freeze_amount", 4,"冻结金额"),
    OTHER("other",5, "其他"),
    WITHDRAWAL_AMOUNT("withdrawal_amount",6, "提现总额"),

    // 扩展
    PENDING("pending_",20, "在途资金变更"),


    /*方案, 准备废弃*/
    PENDING_PAYIN("pending_payIn",7, "在途入账资金"),
    PENDING_SETTLEMENT("pending_settlement", 8,"在途结算资金"),
    PENDING_PAYING("pending_paying", 9,"在途支付资金"),
    PENDING_EXCHANGE("exchange_pending",10, "在途换汇资金"),
    PENDING_PAYER_CHECK("pending_payer_check",11, "付款复核"),

    /* 转结算金额*/
    PENDING_SETTLEMENT_TRANSFER("pending_settlement_transfer",12, "转结算金额"),



    ;


    @Getter
    private final String code;

    @Getter
    private final int type;

    @Getter
    private final String description;

    WalletCodeEnum(String code,int type, String description) {
        this.code = code;
        this.type = type;
        this.description = description;
    }


    public static WalletCodeEnum fromCode(String code) {
        for (WalletCodeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown field name: " + code);
    }

    @Override
    public String toString() {
        return "BalanceType{" +
                "code='" + code + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
