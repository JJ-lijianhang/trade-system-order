package com.futurebank.order.exception.customize;

import com.futurebank.order.common.ReturnCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常接口
 *
 * @author LiuRonghua
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomizeException extends RuntimeException {

    public ReturnCode returnCode = null;

    public String msg = null;

}
