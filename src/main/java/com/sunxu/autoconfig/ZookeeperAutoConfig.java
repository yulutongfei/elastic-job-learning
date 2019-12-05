package com.sunxu.autoconfig;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 孙许
 * @Date 2019/12/05 0:20
 * @Description
 */
@Configuration
@ConditionalOnProperty("elasticjob.zookeeper.serverList")
@EnableConfigurationProperties(ZookeeperProperties.class)
public class ZookeeperAutoConfig {

    @Autowired
    private ZookeeperProperties zookeeperProperties;

    @Bean(initMethod = "init")
    public CoordinatorRegistryCenter zkCenter() {
        ZookeeperConfiguration zc = new ZookeeperConfiguration(zookeeperProperties.getServerList(), zookeeperProperties.getNamespace());
        CoordinatorRegistryCenter crc = new ZookeeperRegistryCenter(zc);
        return crc;
    }
}
