package com.redpacket.activity.service;

import com.redpacket.activity.bean.CouponPool;
import com.redpacket.activity.bean.CouponRecord;
import com.redpacket.activity.bean.RedpacketRecord;
import com.redpacket.activity.config.LuaConfig;
import com.redpacket.activity.constan.Constant;
import com.redpacket.activity.constan.RedPacketConstant;
import com.redpacket.activity.handler.sender.CouponRecordSender;
import com.redpacket.activity.handler.sender.RedpacketRecordSender;
import com.redpacket.activity.utils.RedisLuaUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GrabService {

    public static CouponPool couponPool ;
    static {
        couponPool = new CouponPool();
        couponPool.setCode("XSGFTD0251");
        couponPool.setDiscountAmount(50.0);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(GrabService.class);
    @Resource
    private LuaConfig luaConfig;

    @Resource
    private RedisLuaUtil redisLuaUtil;

    @Resource
    private RedpacketRecordSender redpacketRecordSender;

    @Resource
    private CouponRecordSender couponRecordSender;

    private List<String> argsList = new ArrayList<>();

    public void grabRepacket(String tvid,Integer roundId,Integer currDate) throws Exception{
        Integer yesterDayDate = currDate - 1;
        Integer beforeDate = currDate - 2;
        String packetRatio = "0.8";
        long currentTimeMillis = System.currentTimeMillis();
        String result = grabRedpacket(tvid,roundId,currDate,yesterDayDate,beforeDate,currentTimeMillis,packetRatio);
//        LOGGER.info("current tvid : {} roundId : {} execute lua script return result :{}",tvid,roundId,result);
//        if (result != null && result.split(RedPacketConstant.SEPARATOR).length == 2){
//            String[] split = result.split(RedPacketConstant.SEPARATOR);
//            if (RedPacketConstant.SACK_MARK.equals(split[0])){
//                result = grabCoupon(tvid, roundId, currDate, roundId, currentTimeMillis);
//                LOGGER.info("redpacket grab sack execute coupon lua script return result : {} ",result);
//                if (RedPacketConstant.SACK_MARK.equals(result)){//用户未抢到优惠券或当日已抢过优惠券
//                   return;
//                }
//                parseCouponRecord(result,tvid,couponPool);
//            }
//        }
//        String ip = "192.168.1.100";
//       parseRedPacketRecord(result, tvid,ip);
    }

    private void parseCouponRecord(String result, String tvid, CouponPool couponPool) {
        String[] record = result.split(RedPacketConstant.SEPARATOR);
        long currentTime = System.currentTimeMillis();
        if (record.length == RedPacketConstant.CP_RETURN_LENGTH){
            String couponId = record[RedPacketConstant.CP_RETURN_INDEX_CPID];
            String roundId = record[RedPacketConstant.CP_RETURN_INDEX_RID];
            String time = record[RedPacketConstant.CP_RETURN_INDEX_TIME];
            CouponRecord couponRecord = new CouponRecord();
            couponRecord.setcTime(new Date(Long.valueOf(time)));
            couponRecord.setPoolId(Integer.valueOf(couponId));
            couponRecord.setRoundId(Integer.valueOf(roundId));
            couponRecord.setDiscountAmount(couponPool.getDiscountAmount());
            couponRecord.setCode(couponPool.getCode());
            couponRecord.setTvid(tvid);
            couponRecordSender.sendMsg(Constant.TEST_COUPON_QUEUE_KEY,couponRecord);
        }
    }


    public String grabCoupon(String tvid, Integer roundId, Integer currDate, Integer couponId, long currentTimeMillis){
        try {
            List<String> params = new ArrayList<>();
            params.add(tvid);
            params.add(String.valueOf(couponId));
            params.add(String.valueOf(currDate));
            params.add(String.valueOf(currentTimeMillis));
            params.add(String.valueOf(roundId));
            String result = (String)redisLuaUtil.executeLuaSHA(luaConfig.getCouponLuaScriptSHA(),params,argsList);
            return result;
        }catch (Exception e){
            LOGGER.error("exception : ",e);
            return null;
        }

    }

    private String grabRedpacket(String tvid, Integer roundId, Integer currDate, Integer yesterDayDate, Integer beforeDate, long currentTimeMillis, String packetRatio) throws Exception {
        List<String> params = new ArrayList<>();
        params.add(tvid);
        params.add(String.valueOf(roundId));
        params.add(String.valueOf(currDate));
        params.add(String.valueOf(yesterDayDate));
        params.add(String.valueOf(beforeDate));
        params.add(String.valueOf(currentTimeMillis));
        params.add(packetRatio);
        params.add("6600");
        String result = (String)redisLuaUtil.executeLuaSHA(luaConfig.getRedpacketLuaScriptSHA(),params,argsList);
        return result;
    }

    public void parseRedPacketRecord( String result, String tvid,String ip){
        String[] record = result.split(",");
        RedpacketRecord redPacketRecord = new RedpacketRecord();
        if(record.length >= 6) {
            String roundId = record[RedPacketConstant.RP_RECORD_INDEX_ROUNDID];//场次id
            String redPacketId = record[RedPacketConstant.RP_RECORD_INDEX_REDID];//红包id
            String actualAmount = record[RedPacketConstant.RP_RECORD_INDEX_REDAMOUNT];//实际抢到金额
            String time = record[RedPacketConstant.RP_RECORD_INDEX_TIME];//抢到红包的时间
            String upgradeAmount = record[RedPacketConstant.RP_RECORD_INDEX_UPAMOUNT];//升级金额
            redPacketRecord.setType(RedPacketConstant.REDPACKET_TYPE_GENERAL);
            redPacketRecord.setcTime(new Date(Long.valueOf(time)));
            redPacketRecord.setRoundId(Integer.valueOf(roundId));
            redPacketRecord.setTvid(tvid);
            redPacketRecord.setPoolId(Integer.valueOf(redPacketId));
            redPacketRecord.setrValue(Integer.valueOf(actualAmount));
            redPacketRecord.setIp(ip);
            if (StringUtils.isNotBlank(upgradeAmount)) {//表示为升级红包
                redPacketRecord.setType(RedPacketConstant.REDPACKET_TYPE_UPGRADE);
                redPacketRecord.setrValue(Integer.valueOf(upgradeAmount));
                //redpacketRecordSender.sendMsg(Constant.TEST_REDPACKET_QUEUE_KEY, redPacketRecord);//发送异步消息
                return;
            } else {
                //redpacketRecordSender.sendMsg(Constant.TEST_REDPACKET_QUEUE_KEY, redPacketRecord);//发送异步消息
            }
        }
    }


    public void grabRepacket() {
        try {
            List<String> params = new ArrayList<>();


        }catch (Exception e){
            LOGGER.error("");
        }
    }
}
