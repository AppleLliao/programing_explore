package com.explore.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 应用启动入口
 * Created by macro on 2018/4/26.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.explore.mall.controller","com.explore.mall.service",
        "com.explore.mall.config","com.explore.mall.util","com.explore.mall.component"})
@MapperScan(basePackages = {"com.explore.mall.mapper"})
public class MallAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
    }
}
