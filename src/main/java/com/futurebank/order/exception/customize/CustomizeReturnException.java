package com.futurebank.order.exception.customize;

import com.futurebank.order.common.ReturnCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
/**
 * 自定义返回错误异常类
 *
 * @author LiuRonghua
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class CustomizeReturnException extends CustomizeException {

    public CustomizeReturnException() {
        this.returnCode = ReturnCode.FAIL;
        this.msg = ReturnCode.FAIL.getMsg();
    }

    public CustomizeReturnException(ReturnCode returnCode) {
        this.returnCode = returnCode;
        this.msg = returnCode.getMsg();
    }

    public CustomizeReturnException(ReturnCode returnCode, String msg) {
        log.error("{} ==> {}", returnCode.getMsg(), "[" + msg + "]");
        this.returnCode = returnCode;
        this.msg = StringUtils.isBlank(msg) ? returnCode.getMsg() : msg;
    }

    public CustomizeReturnException(ReturnCode returnCode, String msg, Throwable cause) {
        log.error("{} ==> {} ==> {}", returnCode.getMsg(), "[" + msg + "]", cause.getMessage());
        this.returnCode = returnCode;
        this.msg = StringUtils.isBlank(msg) ? returnCode.getMsg() : msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

}