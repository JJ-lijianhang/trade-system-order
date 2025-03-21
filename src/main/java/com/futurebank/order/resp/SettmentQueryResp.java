package com.futurebank.order.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class SettmentQueryResp implements Serializable {

    private String merchantId;

    private String merchantName;

    private String registerCountryName;

    private String registerCountryCode;

    private String registerNameEn;

    private String ciCode;

    /**
     * 账期
     */
    private String Period;

    /**
     * 结算周期
     */
    private int settlementCycle;

    /**
     * 账期范围
     */
    private String billingPeriod;

    /**
     * 发票号码
     */
    private String invoiceNumber;

    private String support = "support@futurebank.global";




}
