package com.futurebank.order.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "ReconciliationCalendarReq", description = "资金流水明细")
public class ReconciliationCalendarReq extends PageQuery implements Serializable {
    private static final long serialVersionUID = -9056753365129404429L;

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


    /** 支付方式 */
    @ApiModelProperty(value = "Payment method used for the transaction")
    private String paymentMethod;

    /**
     * 订单开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间(yyyy-MM-dd)")
    private Date reconStartTime;

    /**
     * 订单结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "结束时间(yyyy-MM-dd)")
    private Date reconEndTime;

    @ApiModelProperty(value = "支付方式list")
    private List<String> paymentMethodList;

    /** 支付方式 */
    @ApiModelProperty(value = "货币类型list")
    private List<String> currencyList;

}
