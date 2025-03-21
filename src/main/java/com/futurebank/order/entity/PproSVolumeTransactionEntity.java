package com.futurebank.order.entity;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter;
import lombok.Setter;


/**
 * 批量交易记录表(PproSVolumeTransaction)实体类
 *
 * @author ben
 * @since 2024-05-22 15:52:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PproSVolumeTransaction", description = "批量交易记录表")
public class PproSVolumeTransactionEntity implements Serializable {
    private static final long serialVersionUID = 817252361500569307L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    /**
     * 商户交易ID，商户系统中的唯一交易标识符
     */
    @ApiModelProperty(value = "商户交易ID，商户系统中的唯一交易标识符")
    private String merchantTxId;

    /**
     * 交易ID，系统中的唯一交易标识符
     */
    @ApiModelProperty(value = "交易ID，系统中的唯一交易标识符")
    private String txId;

    /**
     * 支付参考，与支付相关的参考信息
     */
    @ApiModelProperty(value = "支付参考，与支付相关的参考信息")
    private String paymentReference;

    /**
     * 事件类型，交易相关的事件类型
     */
    @ApiModelProperty(value = "事件类型，交易相关的事件类型")
    private String eventType;

    /**
     * 事件时间戳，交易事件发生的时间
     */
    @ApiModelProperty(value = "事件时间戳，交易事件发生的时间")
    private String eventTimestamp;

    /**
     * 商户ID，商户的唯一标识
     */
    @ApiModelProperty(value = "商户ID，商户的唯一标识")
    private String merchantId;

    /**
     * 支付方式，使用的支付手段或渠道
     */
    @ApiModelProperty(value = "支付方式，使用的支付手段或渠道")
    private String paymentMethod;

    /**
     * 金额，交易的金额
     */
    @ApiModelProperty(value = "金额，交易的金额")
    private String amount;

    /**
     * 货币类型，交易使用的货币代码
     */
    @ApiModelProperty(value = "货币类型，交易使用的货币代码")
    private String currency;

    /**
     * 国家，交易发生的国家或地区
     */
    @ApiModelProperty(value = "国家，交易发生的国家或地区")
    private String country;

    @ApiModelProperty(value = "${column.comment}")
    private Date createdTime;

    @ApiModelProperty(value = "${column.comment}")
    private Date updateTime;

    @ApiModelProperty(value = "${column.comment}")
    private String fileName;

    @ApiModelProperty(value = "${column.comment}")
    private String settlementDate;

    @ApiModelProperty(value = "${column.comment}")
    private Integer recordCount;

    /**
     * 是否已处理
     */
    @ApiModelProperty(value = "是否已处理")
    private Integer isProcessed;
}
