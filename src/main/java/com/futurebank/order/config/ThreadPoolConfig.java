package com.futurebank.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean("ReportExportExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor orderExecutor = new ThreadPoolTaskExecutor();
        //设置线程池参数信息
        orderExecutor.setCorePoolSize(10);
        orderExecutor.setMaxPoolSize(50);
        orderExecutor.setQueueCapacity(200);
        orderExecutor.setKeepAliveSeconds(60);
        orderExecutor.setThreadNamePrefix("reportExport_");
        orderExecutor.setWaitForTasksToCompleteOnShutdown(true);
        orderExecutor.setAwaitTerminationSeconds(60);
        //修改拒绝策略为使用当前线程执行
        orderExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化线程池
        orderExecutor.initialize();
        return orderExecutor;
    }

    @Bean("OrderStatusQueryExecutor")
    public ThreadPoolTaskExecutor OrderStatusQueryExecutor() {
        ThreadPoolTaskExecutor orderExecutor = new ThreadPoolTaskExecutor();
        //设置线程池参数信息
        orderExecutor.setCorePoolSize(10);
        orderExecutor.setMaxPoolSize(50);
        orderExecutor.setQueueCapacity(200);
        orderExecutor.setKeepAliveSeconds(60);
        orderExecutor.setThreadNamePrefix("order_status_query_");
        orderExecutor.setWaitForTasksToCompleteOnShutdown(true);
        orderExecutor.setAwaitTerminationSeconds(60);
        //修改拒绝策略为使用当前线程执行
        orderExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化线程池
        orderExecutor.initialize();
        return orderExecutor;
    }
}
