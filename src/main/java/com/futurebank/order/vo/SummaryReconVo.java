package com.futurebank.order.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SummaryReconVo {
    private String reconDate;
    private Long providerId;
    private String providerName;
    private Long merchantId;
    private String paymentMethod;
    private String currency;
    private BigDecimal totalAmount;

}
