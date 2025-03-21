package com.futurebank.order.enums;

/**
 * 交易类型
 */
public enum BizTradeType {
    RECEIPT("0101", "收单"),
    RECEIPT_SUCCESS_NOTIFICATION("010101", "【收单】收单成功-在途结算金额"),
    RECEIPT_SUCCESS_SETTLEMENT("010102", "【收单退款-结算前】退单退款款交易"),
    RECEIPT_SETTLEMENT_AMOUNT("010103", "【收单结算成功】收单结算金额"),
    REFUND_AFTER_SETTLEMENT("010104", "【收单退款-结算后】退单退款款交易"),
    PENDING_SETTLEMENT_TRANSFER_DECLINED("010105", "【收单成功-结算】收单结算 - 未达到最低结算金额"),
    PENDING_SETTLEMENT_TRANSFER_COMPLETED("010106", "【收单退款-结算后】收单结算 - 达到最低结算金额释放"),
    COLLECTION("0201", "收款"),
    ONE_TIME_ACTIVATION_FEE("020101", "【开户成功】收取一次性开通费用"),
    MONTHLY_FEE("020102", "【收款账户】收取月费"),
    FUNDS_PENDING_CREDIT_NOTIFICATION("020103", "【资金待入账】FP被通知到账通知商户"),
    FUND_CREDIT_SUCCESS_NOTIFICATION("020104", "【收款成功】资金入账成功通知商户"),
    COLLECTION_FAILURE_REFUNDED_NO_FUNDS("020105", "【收款失败-已退回-资金未入账】收取退回手续费"),
    COLLECTION_FAILURE_REFUNDED_WITH_FUNDS("020106", "【收款失败-已退回-资金已入账】收取退回手续费&退资金"),
    COLLECTION_FAILURE_RECALLED_NO_FUNDS("020107", "【收款失败-已召回-资金未入账】收取召回手续费"),
    COLLECTION_FAILURE_RECALLED_WITH_FUNDS("020108", "【收款失败-已召回-资金已入账】收取召回手续费&退资金"),
    PAYMENT("0301", "付款"),
    SUBMIT_PAYMENT("030101", "【提交付款】商户提交付款"),
    PAYMENT_REVIEW("030102", "【付款复核】商户复核"),
    PAYMENT_REJECTION("030103", "【复核拒绝】付款拒绝"),
    PAYMENT_FAILURE("030104", "【付款失败】付款失败"),
    TRANSACTION_FAILURE_REFUNDED("030105", "【付款交易失败-已退回】交易失败-退回"),
    TRANSACTION_FAILURE_RECALLED("030106", "【付款交易失败-已召回】交易失败-召回"),
    TRANSACTION_SUCCESS("030107", "【交易成功】交易成功"),
    EXCHANGE_BUSINESS("0401", "换汇业务"),
    REAL_TIME_EXCHANGE("040101", "【实时换汇】主动提交换汇"),
    TRIGGERED_EXCHANGE("040102", "【委托换汇】委托触发汇率触发换汇"),
    EXCHANGE_SUCCESS("040103", "【换汇成功】换汇成功"),
    EXCHANGE_FAILURE("040104", "【换汇失败】换汇失败"),
    PLATFORM_TRANSFER_BUSINESS("0501", "平台转账业务"),
    INITIATE_TRANSFER("050101", "【转账-付方】发起转账"),
    TRANSFER_REVIEW_FAILURE("050102", "【转账-付方】复核失败"),
    TRANSFER_SUCCESS_NOTIFICATION("050103", "【转账-收方】到账成功");

    private final String code;
    private final String description;

    BizTradeType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static BizTradeType fromCode(String code) {
        for (BizTradeType type : BizTradeType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown business type code: " + code);
    }
}
