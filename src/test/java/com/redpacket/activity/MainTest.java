package com.redpacket.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class MainTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainTest.class);

    public static void main(String[] args) {
        TestBean testBean = new TestBean();
        TestData testData = new TestData();
        testBean.setCode("11122");
        testBean.setId(12);
        testBean.setData(testData);
        testData.setId(33);
        testData.setType("5");
        testData.setMsg("hello world");
        System.out.println(testBean);
        LOGGER.error("hahha:{}",testBean);
    }
}
