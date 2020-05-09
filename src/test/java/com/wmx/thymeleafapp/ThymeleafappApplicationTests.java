package com.wmx.thymeleafapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThymeleafappApplicationTests {

    /**
     * Spring Boot 默认已经配置好了数据源，程序员可以直接 DI 注入然后使用即可
     * 数据源获取正常，能正常拿到数据库连接，则说明数据库连接成功
     */
    @Resource
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println("数据源>>>>>>" + dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println("连接>>>>>>>>>" + connection);
        System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());
        connection.close();
    }

}
