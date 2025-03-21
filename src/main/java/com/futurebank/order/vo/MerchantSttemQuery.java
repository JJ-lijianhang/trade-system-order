package com.futurebank.order.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "MerchantSttemQuery", description = "商户对账查询")
public class MerchantSttemQuery implements Serializable {
    private static final long serialVersionUID = -6029236815069004121L;

    /**
     * 商户ID
     */
    private Long merchantId;

    private Long providerId;

    private String providerNameStr;

    private List<Long> providerIdList;

    private String paymentMethod;

    private String paymentMethodType;

    /**
     * 商户结算日期
     */
    private String estimatedSettlementDate;

    /**
     * 结算日期 20241212
     */
    private String settlementDate;

    /**
     * 商户对账日期
     */
    private String reconDate;

    private int settlementCycle;

    private String countryCode;

    private String transactionCurrency;

    private String createTimeB;

    private String createTimeE;

    /**
     * 商户对账日期配置
     */
    private Date settlementDateConfig;
    private Date reconDateConfig;
}
