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
 * 商户订单上游信息(PaymentOrderUpstream)实体类
 *
 * @author ben
 * @since 2024-04-02 10:42:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentOrderUpstream", description = "商户订单上游信息")
public class PaymentOrderUpstreamEntity implements Serializable {
    private static final long serialVersionUID = -27048265798120430L;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Integer id;

    /**
     * 上游请求地址
     */
    @ApiModelProperty(value = "上游请求地址")
    private String upstreamRequestUrl;

    /**
     * 平台通知地址
     */
    @ApiModelProperty(value = "平台通知地址")
    private String platformNotifyUrl;

    /**
     * 平台跳转地址
     */
    @ApiModelProperty(value = "平台跳转地址")
    private String platformRedirectUrl;

    /**
     * 上游请求头
     */
    @ApiModelProperty(value = "上游请求头")
    private String upstrameHeader;

    /**
     * 上游请求时间
     */
    @ApiModelProperty(value = "上游请求时间")
    private Date upstreamRequestTime;

    /**
     * 上游成功时间
     */
    @ApiModelProperty(value = "上游成功时间")
    private Date upstreamSuccessTime;

    /**
     * 上游通知时间
     */
    @ApiModelProperty(value = "上游通知时间")
    private Date upstreamNotifyTime;

    /**
     * 上游返回通知状态 'Pending','Success','Failed'
     */
    @ApiModelProperty(value = "上游返回通知状态 'Pending','Success','Failed'")
    private String upstreamNotifyStatus;

    /**
     * 上游请求报文
     */
    @ApiModelProperty(value = "上游请求报文")
    private String upstreamRequestMessage;

    /**
     * 上游响应报文
     */
    @ApiModelProperty(value = "上游响应报文")
    private String upstreamResponseMessage;

    /**
     * 上游通知内容
     */
    @ApiModelProperty(value = "上游通知内容")
    private String upstreamNotifyContent;

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
