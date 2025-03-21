package com.futurebank.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ben
 * @date 2024/6/7 20:04
 **/
@Data
public class SettlementAggergateVo {

    private Long providerId;
    private String providerName;
    private String settlementId; // 应与 SETTLEMENTID 匹配
    private Long merchantId;      // 应与 MERCHANT_ID 匹配
    private String paymentMethod;  // 应与 PAYMENT_METHOD 匹配
    private String eventType;        // 应与 EVENT_TYPE 匹配
    private Long eventCount;       // 应与 EVENT_COUNT 匹配
    private BigDecimal aggregateAmount; // 应与 AGGREGATE_AMOUNT 匹配
    private String currency;        // 应与 CURRENCY 匹配

}
