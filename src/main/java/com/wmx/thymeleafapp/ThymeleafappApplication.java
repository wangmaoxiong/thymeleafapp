package com.wmx.thymeleafapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/10 9:50
 * {@link MapperScan} 指定 MyBatis 扫描的 Mapper 接口目录.
 */
@SpringBootApplication
@MapperScan(value = {"com.wmx.thymeleafapp.mapper"})
public class ThymeleafappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafappApplication.class, args);
    }

}
