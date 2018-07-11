package com.redpacket.activity.config;

import com.redpacket.activity.constan.Constant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RabbitMqConfig {

    @Resource
    private CachingConnectionFactory cachingConnectionFactory;

    //声明队列
    @Bean
    public Queue testRedpacketQueue(){
        Queue queue = new Queue(Constant.TEST_REDPACKET_QUEUE_NAME);
        return queue;
    }
    @Bean
    public Queue testCouponQueue(){
        Queue queue = new Queue(Constant.TEST_COUPON_QUEUE_NAME);
        return queue;
    }

    //声明交换机\
    @Bean
    public DirectExchange directTestExchange(){
        return new DirectExchange(Constant.EXCHANGE_NAME);
    }

    //交换机和队列绑定
    @Bean
    public Binding bindingRedPacketQueueToExchange(){
        return BindingBuilder.bind(testRedpacketQueue()).to(directTestExchange()).with(Constant.TEST_REDPACKET_QUEUE_KEY);
    }

    @Bean
    public Binding bindingCouponQueueToExchange(){
        return BindingBuilder.bind(testCouponQueue()).to(directTestExchange()).with(Constant.TEST_COUPON_QUEUE_KEY);
    }

    @Bean
    public JsonMessageConverter jsonMessageConverter(){
        return new JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
       rabbitTemplate.setMessageConverter(jsonMessageConverter());
       rabbitTemplate.setExchange(Constant.EXCHANGE_NAME);
        return rabbitTemplate;
    }
}
