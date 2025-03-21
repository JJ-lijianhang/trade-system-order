package com.futurebank.order.vo.Invoice;

import lombok.Data;

@Data
public class ClearingStatementVo {

    private String legalEntityName;

    private String billingPeriod;

    private String dateStr;

    private String invoiceNumber;

    private String support;
}
