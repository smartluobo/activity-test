package com.redpacket.activity;

import com.redpacket.activity.properties.ThreadPoolProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ExecutorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolTest.class);

    @Resource
    private ThreadPoolProperties threadPoolProperties;

    @Resource
    private ExecutorService executorService;

    @Test
    public void testThreadPool(){
        LOGGER.info("threadPoolProperties: {}",threadPoolProperties.toString());
    }


    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(10000));
        }
    }
}
