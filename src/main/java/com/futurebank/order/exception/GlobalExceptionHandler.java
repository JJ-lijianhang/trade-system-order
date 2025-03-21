package com.futurebank.order.exception;


import com.alibaba.fastjson.JSON;
import com.futurebank.pojo.base.CommonResp;
import com.futurebank.pojo.exception.ChangeAmountException;
import com.futurebank.pojo.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 拦截表单参数校验
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public CommonResult bindException(BindException e) {
        log.error("表单参数校验失败1：{}", JSON.toJSONString(e));
        BindingResult bindingResult = e.getBindingResult();
        return CommonResult.failed(ExceptionCodeEnum.VALIDATE_FAILED, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 拦截JSON参数校验
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult bindException(MethodArgumentNotValidException e) {
        log.error("表单参数校验失败2：{}", JSON.toJSONString(e));
        BindingResult bindingResult = e.getBindingResult();
        return CommonResult.failed(ExceptionCodeEnum.VALIDATE_FAILED, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 拦截参数类型不正确
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public CommonResult bindException(HttpMediaTypeNotSupportedException e) {
        log.error("表单参数校验失败3：{}", JSON.toJSONString(e));
        return CommonResult.failed(ExceptionCodeEnum.PRAM_NOT_MATCH, Objects.requireNonNull(e.getMessage()));
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ChangeAmountException.class)
    public CommonResp changeAmountException(ChangeAmountException e) {
        log.error("表单参数校验失败4：{}", JSON.toJSONString(e));
        return CommonResp.ok();
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomException.class)
    public CommonResp CustomException(CustomException e) {
        log.error("表单参数校验失败5：{}", JSON.toJSONString(e));
        return CommonResp.fail((e.getCode() == null ? ExceptionCodeEnum.FAILED.getCode() : e.getCode()) + "", e.getMessage());

    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AccountingBizException.class)
    public CommonResp CustomException(AccountingBizException e) {
        log.error("ACCOUNTING EXCEPTION：{}", JSON.toJSONString(e));
        return CommonResp.fail((e.getCode() == null ? ExceptionCodeEnum.FAILED.getCode() : e.getCode()) + "", e.getMessage());

    }

    //声明要捕获的异常
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> CommonResult<?> defaultExceptionHandler(Exception e) {
        e.printStackTrace();
//        if (e instanceof CustomException) {
//            return CommonResult.failed(ExceptionCodeEnum.FAILED, Objects.requireNonNull(e.getMessage()));
//        }
//        if(e instanceof ChangeAmountException) {
//            return CommonResult.success(ExceptionCodeEnum.SUCCESS,e.getMessage()==null?"":e.getMessage());
//        }
        if (e instanceof MissingServletRequestParameterException) {
            return CommonResult.failed(ExceptionCodeEnum.PRAM_NOT_MATCH, Objects.requireNonNull(e.getMessage()));
        }
        //未知错误
        return CommonResult.failed(ExceptionCodeEnum.ERROR, e.getMessage());
    }
}
