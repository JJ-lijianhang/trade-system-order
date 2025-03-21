package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户-支付通道提供商关联表(MerchantPaymentProvider)实体类
 *
 * @author wangxin
 * @since 2024-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantPaymentProvider", description = "商户-支付通道提供商关联表")
public class MerchantPaymentProviderEntity implements Serializable {
    private static final long serialVersionUID = 838593662571975385L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    /**
     * 通道提供商名称
     */
    @ApiModelProperty(value = "通道提供商名称")
    private String providerName;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 代理商编号
     */
    @ApiModelProperty(value = "代理商编号")
    private String agentNo;

    /**
     * 证书编号
     */
    @ApiModelProperty(value = "证书编号")
    private String certificateId;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private Long userNo;

    /**
     * 客户编号 GEP内部的唯一订单号
     */
    @ApiModelProperty(value = "客户编号 GEP内部的唯一订单号")
    private Long customerNo;

    /**
     * 认证状态 0-未认证；1-认证中 2-已认证 3-认证失败
     */
    @ApiModelProperty(value = "认证状态 0-未认证；1-认证中 2-已认证 3-认证失败")
    private Integer realnameStatus;

    /**
     * 激活状态：Unprocessed=未进件 Pending=待激活 Success=激活成功 Failed=激活失败 Closed=已关闭
     */
    @ApiModelProperty(value = "激活状态：Unprocessed=未进件 Pending=待激活 Success=激活成功 Failed=激活失败 Closed=已关闭")
    private String activationStatus;

    /**
     * 上一个激活状态
     */
    @ApiModelProperty(value = "上一个激活状态")
    private String lastActivationStatus;

    /**
     * 商户渠道状态：0=启用 1=未启用
     */
    @ApiModelProperty(value = "商户渠道状态：0=启用 1=未启用")
    private Integer istate;

    /**
     * 账号信息扩展字段
     */
    @ApiModelProperty(value = "账号信息扩展字段")
    private String accExt;

    /**
     * 商用时间
     */
    @ApiModelProperty(value = "商用时间")
    private Date activeTime;

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
     * 商户渠道扩展字段 json
     */
    @ApiModelProperty(value = "商户渠道扩展字段 json")
    private String providerExt;
}
