package com.futurebank.order.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "SettlementCalendarResp", description = "结算日历响应参数")
public class SettlementCalendarResp extends FileListResp implements Serializable {
    private static final long serialVersionUID = 2031970185350357582L;

    @ApiModelProperty(value = "主键id")
    private Integer id;

    /** 结算时间 */
    @ApiModelProperty(value = "Settlement date")
    private String settlementDate;

    /** 结算状态 */
    @ApiModelProperty(value = "Settlement status (e.g., Settled, Unsettled, Transfer)")
    private String settlementStatus;

    /** 支付方式 */
    @ApiModelProperty(value = "Payment method used for settlement")
    private String paymentMethod;
    private String paymentMethodName;

    /** 转结金额 */
    @ApiModelProperty(value = "Transfer amount involved in the settlement")
    private String transferAmount;
    private String transferAmountCcy;

    /** 结算金额 */
    @ApiModelProperty(value = "Final settlement amount")
    private String settlementAmount;
    private String settlementAmountCcy;

    /** 文件 */
    @ApiModelProperty(value = "Links to related documents such as settlement or invoice files")
    private List<FileListResp> documentLinks;


}
