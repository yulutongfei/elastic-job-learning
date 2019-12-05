package com.sunxu.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author 孙许
 * @Date 2019/12/04 20:43
 * @Description
 */
@Configuration
@ConditionalOnProperty("jdbc.url")
@EnableConfigurationProperties(GroupConsultJdbcProperties.class)
public class GroupConsultMybatisConfig {
    @Resource
    private GroupConsultJdbcProperties groupConsultJdbcProperties;

    @Bean(name = "groupDataSource")
    public DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(groupConsultJdbcProperties.getDriverClassName());
        druidDataSource.setUrl(groupConsultJdbcProperties.getUrl());
        druidDataSource.setUsername(groupConsultJdbcProperties.getUsername());
        druidDataSource.setPassword(groupConsultJdbcProperties.getPassword());
        druidDataSource.setDbType("mysql");
        druidDataSource.setMaxActive(30);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(1);
        try {
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    @Bean(name = "groupSqlSessionFactory")
    public SqlSessionFactoryBean getSqlSession() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDataSource());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlmapper/group/*.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean;
    }

    @Bean(name = "groupMobileTransactionManager")
    public DataSourceTransactionManager mobileTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(getDataSource());
        return dataSourceTransactionManager;
    }
}
