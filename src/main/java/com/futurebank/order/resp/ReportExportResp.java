package com.futurebank.order.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "ReportExportResp", description = "报表导出响应")
public class ReportExportResp implements Serializable {
    private static final long serialVersionUID = 7189142262916726814L;

    /**
     * 前端判断的条件：报表状态：SUCCESS  PENDING
     *
     */
    private String status;

    private String fileName;

    private String fileType;

    private String filePath;

    /**
     * aliyun oss url （临时路径）
     */
    private String fileOSSUrl;

    /**
     * 请求参数检验状态： true  false
     */
    private boolean checkStatus;

}
