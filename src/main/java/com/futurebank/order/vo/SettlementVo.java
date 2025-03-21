package com.futurebank.order.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author ben
 * @date 2024/6/7 20:04
 **/
@Data
public class SettlementVo {
    private Long providerId;
    private String providerName;
    private String MERCHANTTXID;        // platformOrderNo
    private String TXID;     // orderStatus
    private String REFERENCETXID;       // orderType
    private String TXSTATE;         // country
    private String TAG; // merchantOrderAmount
    private String ORDERTYPE; // merchantCurrency
    private String COUNTRYCODE;    // paymentMethod
    private String CURRENCY;// downstreamOrderNo
    private String FEETYPE;        // merchantId
    private String AMOUNT;   // orderCreateTime
    private String MERCHANID; // orderCompleteTime
    private Date EVENTTIMESTAMP; //reference_order_no

}
