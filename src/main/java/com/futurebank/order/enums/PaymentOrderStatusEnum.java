package com.futurebank.order.enums;

public enum PaymentOrderStatusEnum {
    /** 交易处理中 */
    PENDING("交易处理中"),

    /** 交易失败 */
    FAILED("交易失败"),

    /** 交易成功 */
    SUCCEED("交易成功"),

    /** 交易已取消 */
    CANCEL("交易已取消"),

    /** 交易已过期 */
    EXPIRED("交易已过期"),

    /** 交易被拒绝 */
    REFUSED("交易被拒绝"),

    /** 退款处理中 */
    REFUND_PENDING("退款处理中"),

    /** 已退款 */
    REFUNDED("已退款"),

    /** 退款失败 */
    REFUND_FAILED("退款失败"),

    /** 退款撤销 */
    REFUND_REVOKE("退款撤销"),

    /** 退款拒绝 */
    REFUND_REFUSED("退款拒绝"),

    /** 拒付 */
    CHARGEBACK("拒付"),

    /** 拒付撤销 */
    CHARGEBACK_REVERSED("拒付撤销"),

    /** 争议 */
    DISPUTE("争议");

    private final String description;

    /**
     * Constructor for the enumeration.
     *
     * @param description the description of the status
     */
    PaymentOrderStatusEnum(String description) {
        this.description = description;
    }

    /**
     * Get the description of the status.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

}
