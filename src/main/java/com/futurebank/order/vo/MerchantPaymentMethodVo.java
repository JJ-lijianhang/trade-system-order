package com.futurebank.order.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "MerchantPaymentMethodVo", description = "商户支付方式")
public class MerchantPaymentMethodVo {

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 渠道ID
     */
    private Long providerId;

    /**
     * 交易类型
     */
    private String transactionType;

    /**
     * 支付方式
     */
    private String paymentMethod;


}
