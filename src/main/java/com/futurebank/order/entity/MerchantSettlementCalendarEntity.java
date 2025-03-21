package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantSettlementCalendarEntity", description = "商户结算每日汇总数据")
public class MerchantSettlementCalendarEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8985577300727407021L;
    /**
     * 渠道信息
     */
    @ApiModelProperty(value = "Channel information ID")
    private Long providerId;

    /**
     * 渠道名称
     */
    @ApiModelProperty(value = "Channel name")
    private String providerName;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "Merchant ID")
    private Long merchantId;

    /**
     * 交易货币
     */
    @ApiModelProperty(value = "Transaction currency")
    private String currency;

    /**
     * 结算时间
     */
    @ApiModelProperty(value = "settlementDate")
    private String settlementDate; // 结算时间

    /**
     * 结算状态
     */
    @ApiModelProperty(value = "结算状态: 1:settled 2:unsettled 3:pending")
    private Integer settlementStatus; // 结算状态

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String paymentMethod; // 支付方式

    /**
     * 转结金额
     */
    @ApiModelProperty(value = "转结金额")
    private BigDecimal transferAmount; // 转结金额

    /**
     * 结算金额
     */
    @ApiModelProperty(value = "结算金额")
    private BigDecimal settlementAmount; // 结算金额


    /**
     * 结算开始时间
     */
    @ApiModelProperty(value = "结算开始时间")
    private Date startDateTime;


    /**
     * 结算结束时间
     */
    @ApiModelProperty(value = "结算结束时间")
    private Date endDateTime;


}
