package com.redpacket.activity.auto;

import com.redpacket.activity.properties.ThreadPoolProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(ThreadPoolProperties.class)
public class ThreadPoolAutoConfiguration {
    @Resource
    private ThreadPoolProperties threadPoolProperties;

    @Bean
    public ExecutorService executorService(){
        return new ThreadPoolExecutor(threadPoolProperties.getCorePoolSize(),
                threadPoolProperties.getMaximumPoolSize(),
                threadPoolProperties.getKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(threadPoolProperties.getQueueLength()),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Bean
    public ExecutorService receiveExecutorService(){
        return new ThreadPoolExecutor(threadPoolProperties.getReceiveCorePoolSize(),
                threadPoolProperties.getReceiveMaximumPoolSize(),
                threadPoolProperties.getReceiveKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(threadPoolProperties.getReceiveQueueLength()),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Bean
    public ExecutorService sendExecutorService(){
        return new ThreadPoolExecutor(threadPoolProperties.getSendCorePoolSize(),
                threadPoolProperties.getSendMaximumPoolSize(),
                threadPoolProperties.getSendKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(threadPoolProperties.getSendQueueLength()),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
