package com.futurebank.order.enums;

import lombok.Getter;

@Getter
public enum ProviderSettleBatchEnum {

    KLASHA("klasha", "Klasha Technologies Ltd", "NIGERIA", "NIGERIA", "尼日利亚假期不结算"),

    KGP("kgp", "KASIKORN GLOBAL PAYMENT", "THAILAND", "THAILAND", "泰国假期不结算"),

    PPRO("ppro", "PPRO", "UNITED_KINGDOM", "UNITED_KINGDOM", "英国假期不结算"),

    ALIPAYPLUS("AlipayPlus", "AlipayPlus", "CHINA", "CHINA", "中国大陆假期不结算"),

    EASYLINK("Easylink", "Easylink", "HONG_KONG", "HONG_KONG", "中国香港假期不结算"),

    NMI("nmi", "Network Merchants Inc.", "UNITED_STATES", "UNITED_STATES", "美国假期不结算"),

    PAYSTORY("Paystory", "Paystory", "SOUTH_KOREA", "SOUTH_KOREA", "韩国假期不结算"),

    ;

    private final String providerCode;
    private final String providerName;
    private final String CountryCode;
    private final String CountryName;
    private final String description;
    ProviderSettleBatchEnum(String providerCode, String providerName, String CountryCode, String CountryName, String description) {
        this.providerCode = providerCode;
        this.providerName = providerName;
        this.CountryCode = CountryCode;
        this.CountryName = CountryName;
        this.description = description;
    }

    public static ProviderSettleBatchEnum getByCode(String code) {
        for (ProviderSettleBatchEnum providerSettleBatchEnum : ProviderSettleBatchEnum.values()) {
            if (providerSettleBatchEnum.getProviderCode().equals(code)) {
                return providerSettleBatchEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ProviderSettleBatchEnum{" +
                "providerCode='" + providerCode + '\'' +
                ", providerName='" + providerName + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", CountryName='" + CountryName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
