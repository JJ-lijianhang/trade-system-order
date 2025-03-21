package com.futurebank.order.enums;

import lombok.Getter;

/**
 * 导出报表类型
 */
public enum ReportBizTypeEnum {
    /**
     * 导出账务变动
     */
    BALANCE_ACTIVITY_REPORT("Balance", "导出账务变动", ""),

    /**
     * 导出收款记录
     */
    COLLECTION_ACTIVITY_REPORT("Collection", "导出收款记录", ""),

    /**
     * 导出付款记录
     */
    PAYMENT_ACTIVITY_REPORT("Payment", "导出付款记录", ""),

    /**
     * 导出换汇记录
     */
    CONVERT_ACTIVITY_REPORT("Convert", "导出换汇记录", ""),

    /**
     * 导出转账记录
     */
    TRANSFER_ACTIVITY_REPORT("Transfer", "导出转账记录", ""),

    /**
     * 导出收单记录
     */
    ACQUIRING_ACTIVITY_TRANSACTION_REPORT("AcquiringTransaction", "导出收单记录", ""),

    /**
     * 导出退款记录
     */
    ACQUIRING_ACTIVITY_REFUND_REPORT("AcquiringRefund", "导出退款记录", ""),

    /**
     * 导出拒付记录
     */
    ACQUIRING_ACTIVITY_CHARGEBACK_REPORT("AcquiringChargeback", "导出拒付记录", "");

    @Getter
    private final String code;
    @Getter
    private final String description;
    @Getter
    private final String notes;

    ReportBizTypeEnum(String code, String description, String notes) {
        this.code = code;
        this.description = description;
        this.notes = notes;
    }

    public static ReportBizTypeEnum fromCode(String code) {
        for (ReportBizTypeEnum bizType : ReportBizTypeEnum.values()) {
            if (bizType.getCode().equals(code)) {
                return bizType;
            }
        }
        return null;
    }

}
