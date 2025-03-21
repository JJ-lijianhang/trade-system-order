package com.futurebank.order.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "TradeDetailsReq", description = "资金流水明细")
public class TradeDetailsReq extends PageQuery implements Serializable {
    private static final long serialVersionUID = -9056753365129404429L;

    /**
    * 商户号id
    */
    @ApiModelProperty(value = "商户号",required = true)
    private Long merchantId;

    /**
     * 语言类型
     */
    @ApiModelProperty(value = "语言类型")
    private String language;

    /**
     * 货币类型
     */
    @ApiModelProperty(value = "货币类型")
    private String currency;

    /**
     * 交易类型
     */
    @ApiModelProperty(value = "交易类型")
    private String bizCode;

    /**
     * 统计科目
     */
    @ApiModelProperty(value = "统计科目")
    private String subjectCode;

    /**
     * 关联单号查询
     */
    @ApiModelProperty(value = "关联单号")
    private String referenceNo;

    /**
     * 订单开始时间
     */
    @ApiModelProperty(value = "交易开始时间(yyyy-MM-dd HH:mm:ss)")
    private String tradeStartTime;

    /**
     * 订单结束时间
     */
    @ApiModelProperty(value = "交易结束时间(yyyy-MM-dd HH:mm:ss)")
    private String tradeEndTime;




}
