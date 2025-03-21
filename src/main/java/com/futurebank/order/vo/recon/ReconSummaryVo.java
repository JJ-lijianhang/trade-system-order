package com.futurebank.order.vo.recon;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReconSummaryVo {
    private String currency;
    private BigDecimal transactionAmount;
    private int transactionCount;
    private BigDecimal successAmount;
    private int successCount;
    private BigDecimal refundAmount;
    private int refundCount;
    private BigDecimal rejectedAmount;
    private int rejectedCount;
    private BigDecimal networkFee;
    private BigDecimal fixedFee;
    private BigDecimal percentageFee;
    private BigDecimal refundFee;
    private BigDecimal rejectedFee;
}
