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
 * 商户交易汇总表(PproSAggregate)实体类
 *
 * @author ben
 * @since 2024-12-06 13:11:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PproSAggregate", description = "商户交易汇总表")
public class PproSAggregateEntity implements Serializable {
    private static final long serialVersionUID = -60730388616157651L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    /**
     * 商户ID，唯一标识商户
     */
    @ApiModelProperty(value = "商户ID，唯一标识商户")
    private String merchantId;

    /**
     * 支付方式，使用的支付手段或渠道
     */
    @ApiModelProperty(value = "支付方式，使用的支付手段或渠道")
    private String paymentMethod;

    /**
     * 事件类型，发生的交易或行为类型
     */
    @ApiModelProperty(value = "事件类型，发生的交易或行为类型")
    private String eventType;

    /**
     * 事件数量，指定类型事件的总数
     */
    @ApiModelProperty(value = "事件数量，指定类型事件的总数")
    private String eventCount;

    /**
     * 累计金额，所有事件的金额总和
     */
    @ApiModelProperty(value = "累计金额，所有事件的金额总和")
    private String aggregateAmount;

    /**
     * 货币类型，交易使用的货币代码
     */
    @ApiModelProperty(value = "货币类型，交易使用的货币代码")
    private String currency;

    @ApiModelProperty(value = "${column.comment}")
    private String settlementDate;

    /**
     * 记录数量，汇总的记录总数
     */
    @ApiModelProperty(value = "记录数量，汇总的记录总数")
    private String recordCount;

    /**
     * 文件名称，存储或报告的文件名
     */
    @ApiModelProperty(value = "文件名称，存储或报告的文件名")
    private String fileName;

    @ApiModelProperty(value = "${column.comment}")
    private Date createdTime;

    @ApiModelProperty(value = "${column.comment}")
    private Date updateTime;

    /**
     * 是否已处理
     */
    @ApiModelProperty(value = "是否已处理")
    private Integer isProcessed;
}
