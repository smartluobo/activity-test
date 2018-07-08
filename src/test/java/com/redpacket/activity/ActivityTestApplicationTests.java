package com.redpacket.activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityTestApplicationTests {

	@Resource
	private DataSource dataSource;

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTestApplicationTests.class);
	@Test
	public void contextLoads() {
		LOGGER.info("driver class name : {}",dataSource.getClass().getName());

	}

}
