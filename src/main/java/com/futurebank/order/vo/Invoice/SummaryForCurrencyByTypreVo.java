package com.futurebank.order.vo.Invoice;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 支付方式汇总
 */
@Data
public class SummaryForCurrencyByTypreVo {

    private int pos;
    private int index;

    private int type;

    private String item;

    private String numVol;

    private String feeRate;

    private BigDecimal merchant;
    private String merchantStr;

    private BigDecimal client;
    private String clientStr;

    private String currency;

    /**
     * 是否下划线
     */
    private String borderBottom;
    private String fontWeight;


}