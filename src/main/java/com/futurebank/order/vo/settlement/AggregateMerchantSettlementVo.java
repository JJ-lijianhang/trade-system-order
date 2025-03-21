package com.futurebank.order.vo.settlement;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AggregateMerchantSettlementVo {
    private Long providerId;
    private String merchantCurrency;
    private BigDecimal realAmount;
    private BigDecimal transactionAmount;
    private BigDecimal rateFee;
}
