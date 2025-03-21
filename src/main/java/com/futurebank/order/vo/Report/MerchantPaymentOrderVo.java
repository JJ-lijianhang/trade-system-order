package com.futurebank.order.vo.Report;

import lombok.Data;

@Data
public class MerchantPaymentOrderVo {

    private String Type;

    private Long merchantId;

    private String tradeNo;

    private String tradeNoType;

    private String currency;

    private String paymentMethod;

}
