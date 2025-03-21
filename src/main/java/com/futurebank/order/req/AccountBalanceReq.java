package com.futurebank.order.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ApiModel(value = "AccountBalanceReq", description = "账户资金查询参数")
public class AccountBalanceReq implements Serializable {
    private static final long serialVersionUID = 6883197780026867782L;

    /**
    * 商户号id
    */
    @ApiModelProperty(value = "商户号",required = true)
    @NotNull
    private Long merchantId;

    /**
     * 货币类型
     */
    @ApiModelProperty(value = "货币类型")
    private String currency;

}
