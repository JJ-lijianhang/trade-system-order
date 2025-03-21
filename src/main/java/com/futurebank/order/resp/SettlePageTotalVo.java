package com.futurebank.order.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SettlePageTotalVo {
    /** 交易金额 */
    @ApiModelProperty(value = "Transaction amount")
    private List<AmountVo> transactionAmount;
}
