package com.wmx.thymeleafapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.wmx.thymeleafapp.mapper"})
public class ThymeleafappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafappApplication.class, args);
    }

}
