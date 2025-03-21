package com.futurebank.order.enums;

import lombok.Getter;

/**
 * 结算记账的费用类型
 */
public enum FeeTypeCodeEnum {

    SUMMARY_FOR_CURRENCY(0,"Summary For Currency ", "Summary For Currency "),
    STARTING_BALANCE(1,"Starting balance ", "Starting balance "),
    ENDING_BALANCE(100,"Ending balance ", "Ending balance "),
    REFUND(20,"Refund volume", "退款"),
    FX_SALE(30,"Amount converted From ", "换汇卖出"),
    /* 这里说明一下
     *  tb_merchant_settlement_aggregate 这个表的 Fee type
     *
     *  TRANSACTION          ------》 对应发票  Total volume
     *  DISCOUNT_FEE    （ ACQUIRING_FEE  ）     ------>  对应发票  Total transaction fees
     *  TOTAL_AMOUNT_DUE     ------>  对应发票  Total amount due
     *  FX_PURCHASE          ------>  对应发票  Total amount due (FX rate, %s: %s )
     *
     * */

    TRANSACTION(2,"Total volume","总交易额") ,
    TOTAL_TRANSACTION_FEES(3,"Total transaction fees", "收单手续费"),
    DISCOUNT_FEE(3,"Total transaction fees", "对应发票-摘要  Total transaction fees"),
    GATEWAY_FEE(4,"Total transaction fees", "对应发票-摘要  Total transaction fees"),
    TOTAL_AMOUNT_DUE(40,"Total amount due","总计"),
    TOTAL_AMOUNT_DUE_FX(41,"Total amount due (FX rate, %s: %s )", "换汇买入"),
    FX_FEE(42,"FX fee", "换汇手续费"),
    TOTAL_AMOUNT_DUE_AFTER(43,"Total amount due","总计"),


    /* 目前未使用，待扩展*/
    TOTAL_VOLUME(2,"Total volume","总交易额"),

    CHARGEBACK0(0,"Total transaction fees", "CHARGEBACK"),
    ACQUIRING_FEE(0,"Acquiring Fee", "收单手续费"),
    REFUND_FEE(0,"Refund Fee", "退款手续费"),
    ;

    @Getter
    private int type;

    @Getter
    private String code;

    @Getter
    private String desc;

    FeeTypeCodeEnum(int type,String code, String desc){
        this.type = type;
        this.code = code;
        this.desc = desc;
    }


    public static FeeTypeCodeEnum getByCode(String name) {
        for (FeeTypeCodeEnum feeTypeCodeEnum : FeeTypeCodeEnum.values()) {
            if (feeTypeCodeEnum.name().equals(name)) {
                return feeTypeCodeEnum;
            }
        }
        return null;
    }




}
