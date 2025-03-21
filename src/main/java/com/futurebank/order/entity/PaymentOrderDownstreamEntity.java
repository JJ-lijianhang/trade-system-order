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
 * 商户订单下游信息(PaymentOrderDownstream)实体类
 *
 * @author ben
 * @since 2024-03-31 19:34:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentOrderDownstream", description = "商户订单下游信息")
public class PaymentOrderDownstreamEntity implements Serializable {
    private static final long serialVersionUID = -15846985997821007L;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Integer id;

    /**
     * 下游通知地址
     */
    @ApiModelProperty(value = "下游通知地址")
    private String downstreamNotifyUrl;

    /**
     * 下游跳转地址
     */
    @ApiModelProperty(value = "下游跳转地址")
    private String downstreamRedirectUrl;

    /**
     * 下游请求头
     */
    @ApiModelProperty(value = "下游请求头")
    private String downstreamRequestHeader;

    /**
     * 下游请求时间
     */
    @ApiModelProperty(value = "下游请求时间")
    private Date downstreamRequestTime;

    /**
     * 下游成功时间
     */
    @ApiModelProperty(value = "下游成功时间")
    private Date downstreamSuccessTime;

    /**
     * 下游通知时间
     */
    @ApiModelProperty(value = "下游通知时间")
    private Date downstreamNotifyTime;

    /**
     * 下游返回通知状态 'pending','success','failed'
     */
    @ApiModelProperty(value = "下游返回通知状态 'pending','success','failed'")
    private String downstreamNotifyStatus;

    /**
     * 下游请求报文
     */
    @ApiModelProperty(value = "下游请求报文")
    private String downstreamRequestMessage;

    /**
     * 下游响应报文
     */
    @ApiModelProperty(value = "下游响应报文")
    private String downstreamResponseMessage;

    /**
     * 下游通知内容
     */
    @ApiModelProperty(value = "下游通知内容")
    private String downstreamNotifyContent;

    /**
     * 同步次数
     */
    @ApiModelProperty(value = "同步次数")
    private Integer nottifyTimes;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    /**
     * 下游订单号
     */
    @ApiModelProperty(value = "下游订单号")
    private String downstreamOrderNo;

    /**
     * 平台订单号
     */
    @ApiModelProperty(value = "平台订单号")
    private String platformOrderNo;

    /**
     * 上游订单号
     */
    @ApiModelProperty(value = "上游订单号")
    private String upstreamOrderNo;
}
