package com.futurebank.order.resp;

import io.swagger.annotations.ApiModelProperty;

public class TransactionDetailResp {
    /** 交易时间 */
    @ApiModelProperty(value = "Transaction timestamp")
    private String transactionTime;

    /** 交易类型 */
    @ApiModelProperty(value = "Type of transaction (e.g., Exchange, Adjustment, Fee, etc.)")
    private String transactionType;

    /** 交易信息 */
    @ApiModelProperty(value = "Detailed information about the transaction")
    private String transactionInfo;

    /** 交易金额 */
    @ApiModelProperty(value = "Amount involved in the transaction")
    private String transactionAmount;

    /** 可用余额 */
    @ApiModelProperty(value = "Available balance after the transaction")
    private String availableBalance;
}
