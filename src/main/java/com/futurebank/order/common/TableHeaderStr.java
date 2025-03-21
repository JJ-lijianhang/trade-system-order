package com.futurebank.order.common;

/**
 * 表头常量定义
 */
public class TableHeaderStr {
    // 常量定义
    public static final String FIELD_LEGAL_ENTITY_NAME = "Legal Entity Name";
    public static final String FIELD_MERCHANT_ID = "Merchant ID";
    public static final String FIELD_TIME_ZONE = "Time Zone";
    public static final String FIELD_DATE_RANGE = "Date Range";
    public static final String FIELD_FROM_DATE = "From Date";
    public static final String FIELD_TO_DATE = "To Date";


    // 常量 - 账变记录
    public static final String BALANCE_REFERENCE = "Reference";
    public static final String BALANCE_CREATED_DATE = "Created Date";
    public static final String BALANCE_COMPLETED_DATE = "Completed Date";
    public static final String BALANCE_TYPE = "Type";
    public static final String BALANCE_CURRENCY  = "Currency";
    public static final String BALANCE_DEBIT = "Debit";
    public static final String BALANCE_CREDIT = "Credit";
    public static final String BALANCE_BALANCE = "Balance";



    // 常量 - 收款记录
    public static final String COLLECTION_REFERENCE = "Reference";
    public static final String COLLECTION_CREATED_DATE = "Created Date";
    public static final String COLLECTION_COMPLETED_DATE = "Completed Date";
    public static final String COLLECTION_STATUS = "Status";
    public static final String COLLECTION_TYPE = "Type";
    public static final String COLLECTION_CURRENCY = "Currency";
    public static final String COLLECTION_RECEIVED_AMOUNT = "Received Amount";
    public static final String COLLECTION_RECEIVED_CURRENCY = "Received Currency";
    public static final String COLLECTION_RATE = "Rate";
    public static final String COLLECTION_NET_AMOUNT = "Net Amount";
    public static final String COLLECTION_NET_AMOUNT_CURRENCY = "Net Amount Currency";
    public static final String COLLECTION_FEE = "Fee";
    public static final String COLLECTION_FEE_CURRENCY = "Fee Currency";
    public static final String COLLECTION_PAYER = "Payer";
    public static final String COLLECTION_PAYER_ADDRESS = "Payer Address";
    public static final String COLLECTION_ACCOUNT_NO_IBAN = "Account No/IBAN";
    public static final String COLLECTION_BANK_NAME = "Bank Name";
    public static final String COLLECTION_BANK_ADDRESS = "Bank Address";
    public static final String COLLECTION_SWIFT_CODE = "SWIFT Code";
    public static final String COLLECTION_ABA_ROUTING_NUMBER = "ABA Routing Number";
    public static final String COLLECTION_NOTE_TO_SELF = "Note to Self";
    public static final String COLLECTION_BALANCE = "Balance";

    // 常量 - 付款记录
    public static final String PAYMENT_REFERENCE = "Reference";
    public static final String PAYMENT_CREATED_DATE = "Created Date";
    public static final String PAYMENT_COMPLETED_DATE = "Completed Date";
    public static final String PAYMENT_STATUS = "Status"; // 新增
    public static final String PAYMENT_DEDUCTION_AMOUNT = "Deduction amount"; // 新增
    public static final String PAYMENT_DEDUCTION_CURRENCY = "Deduction Currency"; // 新增
    public static final String PAYMENT_PAYMENT_AMOUNT = "Payment Amount"; // 新增
    public static final String PAYMENT_PAYMENT_CURRENCY = "Payment Currency"; // 新增
    public static final String PAYMENT_FEE = "Fee"; // 新增
    public static final String PAYMENT_FEE_CURRENCY = "Fee Currency"; // 新增
    public static final String PAYMENT_RATE = "Rate"; // 新增
    public static final String PAYMENT_PAYMENT_METHOD = "Payment Method"; // 新增
    public static final String PAYMENT_FEE_RESPONSIBILITY = "Fee Responsibility"; // 新增
    public static final String PAYMENT_PAYER = "Payer"; // 新增
    public static final String PAYMENT_BENEFICIARY_TYPE = "Beneficiary Type"; // 新增
    public static final String PAYMENT_ACCOUNT_NO_IBAN = "Account No/IBAN"; // 新增
    public static final String PAYMENT_BENEFICIARY_NAME = "Beneficiary Name"; // 新增
    public static final String PAYMENT_BENEFICIARY_ADDRESS = "Beneficiary Address"; // 新增
    public static final String PAYMENT_BANK_NAME = "Bank Name"; // 新增
    public static final String PAYMENT_BANK_ADDRESS = "Bank Address"; // 新增
    public static final String PAYMENT_SWIFT_CODE = "SWIFT Code"; // 新增
    public static final String PAYMENT_ABA_ROUTING_NUMBER = "ABA Routing Number"; // 新增
    public static final String PAYMENT_PAYMENT_REASON = "Payment Reason"; // 新增
    public static final String PAYMENT_NOTE_TO_SELF = "Note to Self"; // 新增


    // 换汇
    public static final String EXCHANGE_REFERENCE = "Reference";
    public static final String EXCHANGE_CREATED_DATE = "Created Date";
    public static final String EXCHANGE_COMPLETED_DATE = "Completed Date";
    public static final String EXCHANGE_STATUS = "Status";
    public static final String EXCHANGE_SOLD = "Sold";
    public static final String EXCHANGE_SOLD_CURRENCY = "Sold Currency";
    public static final String EXCHANGE_BOUGHT = "Bought";
    public static final String EXCHANGE_BOUGHT_CURRENCY = "Bought Currency";
    public static final String EXCHANGE_RATE = "Rate";
    public static final String EXCHANGE_TYPE = "Type";


    // 转账
    public static final String TRANSFER_REFERENCE = "Reference";
    public static final String TRANSFER_CREATED_DATE = "Created Date";
    public static final String TRANSFER_COMPLETED_DATE = "Completed Date";
    public static final String TRANSFER_STATUS = "Status";
    public static final String TRANSFER_TYPE = "Type";
    public static final String TRANSFER_TARGET_ACCOUNT = "Target Account";
    public static final String TRANSFER_TRANSFER_AMOUNT = "Tranfer Amount";
    public static final String TRANSFER_TRANSFER_CURRENCY = "Tranfer Currency";
    public static final String TRANSFER_FEE_AMOUNT = "Fee Amount";
    public static final String TRANSFER_FEE_CURRENCY = "Fee Currency";
    public static final String TRANSFER_RATE = "Rate";


    // 收单交易
    public static final String AQT_REFERENCE = "Reference";
    public static final String AQT_CREATED_DATE = "Created Date";
    public static final String AQT_COMPLETED_DATE = "Completed Date";
    public static final String AQT_NOTIFY_DATE = "Notify Date";
    public static final String AQT_STATUS = "Status";
    public static final String AQT_PAYMENT_METHOD = "Payment Method";
    public static final String AQT_PAYMENT_TYPE = "Payment Type";
    public static final String AQT_COUNTRY_REGION = "Country/Region";
    public static final String AQT_TRANSACTION = "Transaction";
    public static final String AQT_TRANSACTION_CURRENCY = "Transaction Currency";
    public static final String AQT_GATEWAY_FEE = "Gateway Fee";
    public static final String AQT_GATEWAY_CURRENCY = "Gateway Currency";
    public static final String AQT_FIXED_FEE = "Fixed Fee";
    public static final String AQT_FIXED_FEE_CURRENCY = "Fixed Fee Currency";
    public static final String AQT_DISCOUNT_FEE = "Discount Fee";
    public static final String AQT_DISCOUNT_FEE_CURRENCY = "Discount Fee Currency";
    public static final String AQT_FUND_STATUS = "Fund Status";
    public static final String AQT_ESTIMATED_SETTLEMENT_DATE = "Estimated Settlement Date";
    public static final String AQT_ERROR_DESCRIPTION = "Error Description";


    // 收单退款
    public static final String AQR_REFERENCE = "Reference";
    public static final String AQR_SOURCE_REFERENCE = "Source Reference";
    public static final String AQR_CREATED_DATE = "Created Date";
    public static final String AQR_COMPLETED_DATE = "Completed Date";
    public static final String AQR_NOTIFY_DATE = "Notify Date";
    public static final String AQR_STATUS = "Status";
    public static final String AQR_PAYMENT_METHOD = "Payment Method";
    public static final String AQR_PAYMENT_TYPE = "Payment Type";
    public static final String AQR_COUNTRY_REGION = "Country/Region";
    public static final String AQR_TRANSACTION = "Transaction";
    public static final String AQR_TRANSACTION_CURRENCY = "Transaction Currency";
    public static final String AQR_REFUND_FEE = "Refund Fee";
    public static final String AQR_REFUND_FEE_CURRENCY = "Refund Fee Currency";
    public static final String AQR_FUND_STATUS = "Fund Status";
    public static final String AQR_SETTLEMENT_STATUS = "Settlement Status";
    public static final String AQR_REASON = "Reason";
    public static final String AQR_REFUND_ERROR_DESCRIPTION = "Refund Error Description";


    // 收单拒付
    public static final String AQRJ_REFERENCE = "Reference";
    public static final String AQRJ_SOURCE_REFERENCE = "Source Reference";
    public static final String AQRJ_CREATED_DATE = "Created Date";
    public static final String AQRJ_COMPLETED_DATE = "Completed Date";
    public static final String AQRJ_STATUS = "Status";
    public static final String AQRJ_PAYMENT_METHOD = "Payment Method";
    public static final String AQRJ_PAYMENT_TYPE = "Payment Type";
    public static final String AQRJ_COUNTRY_REGION = "Country/Region";
    public static final String AQRJ_TRANSACTION = "Transaction";
    public static final String AQRJ_TRANSACTION_CURRENCY = "Transaction Currency";
    public static final String AQRJ_CHARGEBANK_FEE = "Chargebank Fee";
    public static final String AQRJ_CHARGEBANK_FEE_CURRENCY = "Chargebank Fee Currency";
    public static final String AQRJ_FUND_STATUS = "Fund Status";
    public static final String AQRJ_SETTLEMENT_STATUS = "Settlement Status";
    public static final String AQRJ_REASON = "Reason";



    public static final String DATE_RANGE_CREATED_DATE = "Created Date";

}
