package com.futurebank.order.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RpAccountingResp", description = "统一入账响应参数")
public class RpAccountingResp implements Serializable {
    private static final long serialVersionUID = -1553303888082940162L;

    /**
     * 凭证号
     */
    @ApiModelProperty(value = "凭证号")
    private String voucherNo;
}
