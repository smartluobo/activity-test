package com.redpacket.activity.handler.sender;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedpacketRecordSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedpacketRecordSender.class);

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String mqKey, final Object bean) {
        try {
            LOGGER.info("用户抢到红包发送红包记录信息到MQ--queue: " + mqKey + "消息内容: " + JSON.toJSONString(bean));
            rabbitTemplate.convertAndSend(mqKey, bean);
        } catch (Exception e) {
            LOGGER.error("用户抢到红包发送红包记录信息到MQ--queue: " + mqKey + "消息内容: " +
                    JSON.toJSONString(bean) + "发送异常，异常信息： ", e);
        }

    }
}
