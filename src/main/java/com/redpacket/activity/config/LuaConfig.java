package com.redpacket.activity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.util.ResourceUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;

@Configuration
public class LuaConfig implements InitializingBean{

    private static final Logger LOGGER = LoggerFactory.getLogger(LuaConfig.class);

    @Value("${lua.path}")
    private String luaPath ;

    @Resource
    private JedisConnectionFactory jdc;

    private String redpacketLuaScript ;

    private String couponLuaScript;

    private String upgradeLuaScript;

    private String testLuaScript;

    private String redpacketLuaScriptSHA;

    private String couponLuaScriptSHA;

    private String upgradeLuaScriptSHA;

    private String testLuaScriptSHA;

    @Override
    public void afterPropertiesSet() throws Exception {
        initGrabRedPacketLuaScript();
        initCouponLuaScriptLuaScript();
        initUpgradeLuaScript();
        initTestLuaScript();
    }

    //初始化抢红包lua脚本
    private void initGrabRedPacketLuaScript()throws Exception{
        FileInputStream in = null;
        Jedis jedis = null;
        try {
            File file = ResourceUtils.getFile(luaPath+"/redpacket.lua");
            in=new FileInputStream(file);
            int size=in.available();
            byte[] buffer=new byte[size];
            in.read(buffer);
            in.close();
            String redpacketLuaScript=new String(buffer);
            this.redpacketLuaScript = redpacketLuaScript;
            jedis = (Jedis)jdc.getConnection().getNativeConnection();
            redpacketLuaScriptSHA = jedis.scriptLoad(this.redpacketLuaScript);
            LOGGER.info("*********redpacketLuaScriptSHA : {} ",redpacketLuaScriptSHA);
        }catch (Exception e){
            LOGGER.error("load grab redpacket lua script happen exception : ",e);
        }finally {
            if (in != null){
                in.close();
            }
            if (jedis != null){
                jedis.close();
            }
        }
    }

    //初始化抢优惠券lua脚本
    private void initCouponLuaScriptLuaScript()throws Exception{
        FileInputStream in = null;
        Jedis jedis = null;
        try {
            File file = ResourceUtils.getFile(luaPath+"/conpon.lua");
            in=new FileInputStream(file);
            int size=in.available();
            byte[] buffer=new byte[size];
            in.read(buffer);
            in.close();
            String couponLuaScript=new String(buffer);
            this.couponLuaScript = couponLuaScript;
            jedis = (Jedis) jdc.getConnection().getNativeConnection();
            couponLuaScriptSHA = jedis.scriptLoad(this.couponLuaScript);
            LOGGER.info("couponLuaScriptSHA : {}",couponLuaScriptSHA);
        }catch (Exception e){
            LOGGER.error("load grab coupon lua script happen exception : ",e);
        }finally {
            if (in != null){
                in.close();
            }
            if (jedis != null){
                jedis.close();
            }
        }
    }

    //初始化升级红包脚本
    private void initUpgradeLuaScript()throws Exception{
        FileInputStream in = null;
        Jedis jedis = null;
        try {
            File file = ResourceUtils.getFile(luaPath+"/upgrade.lua");
            in=new FileInputStream(file);
            int size=in.available();
            byte[] buffer=new byte[size];
            in.read(buffer);
            in.close();
            String upgradeLuaScript=new String(buffer);
            this.upgradeLuaScript = upgradeLuaScript;
            jedis = (Jedis) jdc.getConnection().getNativeConnection();
            upgradeLuaScriptSHA = jedis.scriptLoad(this.upgradeLuaScript);
            LOGGER.info("upgradeLuaScriptSHA : {}",upgradeLuaScriptSHA);
        }catch (Exception e){
            LOGGER.error("load upgrade redpacket lua script happen exception : ",e);
        }finally {
            if (in != null){
                in.close();
            }
            if (jedis != null){
                jedis.close();
            }
        }
    }

    //初始化测试Lua 脚本
    private void initTestLuaScript()throws Exception{
        FileInputStream in = null;
        Jedis jedis = null;
        try {
            File file = ResourceUtils.getFile(luaPath+"/test.lua");
            in=new FileInputStream(file);
            int size=in.available();
            byte[] buffer=new byte[size];
            in.read(buffer);
            in.close();
            String testLuaScript=new String(buffer);
            this.testLuaScript = testLuaScript;
            jedis = (Jedis) jdc.getConnection().getNativeConnection();
            testLuaScriptSHA = jedis.scriptLoad(this.testLuaScript);
            LOGGER.info("testLuaScriptSHA : {}",testLuaScriptSHA);
        }catch (Exception e){
            LOGGER.error("load test lua script happen exception : ",e);
        }finally {
            if (in != null){
                in.close();
            }
            if (jedis != null){
                jedis.close();
            }
        }
    }

    public String getRedpacketLuaScript() {
        return redpacketLuaScript;
    }

    public String getCouponLuaScript() {
        return couponLuaScript;
    }

    public String getUpgradeLuaScript() {
        return upgradeLuaScript;
    }

    public String getRedpacketLuaScriptSHA() {
        return redpacketLuaScriptSHA;
    }

    public String getCouponLuaScriptSHA() {
        return couponLuaScriptSHA;
    }

    public String getUpgradeLuaScriptSHA() {
        return upgradeLuaScriptSHA;
    }

    public String getTestLuaScript() {
        return testLuaScript;
    }

    public String getTestLuaScriptSHA() {
        return testLuaScriptSHA;
    }
}
