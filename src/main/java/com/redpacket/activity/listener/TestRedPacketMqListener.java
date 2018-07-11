package com.redpacket.activity.listener;

import com.redpacket.activity.bean.CouponRecord;
import com.redpacket.activity.bean.RedpacketRecord;
import com.redpacket.activity.constan.Constant;
import com.redpacket.activity.mapper.CouponRecordMapper;
import com.redpacket.activity.mapper.RedPacketRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestRedPacketMqListener{

    private static final Logger LOGGER = LoggerFactory.getLogger(TestRedPacketMqListener.class);

    @Resource
    private RedPacketRecordMapper redPacketRecordMapper;

    @Resource
    private CouponRecordMapper couponRecordMapper;

   // @RabbitListener(queues = Constant.TEST_REDPACKET_QUEUE_NAME)
    public void redpacketRecordReceive(RedpacketRecord redpacketRecord) {
        try {
            LOGGER.info("*************************TestRedPacketMqListener: ********************，消息内容： {} ",redpacketRecord.toString());
            redPacketRecordMapper.insertRedPacketRecord(redpacketRecord);
        }catch (Exception e){
            LOGGER.info("*************************TestRedPacketMqListener: ***************** happen exception: ",e);
        }
    }

    //@RabbitListener(queues =Constant.TEST_COUPON_QUEUE_NAME)
    public void couponRecordReceive(CouponRecord couponRecord){
        try {
            LOGGER.info("*************************TestRedPacketMqListener: coupon receive********************，消息内容： {} ",couponRecord.toString());
            couponRecordMapper.insertCouponRecord(couponRecord);
        }catch (Exception e){
            LOGGER.info("*************************TestRedPacketMqListener: ***************** happen exception: ",e);
        }
    }
}
