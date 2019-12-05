package com.sunxu.demo;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {MybatisAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class ElasticJobLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticJobLearningApplication.class, args);
    }

}
