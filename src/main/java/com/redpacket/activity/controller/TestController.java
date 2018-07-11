package com.redpacket.activity.controller;

import com.redpacket.activity.bean.RedpacketRecord;
import com.redpacket.activity.constan.Constant;
import com.redpacket.activity.handler.sender.RedpacketRecordSender;
import com.redpacket.activity.service.GrabService;
import com.redpacket.activity.utils.XredisTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ExecutorService;

@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private String tvidSuffix = "-5ba4959e5583fc32";

    private String userKeyPrefix = "728_user_";

    @Resource
    private XredisTemplate xredisTemplate;

    @Resource(name="executorService")
    private ExecutorService executorService;

    @Resource(name="receiveExecutorService")
    private ExecutorService receiveExecutorService;

    @Resource(name = "sendExecutorService")
    private ExecutorService sendExecutorService;

    @Resource
    private RedpacketRecordSender redpacketRecordSender;

    @Resource
    private GrabService grabService;


    //用户保存到redis
    @GetMapping("/insertUser/{startIndex}/{count}")
    public String insertUser(@PathVariable("startIndex") Integer startIndex,@PathVariable("count") Integer count){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            startIndex++;
            insertUserToRedis(startIndex);
        }
        long endTime = System.currentTimeMillis();
        return count+"user insert to redis cost time :"+(endTime-startTime)/1000+"s";
    }

    private void insertUserToRedis(Integer dnum){
        final String userKey = userKeyPrefix+dnum+tvidSuffix;
        executorService.execute(() -> xredisTemplate.hashSet(userKey,"st","1"));

    }

    //抢红包接口
    @GetMapping("/grab/{startIndex}/{count}/{currDate}/{roundId}")
    public String grab(@PathVariable("startIndex") Integer startIndex,@PathVariable("count") Integer count,
                       @PathVariable("currDate") Integer currDate,@PathVariable("roundId") Integer roundId){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            startIndex++;
            grabRedpacket(startIndex,currDate,roundId);
        }
        long endTime = System.currentTimeMillis();
        return count+"grab redpacket request handler complete cost time :"+(endTime-startTime)/1000+"s";
    }

    private void grabRedpacket(Integer startIndex, Integer currDate,Integer roundId) {
        final String dnumStr = String.valueOf(startIndex);
        final String tvid = dnumStr+tvidSuffix;
        executorService.execute(() -> {
            try {
                grabService.grabRepacket(tvid,roundId,currDate);
            }catch (Exception e){
                LOGGER.error("exception : ",e);
            }
        });

    }

    @GetMapping("/upgrade/{type}")
    public String upgrade(@PathVariable("type") String type){
        return null;
    }

    @GetMapping("/singleSendMq/{startIndex}/{count}")
    public String singleSendMq(@PathVariable("startIndex") long startIndex,@PathVariable("count") Integer count){
        long startTime = System.currentTimeMillis();
        RedpacketRecord redPacketRecord = new RedpacketRecord();
        for (int i = 0; i < count; i++) {
            startIndex++;
            String tvid = startIndex + tvidSuffix;
            redPacketRecord.setTvid(tvid);
            redPacketRecord.setIp("192.168.1.100");
            redPacketRecord.setRoundId(22);
            redPacketRecord.setPoolId(22);
            redPacketRecord.setType("1");
            redPacketRecord.setrValue(198);
            redPacketRecord.setcTime(new Date());
            redpacketRecordSender.sendMsg(Constant.TEST_REDPACKET_QUEUE_KEY,redPacketRecord);
        }
        long endTime = System.currentTimeMillis();
        return count +"message single send to mq complete cost Time : "+(endTime-startTime)/1000+"s";
    }

    @GetMapping("/concurrentSendMq/{dnum}/{count}")
    public String concurrentSendMq(@PathVariable("dnum") long dnum,@PathVariable("count") int count){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            dnum++;
            RedpacketRecord redpacketRecord = new RedpacketRecord();
            String tvid = dnum + tvidSuffix;
            redpacketRecord.setTvid(tvid);
            redpacketRecord.setIp("192.168.1.100");
            redpacketRecord.setRoundId(22);
            redpacketRecord.setPoolId(22);
            redpacketRecord.setType("1");
            redpacketRecord.setrValue(198);
            redpacketRecord.setcTime(new Date());
            sendExecutorService.execute(() -> redpacketRecordSender.sendMsg(Constant.TEST_REDPACKET_QUEUE_KEY,redpacketRecord));
        }
        long endTime = System.currentTimeMillis();
        return count+"message concurrent send mq complete cost Time :  "+(endTime-startTime)/1000 +"s";
    }

}
