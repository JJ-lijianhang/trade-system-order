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
 * 商户交易汇总子表(PproSAggregateSub)实体类
 *
 * @author ben
 * @since 2024-05-22 15:52:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PproSAggregateSub", description = "商户交易汇总子表")
public class PproSAggregateSubEntity implements Serializable {
    private static final long serialVersionUID = 993115016161407032L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    /**
     * 商户ID，唯一标识商户
     */
    @ApiModelProperty(value = "商户ID，唯一标识商户")
    private String ikey;

    /**
     * 支付方式，使用的支付手段或渠道
     */
    @ApiModelProperty(value = "支付方式，使用的支付手段或渠道")
    private String ivalue;

    @ApiModelProperty(value = "${column.comment}")
    private String settlementDate;

    @ApiModelProperty(value = "${column.comment}")
    private Date createdTime;

    @ApiModelProperty(value = "${column.comment}")
    private Date updateTime;

    @ApiModelProperty(value = "${column.comment}")
    private String fileName;

    /**
     * 是否已处理
     */
    @ApiModelProperty(value = "是否已处理")
    private Integer isProcessed;
}
