package com.futurebank.order.vo.Invoice;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MerchantInfoVo implements Serializable {

    private Long merchantId;

    /**
     * 渠道id
     */
    private Long providerId;

    /**
     * 渠道名称
     */
    private String providerName;

    private String merchantName;

    private String currency;

    private String merchantEmail;

    /**
     * 结算模式 (取整型数字)
     */
    private int settlementMethod;

    /**
     * 结算-换汇加点汇率
     */
    private BigDecimal settlementExchangeMargin;

    private String currentSettDate;

    private String reconciliationDate;

    private BigDecimal minNumSettlementMoney;

    private BigDecimal totalSettlementMoney;
}
