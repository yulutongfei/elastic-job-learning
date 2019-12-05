package com.sunxu.demo;

import com.sunxu.demo.domain.User;
import com.sunxu.demo.mapper.group.GroupMapper;
import com.sunxu.demo.mapper.mtc.MtcMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = ElasticJobLearningApplication.class)
class ElasticJobLearningApplicationTests {

    @Resource
    private MtcMapper mtcMapper;
    @Resource
    private GroupMapper groupMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testMtc() {
        User user = new User();
        user.setName("孙许");
        Integer id = mtcMapper.insert(user);
        System.out.println(id);
    }

    @Test
    public void testGroup() {
        User user = new User();
        user.setName("孙许");
        Integer id = groupMapper.insert(user);
        System.out.println(id);
    }

}
