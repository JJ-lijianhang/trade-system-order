package com.futurebank.order.vo.AutoSettmentVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FileHandleLogVo {
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

}
