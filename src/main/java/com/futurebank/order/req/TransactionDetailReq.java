package com.futurebank.order.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "TransactionDetailReq", description = "资金流水明细")
public class TransactionDetailReq extends PageQuery implements Serializable{
    private static final long serialVersionUID = 8949799573544968926L;

    /**
     * 商户号id
     */
    @ApiModelProperty(value = "商户号",required = true)
    private Long merchantId;

    /**
     * 货币类型
     */
    @ApiModelProperty(value = "货币类型")
    private String currency;


    /**
     * 订单开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "交易开始时间(yyyy-MM-dd HH:mm:ss)")
    private Date tradeStartTime;

    /**
     * 订单结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "交易结束时间(yyyy-MM-dd HH:mm:ss)")
    private Date tradeEndTime;
}
