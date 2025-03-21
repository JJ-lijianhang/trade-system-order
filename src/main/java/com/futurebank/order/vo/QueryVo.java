package com.futurebank.order.vo;

import lombok.Data;

@Data
public class QueryVo {
    private String merchantId;
    private String merchantName;
    private String currency;
    private String platformName;
    private String paymentMethod;
}
