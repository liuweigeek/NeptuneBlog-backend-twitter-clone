package com.scott.neptune.searchserver;

import com.scott.neptune.common.constant.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: scott
 * @Email: <a href="mailto:liuweigeek@outlook.com">Scott Lau</a>
 * @Date: 2019/10/21 23:51
 * @Description:
 */
@ComponentScan(basePackages = Constant.BASE_PACKAGE)
@EnableFeignClients(basePackages = Constant.BASE_PACKAGE)
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
public class SearchServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchServerApplication.class, args);
    }

}
