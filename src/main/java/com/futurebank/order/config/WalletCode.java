package com.futurebank.order.config;

import com.futurebank.order.enums.WalletCodeEnum;
import org.springframework.stereotype.Component;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义钱包
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface WalletCode {
    WalletCodeEnum value();
}
