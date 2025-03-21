package com.futurebank.order.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author ben
 * @date 2024/6/7 20:04
 **/
@Data
public class ReconciliationVo {
    private Long billId;
    private Long providerId;
    private String TXID;        // platformOrderNo
    private String TXSTATE;     // orderStatus
    private String ORDERTYPE;       // orderType
    private String COUNTRYCODE;         // country
    private String AMOUNT; // merchantOrderAmount
    private String CURRENCY; // merchantCurrency
    private String TAG;    // paymentMethod
    private String MERCHANTTXID;// downstreamOrderNo
    private String referenceTxId; //reference_order_no
    private String MERCHANID;        // merchantId
    private Date INITTS;   // orderCreateTime
    private Date COMPLETETS; // orderCompleteTime
}
