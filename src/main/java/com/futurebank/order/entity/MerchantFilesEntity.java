package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 商户对账和结算文件表(MerchantFiles)实体类
 *
 * @author ben
 * @since 2024-06-08 15:32:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantFiles", description = "商户对账和结算文件表")
public class MerchantFilesEntity implements Serializable {
    private static final long serialVersionUID = -22916548450597831L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    @ApiModelProperty(value = "渠道id")
    private Long providerId;

    /**
     * 渠道名称
     */
    @ApiModelProperty(value = "渠道名称")
    private String providerName;

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    /**
     * 文件类型 reconciliation 对账文件 settlement 结算文件
     */
    @ApiModelProperty(value = "文件类型 reconciliation 对账文件 settlement 结算文件")
    private String fileType;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    /**
     * 文件存储路径
     */
    @ApiModelProperty(value = "文件存储路径")
    private String filePath;

    /**
     * 文件大小，单位字节
     */
    @ApiModelProperty(value = "文件大小，单位字节")
    private Long fileSize;

    /**
     * 期数
     */
    @ApiModelProperty(value = "期数")
    private String period;

    /**
     * 结算金额
     */
    @ApiModelProperty(value = "结算金额")
    private BigDecimal money;

    /**
     * 结算货币
     */
    @ApiModelProperty(value = "结算货币")
    private String currency;

    /**
     * 结算开始时间
     */
    @ApiModelProperty(value = "结算开始时间")
    private Date beginTime;

    /**
     * 结算结算时间
     */
    @ApiModelProperty(value = "结算结算时间")
    private Date endTime;


    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateAt;
}
