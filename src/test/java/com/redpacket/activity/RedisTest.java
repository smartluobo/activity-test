package com.redpacket.activity;


import com.redpacket.activity.utils.XredisTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTest.class);
    @Resource
    private XredisTemplate xredisTemplate;

    @Test
    public void test1(){
        String value = xredisTemplate.getValue("zhangsan");
        LOGGER.info("value: {}",value);
    }
}
