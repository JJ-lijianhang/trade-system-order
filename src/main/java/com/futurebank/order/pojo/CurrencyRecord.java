package com.futurebank.order.pojo;

import lombok.Data;

/**
 * @author ben
 * @date 2024/5/31 17:55
 **/
@Data
public class CurrencyRecord {
    private String currency;
    private Double amount;
    private Double merchantFixFee;
    private Double merchantPercentageFee;
    private Double upstreamFee;
    private Double markup;

    // Constructors, getters and setters
    public CurrencyRecord(String currency, Double amount, Double merchantFixFee, Double merchantPercentageFee, Double upstreamFee, Double markup) {
        this.currency = currency;
        this.amount = amount;
        this.merchantFixFee = merchantFixFee;
        this.merchantPercentageFee = merchantPercentageFee;
        this.upstreamFee = upstreamFee;
    }

    // Getters and setters...

    @Override
    public String toString() {
        return "CurrencyRecord{" +
                "currency='" + currency + '\'' +
                ", amount=" + amount +
                ", merchantFixFee=" + merchantFixFee +
                ", merchantPercentageFee=" + merchantPercentageFee +
                ", upstreamFee=" + upstreamFee +
                '}';
    }
}

