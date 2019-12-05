package com.sunxu.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @Author 孙许
 * @Date 2019/12/05 23:44
 * @Description
 */
@Configuration
public class PropertiesConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer property() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setFileEncoding("UTF-8");
        propertySourcesPlaceholderConfigurer.setLocation(new PathMatchingResourcePatternResolver().getResource("classpath:application.properties"));
        return propertySourcesPlaceholderConfigurer;
    }
}
