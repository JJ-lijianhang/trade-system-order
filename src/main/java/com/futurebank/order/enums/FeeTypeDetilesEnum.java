package com.futurebank.order.enums;

import lombok.Getter;

public enum FeeTypeDetilesEnum {

    ACQUIRING_FEE(1,"Acquiring fee", "收单手续费"),
    DISCOUNT_FEE(1,"Discount Fee", "百分比手续费"),
    PAYMENT_VOLUME(1,"Payment Volume","交易费用"),
    REFUND_VOLUME(1,"Refund Volume","退款"),
    GATEWAY_FEE(1,"Gateway fee","网关费"),
    REFUND_FEE(1,"Refund fee","交易费用"),
    REFUND(1,"Refund Volume","退款"),
    MINIMUM_FEE(1,"Minimum Discount Fee","最小费用"),
    fixed(1,"fixed","最小费用"),


    TRANSACTION(3,"Payment Volume","总交易额") ,
    TOTAL_TRANSACTION_FEE(2,"Total transaction fees","总交易费用"),
    FX_SALE(2,"Total transaction fees", "换汇卖出"),
    FX_PURCHASE(2,"Total amount due (FX rate, date: fx-rate )", "换汇买入"),
    FX_FEE(2,"Total transaction fees", "换汇手续费"),
    CHARGEBACK(2,"Total transaction fees", "CHARGEBACK"),
    TOTAL_VOLUME(2,"Total volume","总交易额"),
    TOTAL_FEE(2,"Total fees","交易费用"),


    refund(1,"Refund Volume","退款"),

    ;


    @Getter
    private String code;

    @Getter
    private int type;

    @Getter
    private String desc;

    FeeTypeDetilesEnum(int type,String code, String desc){
        this.type = type;
        this.code = code;
        this.desc = desc;
    }


    public static FeeTypeDetilesEnum getByName(String code) {
        for (FeeTypeDetilesEnum feeTypeCodeEnum : FeeTypeDetilesEnum.values()) {
            if (feeTypeCodeEnum.name().equals(code)) {
                return feeTypeCodeEnum;
            }
        }
        return null;
    }
}
