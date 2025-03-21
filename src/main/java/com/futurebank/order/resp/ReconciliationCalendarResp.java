package com.futurebank.order.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "SettlementCalendarResp", description = "对账日历响应参数")
public class ReconciliationCalendarResp extends FileListResp implements Serializable {
    private static final long serialVersionUID = 5945250721475676583L;

    @ApiModelProperty(value = "主键id", example = "1")
    private Integer id;
    /** 对账日期 */
    @ApiModelProperty(value = "reconciliation date of the transaction")
    private String reconDate;

    /** 支付方式 */
    @ApiModelProperty(value = "Payment method used for the transaction")
    private String paymentMethod;
    private String paymentMethodName;

    /** 交易金额 */
    @ApiModelProperty(value = "Transaction amount")
    private String transactionAmount;
    private String transactionAmountCcy;

    /** 交易笔数 */
    @ApiModelProperty(value = "Number of transactions")
    private int transactionCount;

    /** 成功金额 */
    @ApiModelProperty(value = "Amount of successful transactions")
    private String successAmount;
    private String successAmountCcy;

    /** 成功笔数 */
    @ApiModelProperty(value = "Number of successful transactions")
    private int successCount;

    /** 退款金额 */
    @ApiModelProperty(value = "Amount refunded")
    private String refundAmount;
    private String refundAmountCcy;

    /** 退款笔数 */
    @ApiModelProperty(value = "Number of refund transactions")
    private int refundCount;

    /** 拒付金额 */
    @ApiModelProperty(value = "Amount rejected")
    private String rejectedAmount;
    private String rejectedAmountCcy;

    /** 拒付笔数 */
    @ApiModelProperty(value = "Number of rejected transactions")
    private int rejectedCount;

    /** 网关费用 */
    @ApiModelProperty(value = "Network gateway fees")
    private String networkFee;
    private String networkFeeCcy;

    /** 固定费用 */
    @ApiModelProperty(value = "Fixed fee for the transaction")
    private String fixedFee;
    private String fixedFeeCcy;

    /** 百分比费用 */
    @ApiModelProperty(value = "Percentage-based fee for the transaction")
    private String percentageFee;
    private String percentageFeeCcy;

    /** 退款手续费 */
    @ApiModelProperty(value = "Refund handling fee")
    private String refundFee;
    private String refundFeeCcy;

    /** 拒付手续费 */
    @ApiModelProperty(value = "Rejection handling fee")
    private String rejectedFee;
    private String rejectedFeeCcy;

    /** 预计结算时间 */
    @ApiModelProperty(value = "Estimated settlement time")
    private String estimatedSettlementDate;

    /** 附件 */
    @ApiModelProperty(value = "Link to the attachment file")
    private List<FileListResp> attachment;
}
