package com.futurebank.order.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "ReportExportReq", description = "报表导出查询")
public class ReportExportReq implements Serializable {


    /**
     * 商户id信息
     */
    @ApiModelProperty(value = "商户信息")
    private Long merchantId;


    /**
     * 报表业务：
     * Balance:导出账务变动/Collection:导出收款记录/Payment:导出付款记录/Convert:导出换汇记录/Transfer:导出转账记录/AcquiringTransaction:导出收单记录/AcquiringRefund:导出退款记录/AcquiringChargeback:导出拒付记录
     */
    @NotNull
    @ApiModelProperty(value = "交易类型",required = true, example = "Balance:导出账务变动/Collection:导出收款记录/Payment:导出付款记录/Convert:导出换汇记录/Transfer:导出转账记录/AcquiringTransaction:导出收单记录/AcquiringRefund:导出退款记录/AcquiringChargeback:导出拒付记录")
    private String bizCode;


    /**
     * 货币信息
     */
    @ApiModelProperty(value = "货币")
    private List<String> currency;

    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型：CSV  EXCEL")
    private String fileType;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;


    /**
     * 订单开始时间
     */
    @ApiModelProperty(value = "结束时间(yyyy-MM-dd HH:mm:ss)")
    private String startTime;

    /**
     * 订单结束时间
     */
    @ApiModelProperty(value = "结束时间(yyyy-MM-dd HH:mm:ss)")
    private String endTime;

    /**
     * 商户时区信息
     */
    @ApiModelProperty(value = "商户时区信息")
    private String timeZone;



}
