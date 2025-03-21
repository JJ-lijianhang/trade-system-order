package com.futurebank.order.vo.Report;

import lombok.Data;

@Data
public class PaymentMethodTypeVo {
    private String id;
    private String paymentType;
    private String childrenPaymentType;
}
