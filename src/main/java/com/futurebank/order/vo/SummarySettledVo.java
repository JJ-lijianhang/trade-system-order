package com.futurebank.order.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SummarySettledVo {
    private Long merchantId;
    private String settlementId;
    private Long providerId;
    private String providerName;
    private String paymentMethod;
    private BigDecimal totalSettlementMoney;
}
