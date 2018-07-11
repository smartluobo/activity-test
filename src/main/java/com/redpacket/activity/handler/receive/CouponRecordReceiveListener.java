package com.redpacket.activity.handler.receive;

import com.redpacket.activity.bean.CouponRecord;
import com.redpacket.activity.constan.Constant;
import com.redpacket.activity.mapper.CouponRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CouponRecordReceiveListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponRecordReceiveListener.class);

    @Resource
    private CouponRecordMapper couponRecordMapper;

    //@RabbitListener(queues = Constant.TEST_COUPON_QUEUE_NAME)
    public void couponRecordReceive(CouponRecord couponRecord){
        try {
            LOGGER.info("*************************TestRedPacketMqListener: coupon receive********************，消息内容： {} ",couponRecord.toString());
            couponRecordMapper.insertCouponRecord(couponRecord);
        }catch (Exception e){
            LOGGER.info("*************************TestRedPacketMqListener: ***************** happen exception: ",e);
        }
    }
}
