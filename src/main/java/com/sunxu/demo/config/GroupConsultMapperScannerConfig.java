package com.sunxu.demo.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 孙许
 * @Date 2019/12/06 2:03
 * @Description
 */
@Configuration
@AutoConfigureAfter(GroupConsultMybatisConfig.class)
public class GroupConsultMapperScannerConfig {

    @Bean(name = "groupConsultConfigurer")
    public MapperScannerConfigurer getConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.sunxu.demo.mapper.group");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("groupSqlSessionFactory");
        return mapperScannerConfigurer;
    }
}
