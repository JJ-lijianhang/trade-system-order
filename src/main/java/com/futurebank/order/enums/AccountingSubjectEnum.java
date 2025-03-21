package com.futurebank.order.enums;

import java.util.HashMap;
import java.util.Map;

public enum AccountingSubjectEnum {
    // 付款金额相关
    PAYMENT_AMOUNT(1000101, "付款金额"),
    PAYMENT_AMOUNT_FEE(1000102, "付款手续费"),
    PAYMENT_AMOUNT_FX_FEE(1000103, "付款手续费-固定"),
    PAYMENT_AMOUNT_DC_FEE(1000104, "付款手续费-百分比"),
    RT_PAYMENT_AMOUNT(1000201, "付款失败-退回"),
    RT_PAYMENT_AMOUNT_FEE(1000202, "付款失败-手续费-退回"),
    RT_PAYMENT_AMOUNT_AQ_FEE(1000203, "付款失败-手续费固定-退回"),
    RT_PAYMENT_AMOUNT_DC_FEE(1000204, "付款失败-手续费百分比-退回"),
    PAY_ONETIMES_FEE(1000301, "付款一次性手续费"),
    PENDING_PAYING(1009901, "在途支付金额"),
    RT_PENDING_PAYING(1009902, "在途支付金额-划转"),
    PENDING_PAYING_CHECK(1009801, "商户复核"),

    // 收款金额相关
    PAYEE_AMOUNT(2000101, "收款金额"),
    PAYEE_AMOUNT_FEE(2000102, "收款手续费"),
    PAYEE_AMOUNT_AQ_FEE(2000103, "收款手续费-固定"),
    PAYEE_AMOUNT_DC_FEE(2000104, "收款手续费-百分比"),
    RT_PAYEE_AMOUNT(2000201, "收款失败-退回"),
    RT_PAYEE_AMOUNT_FEE(2000202, "收款失败-手续费-退回"),
    RT_PAYEE_AMOUNT_AQ_FEE(2000203, "收款失败-手续费固定-退回"),
    RT_PAYEE_AMOUNT_DC_FEE(2000204, "收款失败-手续费百分比-退回"),
    RB_PAYEE_AMOUNT(2000301, "收款失败-召回"),
    RB_PAYEE_AMOUNT_FEE(2000302, "收款失败-手续费-召回"),
    RB_PAYEE_AMOUNT_AQ_FEE(2000303, "收款失败-手续费固定-召回"),
    RB_PAYEE_AMOUNT_DC_FEE(2000304, "收款失败-手续费百分比-召回"),
    RECV_ONETIMES_FEE(2000401, "收款一次性手续费"),
    PENDING_RECV(2009901, "在途收款金额"),
    RT_PENDING_RECV(2009902, "在途收款金额-划转"),

    // 换汇金额相关
    EX_CHANGE_AMOUNT_SU(3000101, "换汇金额"),
    EX_CHANGE_AMOUNT_FEE_SU(3000102, "换汇手续费"),
    EX_CHANGE_AMOUNT_AQ_FEE_SU(3000103, "换汇手续费-固定"),
    EX_CHANGE_AMOUNT_DC_FEE_SU(3000104, "换汇手续费-百分比"),
    RB_EX_CHANGE_AMOUNT_SU(3000201, "换汇金额-退回"),
    RB_EX_CHANGE_AMOUNT_FEE_SU(3000202, "换汇手续费-退回"),
    RB_EX_CHANGE_AMOUNT_AQ_FEE_SU(3000203, "换汇手续费-固定-退回"),
    RB_EX_CHANGE_AMOUNT_DC_FEE_SU(3000204, "换汇手续费-百分比-退回"),
    EX_CHANGE_AMOUNT_TG(3000301, "换汇金额"),
    EX_CHANGE_AMOUNT_FEE_TG(3000302, "换汇手续费"),
    EX_CHANGE_AMOUNT_AQ_FEE_TG(3000303, "换汇手续费-固定"),
    EX_CHANGE_AMOUNT_DC_FEE_TG(3000304, "换汇手续费-百分比"),
    RB_EX_CHANGE_AMOUNT_TG(3000401, "换汇金额-退回"),
    RB_EX_CHANGE_AMOUNT_FEE_TG(3000402, "换汇手续费-退回"),
    RB_EX_CHANGE_AMOUNT_AQ_FEE_TG(3000403, "换汇手续费-固定-退回"),
    RB_EX_CHANGE_AMOUNT_DC_FEE_TG(3000404, "换汇手续费-百分比-退回"),
    PENDING_EXCHANGE(3009901, "在途换汇金额"),
    RT_PENDING_EXCHANGE(3009902, "在途换汇金额-退回"),

    // 结算金额相关
    SETTLEMENT_AMOUNT(4000101, "结算金额"),
    SETTLEMENT_AMOUNT_FEE(4000102, "结算手续费"),
    SETTLEMENT_AMOUNT_AQ_FEE(4000103, "结算手续费-固定"),
    SETTLEMENT_AMOUNT_DC_FEE(4000104, "结算手续费-百分比"),
    RT_SETTLEMENT_AMOUNT(4000201, "结算金额-退回"),
    RT_SETTLEMENT_AMOUNT_FEE(4000202, "结算手续费-退回"),
    RT_SETTLEMENT_AMOUNT_AQ_FEE(4000203, "结算手续费-固定-退回"),
    RT_SETTLEMENT_AMOUNT_DC_FEE(4000204, "结算手续费-百分比-退回"),
    PENDING_SETTLEMENT(4009901, "在途结算资金"),
    RT_PENDING_SETTLEMENT(4009902, "在途结算资金-划转"),

    // 其他费用相关
    ONE_TIME_ACTIOVATION_FEE(5000101, "【开户成功】收取一次性开通费用"),
    MERCHANT_MONTHLY_FEE(5000201, "【月费】循环月费");

    private static final Map<Integer, AccountingSubjectEnum> CODE_MAP = new HashMap<>();
    private static final Map<String, AccountingSubjectEnum> NAME_MAP = new HashMap<>();

    static {
        for (AccountingSubjectEnum code : AccountingSubjectEnum.values()) {
            CODE_MAP.put(code.code, code);
            NAME_MAP.put(code.description, code);
        }
    }


    private final Integer code;
    private final String description;

    AccountingSubjectEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static AccountingSubjectEnum valueOfCode(int code) {
        AccountingSubjectEnum result = CODE_MAP.get(code);
        if (result == null) {
            throw new IllegalArgumentException("No matching TransactionCode for code: " + code);
        }
        return result;
    }

    public static AccountingSubjectEnum valueOfName(String name) {
        AccountingSubjectEnum result = NAME_MAP.get(name);
        if (result == null) {
            throw new IllegalArgumentException("No matching TransactionCode for name: " + name);
        }
        return result;
    }


    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
