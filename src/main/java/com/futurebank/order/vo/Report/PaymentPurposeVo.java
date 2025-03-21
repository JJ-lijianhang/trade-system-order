package com.futurebank.order.vo.Report;

import lombok.Data;

@Data
public class PaymentPurposeVo {
    private Long id;
    private String paymentName;
    private String flag;
    private String createdTime;
    private String updatedTime;
    private String operator;
    private String translations;
    private String code;
    private String payerType;
}
