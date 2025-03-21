package com.futurebank.order.utils.pdf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * PDF导出模板文件扩展名
 *
 * @author LiuRonghua
 */
@Getter
@AllArgsConstructor
public enum ExportExtension {

    /**
     * 居左
     */
    FO(".fo"),

    /**
     * 居中
     */
    JTE(".jte");

    private final String name;

}
