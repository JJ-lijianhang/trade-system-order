package com.futurebank.order.utils.pdf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * PDF字体样式
 *
 * @author LiuRonghua
 */
@Getter
@AllArgsConstructor
public enum FontStyle {

    /**
     * 正常
     */
    NORMAL("normal"),

    /**
     * 斜体
     */
    ITALIC("italic");

    private final String name;

}
