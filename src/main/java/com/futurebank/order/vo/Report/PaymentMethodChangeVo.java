package com.futurebank.order.vo.Report;

import lombok.Data;

import java.util.List;

@Data
public class PaymentMethodChangeVo {
    private String platformName;
    private String paymentMethod;

    /*
    *  query list
    * */
    private List<String> methodList;
}
