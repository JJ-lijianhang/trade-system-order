

package com.futurebank.order.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    @Bean(name = {"master"})
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dataSource1() {
        return (DataSource) new DruidDataSource();
    }

    @Bean(name = {"slave"})
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource dataSource2() {
        return (DataSource) new DruidDataSource();
    }

/*    @Bean(name = {"zoloz"})
    @ConfigurationProperties(prefix = "spring.datasource.zoloz")
    public DataSource dataSource3() {
        return (DataSource) new DruidDataSource();
    }*/

    @Primary
    @Bean(name = {"dynamicDataSource"})
    public DataSource dynamicDataSource() {
        final DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource((Object) this.dataSource1());
        final Map<Object, Object> dsMap = new HashMap<Object, Object>();
        dsMap.put("master", this.dataSource1());
        dsMap.put("slave", this.dataSource2());
        //dsMap.put("zoloz", this.dataSource3());
        dynamicDataSource.setTargetDataSources((Map) dsMap);
        return (DataSource) dynamicDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return (PlatformTransactionManager) new DataSourceTransactionManager(this.dynamicDataSource());
    }
}
