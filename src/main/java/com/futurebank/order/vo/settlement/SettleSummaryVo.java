package com.futurebank.order.vo.settlement;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SettleSummaryVo {
    private String currency;
    private BigDecimal transactionAmount;
}
