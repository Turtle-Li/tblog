package com.turtle.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.turtle.web.mapper")
@ComponentScan(basePackages = {
        "com.turtle.common.config",
        "com.turtle.web.controller",
        "com.turtle.web.service",
        "com.turtle.common.handler"
                                })
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
