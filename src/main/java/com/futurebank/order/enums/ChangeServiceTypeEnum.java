package com.futurebank.order.enums;

public enum ChangeServiceTypeEnum {

    ACQUIRING_SERVICE("acquiring service", "资金获取服务"),

    PAYMENT("payment", "付款"),

    COLLECTION("collection", "收款"),

    EXCHANGE("exchange", "兑换"),

    DISBURSEMENT("disbursement", "付款"),

    /* 下面个枚举兼容 生成环境 脏数据的问题*/
    PAYMENT_TYPE("付款", "付款手续费"),
    COLLECTION_TYPE("收款", "收款手续费");

    private final String code;
    private final String description;

    ChangeServiceTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }



}
