package com.futurebank.order.enums;

import lombok.Getter;

/**
 * 参与对像
 */
public enum OptTypeEnum {

    /**
     * 支付方
     */
    RECV("recv", "接收方"),

    /**
     * 付款方
     */
    PAYER("payer", "付款方"),

    /**
     * 第三方
     */
    THREE("three", "第三方");

    @Getter
    private final String code;

    @Getter
    private final String description;

    OptTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static OptTypeEnum fromCode(String code) {
        for (OptTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }

    @Override
    public String toString() {
        return "FieldType{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
