package com.sunxu.demo.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.sunxu.autoconfig.ElasticSimpleJob;
import com.sunxu.demo.domain.User;
import com.sunxu.demo.mapper.group.GroupMapper;
import com.sunxu.demo.mapper.mtc.MtcMapper;

import javax.annotation.Resource;

/**
 * @Author 孙许
 * @Date 2019/12/03 21:51
 * @Description
 */
@ElasticSimpleJob(jobName = "mySimpleJob", cron = "*/5 * * * * ?", shardingTotalCount = 5, overwrite = true)
public class MySimpleJob implements SimpleJob {

    @Resource
    private MtcMapper mtcMapper;
    @Resource
    private GroupMapper groupMapper;

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("分片项：" + shardingContext.getShardingItem() + "总分片项" + shardingContext.getShardingTotalCount());
    }
}
