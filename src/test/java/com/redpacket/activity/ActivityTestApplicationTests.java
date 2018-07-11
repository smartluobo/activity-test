package com.redpacket.activity;

import com.redpacket.activity.config.LuaConfig;
import com.redpacket.activity.utils.RedisLuaUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityTestApplicationTests {

	@Resource
	private DataSource dataSource;

	@Resource
	private LuaConfig luaConfig;

	@Resource
	private RedisLuaUtil redisLuaUtil;

	private List<String> luaArgs = new ArrayList<>();
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTestApplicationTests.class);

	@Test
	public void testPath(){
		//LOGGER.info(luaPath);
	}


	//测试ioc容器加载
	@Test
	public void contextLoads() {
		LOGGER.info("driver class name : {}",dataSource.getClass().getName());
	}

	//测试lua脚本加载
	@Test
	public void testLuaLoad(){
		LOGGER.info(luaConfig.getRedpacketLuaScript());
	}

	//测试lua脚本执行
	@Test
	public void testExecuteLua() throws Exception{
		List<String> params = new ArrayList<>();
		params.add("101044404-4bd5843a0e004eef");
		for (int i = 0; i < 10; i++) {
			Object result = redisLuaUtil.executeLua(luaConfig.getTestLuaScript(),params,luaArgs);
			LOGGER.info("test lua execute return result : {}",result.toString());
			Thread.sleep(1000);
		}
		//params.add("hello lua ");

	}

	//测试lua脚本SHA加密脚本
	@Test
	public void testExecuteLuaSHA() throws Exception{
		List<String> params = new ArrayList<>();
		params.add("101044404-4bd5843a0e004eef");
		//params.add("hello lua  SHA");
		for (int i = 0; i < 10; i++) {
			Object result = redisLuaUtil.executeLuaSHA(luaConfig.getTestLuaScriptSHA(),params,luaArgs);
			LOGGER.info("*************test lua execute return result : {}",result.toString());
			Thread.sleep(1000);
		}
	}

}
