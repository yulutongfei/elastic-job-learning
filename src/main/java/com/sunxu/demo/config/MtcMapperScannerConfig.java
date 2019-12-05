package com.sunxu.demo.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 孙许
 * @Date 2019/12/06 1:56
 * @Description
 */
@Configuration
@AutoConfigureAfter(MtcMybatisConfig.class)
public class MtcMapperScannerConfig {

    @Bean(name = "mtcConfigurer")
    public MapperScannerConfigurer getConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.sunxu.demo.mapper.mtc");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("mtcSqlSessionFactory");
        return mapperScannerConfigurer;
    }
}
