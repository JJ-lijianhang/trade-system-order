package com.futurebank.order.vo.Invoice;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SummaryForCurrencyVo {

    private int pos;
    private int index;
    private String item;
    private BigDecimal amount;
    private String amountStr;
    private String currency;
    /* 转结算*/
    private String minSettleAmount;
    /**
     * 是否下划线
     */
    private String borderBottom;
    private String fontWeight;
}
