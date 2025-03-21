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
 * 支付通道提供商(PaymentProvider)实体类
 *
 * @author ben
 * @since 2024-12-03 19:18:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentProvider", description = "支付通道提供商")
public class PaymentProviderEntity implements Serializable {
    private static final long serialVersionUID = -87002635276718006L;

    /**
     * 通道提供商id
     */
    @ApiModelProperty(value = "通道提供商id")
    private Long providerId;

    /**
     * 通道提供商全称
     */
    @ApiModelProperty(value = "通道提供商全称")
    private String providerName;

    /**
     * 通道提供商重命名全称
     */
    @ApiModelProperty(value = "通道提供商重命名全称")
    private String providerRename;

    /**
     * 渠道提供的产品能力：acquiring=收单 collection=收款 disbursement=付款 transfers=转账 withdrawals=提现 exchange=换汇 recharges=充值
     */
    @ApiModelProperty(value = "渠道提供的产品能力：acquiring=收单 collection=收款 disbursement=付款 transfers=转账 withdrawals=提现 exchange=换汇 recharges=充值")
    private String productCapability;

    /**
     * 渠道商授权MID
     */
    @ApiModelProperty(value = "渠道商授权MID")
    private String creditMid;

    /**
     * 通道对应商户Id
     */
    @ApiModelProperty(value = "通道对应商户Id")
    private Long merchantId;

    /**
     * 通道描述
     */
    @ApiModelProperty(value = "通道描述")
    private String providerDesc;

    /**
     * 代收通道配置
     */
    @ApiModelProperty(value = "代收通道配置")
    private String payinConfig;

    /**
     * 代付通道配置
     */
    @ApiModelProperty(value = "代付通道配置")
    private String payoutConfig;

    /**
     * 综合服务费
     */
    @ApiModelProperty(value = "综合服务费")
    private String serviceFee;

    /**
     * 状态：0=商用 1=待商用
     */
    @ApiModelProperty(value = "状态：0=商用 1=待商用")
    private Integer istate;

    /**
     * 商用时间
     */
    @ApiModelProperty(value = "商用时间")
    private Date activeTime;

    /**
     * 代收服务名称
     */
    @ApiModelProperty(value = "代收服务名称")
    private String payinService;

    /**
     * 代付服务名称
     */
    @ApiModelProperty(value = "代付服务名称")
    private String payoutService;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sortIndex;

    /**
     * 是否删除 0=未删除；1=已删除
     */
    @ApiModelProperty(value = "是否删除 0=未删除；1=已删除")
    private Integer isDeleted;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private Long operator;

    /**
     * 是否支持api 0 支持 1 不支持
     */
    @ApiModelProperty(value = "是否支持api 0 支持 1 不支持")
    private Integer supportApis;

    /**
     * 是否收取网关费用 0 不收取 1收取
     */
    @ApiModelProperty(value = "是否收取网关费用 0 不收取 1收取")
    private Integer supportGateway;
}
