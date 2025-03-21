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
 * (FileHandleLog)实体类
 *
 * @author ben
 * @since 2024-05-26 10:16:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FileHandleLog", description = "$tableInfo.comment")
public class FileHandleLogEntity implements Serializable {
    private static final long serialVersionUID = -61501326574676552L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    /**
     * 通道提供商
     */
    @ApiModelProperty(value = "通道提供商")
    private String providerName;

    /**
     * 文件处理类型: 1=对账文件 2=结算文件
     */
    @ApiModelProperty(value = "文件处理类型: 1=对账文件 2=结算文件")
    private Integer fileType;

    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名")
    private String fileName;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型")
    private String orderType;

    /**
     * 上游订单号
     */
    @ApiModelProperty(value = "上游订单号")
    private String upstreamOrderNo;

    /**
     * 上游关联号
     */
    @ApiModelProperty(value = "上游关联号")
    private String referenceOrderNo;

    /**
     * 日志备注信息
     */
    @ApiModelProperty(value = "日志备注信息")
    private String handleLog;

    @ApiModelProperty(value = "${column.comment}")
    private String periods;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
