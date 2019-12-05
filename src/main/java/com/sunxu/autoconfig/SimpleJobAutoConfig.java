package com.sunxu.autoconfig;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author 孙许
 * @Date 2019/12/05 0:53
 * @Description
 */
@Configuration
@ConditionalOnBean(CoordinatorRegistryCenter.class)
@AutoConfigureAfter(ZookeeperAutoConfig.class)
public class SimpleJobAutoConfig {
    @Autowired
    private ApplicationContext applicationContext;

    @Resource
    private CoordinatorRegistryCenter zkCenter;

    @PostConstruct
    public void initSimpleJob() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(ElasticSimpleJob.class);
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object instance = entry.getValue();
            Class<?>[] interfaces = instance.getClass().getInterfaces();
            for (Class<?> superInterface : interfaces) {
                if (superInterface == SimpleJob.class) {
                    ElasticSimpleJob annotation = instance.getClass().getAnnotation(ElasticSimpleJob.class);
                    JobCoreConfiguration jcc = JobCoreConfiguration
                            .newBuilder(annotation.jobName(), annotation.cron(), annotation.shardingTotalCount())
                            .build();
                    SimpleJobConfiguration jtc = new SimpleJobConfiguration(jcc, instance.getClass().getCanonicalName());
                    LiteJobConfiguration ljc = LiteJobConfiguration.newBuilder(jtc).overwrite(annotation.overwrite()).build();
                    new SpringJobScheduler((ElasticJob) instance, zkCenter, ljc, new ElasticJobListener[0]).init();
                }
            }
        }
    }
}
