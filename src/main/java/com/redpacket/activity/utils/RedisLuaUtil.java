package com.redpacket.activity.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RedisLuaUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLuaUtil.class);

    @Resource
    private JedisConnectionFactory jdc;

    public Object executeLua(String script, List<String> params, List<String> ags) throws Exception{
        Jedis jedis = null;
        try {
            jedis = (Jedis)jdc.getConnection().getNativeConnection();
            Object result = jedis.eval(script, params, ags);
            return result;
        }catch (Exception e){
            LOGGER.error("redis execute lua script exception script content :"+script+" params: "+params+" args : "+ags,e);
            throw e;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public Object executeLuaSHA(String script, List<String> params,List<String> ags) throws Exception{
        Jedis jedis = null;
        try {
            jedis = (Jedis)jdc.getConnection().getNativeConnection();
            Object result = jedis.evalsha(script, params, ags);
            return result;
        }catch (Exception e){
            LOGGER.error("redis execute lua script SHA exception script content :"+script+" params: "+params+" args : "+ags,e);
            throw e;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

}
