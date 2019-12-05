package com.sunxu.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author 孙许
 * @Date 2019/12/05 2:50
 * @Description
 */
@ConfigurationProperties(prefix = "mtc.jdbc")
public class MtcJdbcProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
