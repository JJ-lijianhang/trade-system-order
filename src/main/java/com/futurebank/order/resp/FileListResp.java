package com.futurebank.order.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "对账结算文件描述", description = "对账结算文件描述")
public class FileListResp implements Serializable {
    private static final long serialVersionUID = 7191595305766102160L;

    @ApiModelProperty(value = "文件类型：inv:发票 recon: 对账文件 settle:结算文件 aggregate:结算汇总文件")
    private String fileType;
    private String fileName;
    private String filePath;
    private String fileUrl;
    private String fileSize;
    private String fileCreateTime;
    private String fileUpdateTime;
    private String fileStatus;
}
