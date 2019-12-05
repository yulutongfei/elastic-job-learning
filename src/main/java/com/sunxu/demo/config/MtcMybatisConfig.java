package com.sunxu.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author 孙许
 * @Date 2019/12/04 20:42
 * @Description
 */
@Configuration
@ConditionalOnProperty("mtc.jdbc.url")
@EnableConfigurationProperties(MtcJdbcProperties.class)
public class MtcMybatisConfig {
    @Autowired
    private MtcJdbcProperties mtcJdbcProperties;

    @Bean(name = "mtcDataSource")
    public DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(mtcJdbcProperties.getDriverClassName());
        druidDataSource.setUrl(mtcJdbcProperties.getUrl());
        druidDataSource.setUsername(mtcJdbcProperties.getUsername());
        druidDataSource.setPassword(mtcJdbcProperties.getPassword());
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

//    public static void main(String[] args) {
//        ConfigurableApplicationContext context = new SpringApplicationBuilder(MtcMybatisConfig.class).web(WebApplicationType.NONE).run(args);
//
//        MtcJdbcProperties user = context.getBean(MtcJdbcProperties.class);
//        System.out.println(user.getDriverClassName());
//        context.close();
//
//    }

    @Bean(name = "mtcSqlSessionFactory")
    public SqlSessionFactoryBean getSqlSession() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDataSource());
        sqlSessionFactoryBean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:sqlmapper/mtc/*.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean;
    }

    @Bean(name = "mtcMobileTransactionManager")
    public DataSourceTransactionManager mobileTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(getDataSource());
        return dataSourceTransactionManager;
    }
}
