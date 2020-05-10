package com.wmx.thymeleafapp.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/9 16:02
 * MyBatis 配置类
 */

@org.springframework.context.annotation.Configuration
public class MybatisConfig {

    /**
     * 将组建添加到容器中
     *
     * @return
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                /**setMapUnderscoreToCamelCase(boolean mapUnderscoreToCamelCase)
                 * 将映射下划线设置为大小写，无论是数据库字段还是实体的属性，当出现下划线时，自动转为驼峰命名
                 * 默认为 false.
                 */
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}