package com.sunxu.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author 孙许
 * @Date 2019/12/05 0:22
 * @Description
 */
@ConfigurationProperties(prefix = "elasticjob.zookeeper")
public class ZookeeperProperties {

    /**
     * zookeeper地址列表
     */
    private String serverList;

    /**
     * zookeeper命名空间
     */
    private String namespace;

    public String getServerList() {
        return serverList;
    }

    public void setServerList(String serverList) {
        this.serverList = serverList;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
