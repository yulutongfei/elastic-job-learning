package com.sunxu.autoconfig;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 孙许
 * @Date 2019/12/05 0:49
 * @Description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ElasticSimpleJob {
    String jobName() default "";

    String cron() default "";

    int shardingTotalCount() default 1;

    boolean overwrite() default true;
}
