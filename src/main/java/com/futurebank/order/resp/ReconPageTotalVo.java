package com.futurebank.order.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReconPageTotalVo {
    /** 交易金额 */
    @ApiModelProperty(value = "Transaction amount")
    private List<AmountVo> transactionAmount;

    /** 交易笔数 */
    @ApiModelProperty(value = "Number of transactions")
    private int transactionCount;

    /** 成功金额 */
    @ApiModelProperty(value = "Amount of successful transactions")
    private List<AmountVo> successAmount;

    /** 成功笔数 */
    @ApiModelProperty(value = "Number of successful transactions")
    private int successCount;

    /** 退款金额 */
    @ApiModelProperty(value = "Amount refunded")
    private List<AmountVo> refundAmount;

    /** 退款笔数 */
    @ApiModelProperty(value = "Number of refund transactions")
    private int refundCount;

    /** 拒付金额 */
    @ApiModelProperty(value = "Amount rejected")
    private List<AmountVo> rejectedAmount;

    /** 拒付笔数 */
    @ApiModelProperty(value = "Number of rejected transactions")
    private int rejectedCount;

    /** 网关费用 */
    @ApiModelProperty(value = "Network gateway fees")
    private List<AmountVo> networkFee;

    /** 固定费用 */
    @ApiModelProperty(value = "Fixed fee for the transaction")
    private List<AmountVo> fixedFee;

    /** 百分比费用 */
    @ApiModelProperty(value = "Percentage-based fee for the transaction")
    private List<AmountVo> percentageFee;

    /** 退款手续费 */
    @ApiModelProperty(value = "Refund handling fee")
    private List<AmountVo> refundFee;

    /** 拒付手续费 */
    @ApiModelProperty(value = "Rejection handling fee")
    private List<AmountVo> rejectedFee;

}


