package com.redpacket.activity.handler.receive;

import com.rabbitmq.client.Channel;
import com.redpacket.activity.bean.RedpacketRecord;
import com.redpacket.activity.constan.Constant;
import com.redpacket.activity.mapper.RedPacketRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

@Component
public class RedpacketRecordReceiveListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedpacketRecordReceiveListener.class);

    @Resource
    private RedPacketRecordMapper redPacketRecordMapper;

    //@RabbitListener(queues = Constant.TEST_REDPACKET_QUEUE_NAME)
    public void redpacketRecordReceive(RedpacketRecord redpacketRecord) {
        try {
            LOGGER.info("*************************TestRedPacketMqListener: ********************，content： {} ",redpacketRecord.toString());
            redPacketRecordMapper.insertRedPacketRecord(redpacketRecord);
        }catch (Exception e){
            LOGGER.info("*************************TestRedPacketMqListener: ***************** happen exception: ",e);
        }
    }

//    @RabbitListener(queues = Constant.TEST_REDPACKET_QUEUE_NAME)
//    public void redpacketRecordReceive1(RedpacketRecord redpacketRecord, Channel channel) {
//        try {
//            LOGGER.info("*************************TestRedPacketMqListener: ********************，content： {} ",redpacketRecord.toString());
//            redPacketRecordMapper.insertRedPacketRecord(redpacketRecord);
//        }catch (Exception e){
//            LOGGER.info("*************************TestRedPacketMqListener: ***************** happen exception: ",e);
//        }
//    }
//
//    @RabbitListener(queues = Constant.TEST_REDPACKET_QUEUE_NAME)
//    public void redpacketRecordReceive2(RedpacketRecord redpacketRecord, Channel channel) {
//        try {
//            LOGGER.info("*************************TestRedPacketMqListener: ********************，content： {} ",redpacketRecord.toString());
//            redPacketRecordMapper.insertRedPacketRecord(redpacketRecord);
//        }catch (Exception e){
//            LOGGER.info("*************************TestRedPacketMqListener: ***************** happen exception: ",e);
//        }
//    }
//
//    @RabbitListener(queues = Constant.TEST_REDPACKET_QUEUE_NAME)
//    public void redpacketRecordReceive3(RedpacketRecord redpacketRecord, Channel channel) {
//        try {
//            LOGGER.info("*************************TestRedPacketMqListener: ********************，content： {} ",redpacketRecord.toString());
//            redPacketRecordMapper.insertRedPacketRecord(redpacketRecord);
//        }catch (Exception e){
//            LOGGER.info("*************************TestRedPacketMqListener: ***************** happen exception: ",e);
//        }
//    }


}
