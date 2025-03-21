package com.futurebank.order.enums;

public enum ReportType {
    SETTLEMENT("settle", "结算"),
    RECONCILIATION("reconciliation", "对账"),
    TRANSACTION("transaction", "交易"),
    REFUND("refund", "退款"),
    INVOICE("invoice", "发票");


    private final String code;
    private final String description;

    ReportType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ReportType fromCode(String code) {
        for (ReportType type : ReportType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown report type code: " + code);
    }
}
