package com.redpacket.activity;

import com.redpacket.activity.bean.CouponRecord;
import com.redpacket.activity.bean.RedpacketRecord;
import com.redpacket.activity.constan.Constant;
import com.redpacket.activity.handler.sender.RedpacketRecordSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqTest.class);

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RedpacketRecordSender redpacketRecordSender;

    @Test
    public void testRedPacketMqSend(){
        for (int i = 0; i < 10; i++) {
            RedpacketRecord redpacketRecord = new RedpacketRecord();
            redpacketRecord.setIp("192.168.1.100");
            redpacketRecord.setPoolId(655351);
            redpacketRecord.setRoundId(22);
            redpacketRecord.setrValue(128);
            redpacketRecord.setTvid("101010101-db8f400ff5b452f6");
            redpacketRecord.setType("1");
            redpacketRecord.setcTime(new Date());
            redpacketRecordSender.sendMsg(Constant.TEST_REDPACKET_QUEUE_KEY,redpacketRecord);
        }

    }

    public void testCouponMqSend(){
        CouponRecord couponRecord = new CouponRecord();
        couponRecord.setCode("XSDF25SER");
        couponRecord.setDiscountAmount(100.0);
        couponRecord.setPoolId(22);
        couponRecord.setRoundId(22);
        couponRecord.setTvid("101010101-db8f400ff5b452f6");
        couponRecord.setcTime(new Date());
    }

    @Test
    public void testMqReceive() throws Exception {
        Thread.sleep(2000);
    }

}
