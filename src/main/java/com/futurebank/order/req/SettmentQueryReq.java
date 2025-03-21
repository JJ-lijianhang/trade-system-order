package com.futurebank.order.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "SettmentQueryReq", description = "结算对账查询")
public class SettmentQueryReq implements Serializable {

    /**
     * 系统商户号
     */
    private String merchantId;

    /**
     * 结算周期
     */
    private String period;
    private String periodStr;

    /**
     * 对账日期
     */
    private String reconciliationDate;

    /**
     * 结算日期
     */
    private String settledDate;
}
