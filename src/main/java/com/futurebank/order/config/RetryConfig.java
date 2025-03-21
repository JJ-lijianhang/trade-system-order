package com.futurebank.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

/**
 * @author ben
 * @date 2024/6/4 14:53
 **/
@Configuration
@EnableRetry
@EnableAspectJAutoProxy
public class RetryConfig {
    @Bean
    public RetryOperationsInterceptor retryInterceptor() {
        return RetryInterceptorBuilder.stateless()
                .maxAttempts(10)
                .backOffOptions(1000, 2, 10000)
                .build();
    }
}
