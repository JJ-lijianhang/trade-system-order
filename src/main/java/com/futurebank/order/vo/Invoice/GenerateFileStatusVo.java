package com.futurebank.order.vo.Invoice;

import com.futurebank.order.enums.GenerateFileTypeEnum;
import lombok.Data;

@Data
public class GenerateFileStatusVo {
    /* 0 失败 1成功 */
    private int fileStatus;
    private String fileName;
    private String fileUrl;
    private String filePath;
    private GenerateFileTypeEnum fileType;
    private String fileStatusDesc;
    private String fileStatusCode;
}
