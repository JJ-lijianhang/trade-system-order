package com.futurebank.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.futurebank"}, exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@ServletComponentScan
@EnableScheduling
@MapperScan(basePackages = {"com.futurebank.order.mapper"})
@EnableTransactionManagement
@EnableFeignClients
public class TradeSystemOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeSystemOrderApplication.class, args);
    }
}
