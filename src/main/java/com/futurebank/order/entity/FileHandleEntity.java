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
 * (FileHandle)实体类
 *
 * @author ben
 * @since 2024-05-21 17:49:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FileHandle", description = "$tableInfo.comment")
public class FileHandleEntity implements Serializable {
    private static final long serialVersionUID = -92229538267959879L;

    /**
     * 唯一id
     */
    @ApiModelProperty(value = "唯一id")
    private Long id;

    /**
     * 通道提供商id
     */
    @ApiModelProperty(value = "通道提供商id")
    private Long providerId;

    /**
     * 通道提供商名称
     */
    @ApiModelProperty(value = "通道提供商名称")
    private String providerName;

    /**
     * 文件类型：reconciliation=对账 settlement=结算
     */
    @ApiModelProperty(value = "文件类型：reconciliation=对账 settlement=结算")
    private String fileType;

    /**
     * 处理的文件名
     */
    @ApiModelProperty(value = "处理的文件名")
    private String fileName;

    /**
     * 处理状态 0=成功 1=失败
     */
    @ApiModelProperty(value = "处理状态 0=成功 1=失败")
    private Integer istate;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String filePath;

    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数")
    private Integer icount;

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
}
