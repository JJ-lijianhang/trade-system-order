package com.futurebank.order.pojo;

import lombok.Data;

/**
 * @author ben
 */
@Data
public class CodeResult {

    private String code;
    private Boolean success;
    private String msg;
    private Object data;
}