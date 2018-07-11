package com.redpacket.activity.mapper;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.redpacket.activity.bean.Department;
import com.redpacket.activity.bean.RedpacketRecord;
import com.redpacket.activity.bean.SpringBootTestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentMapperTest.class);
    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private SpringBootTestMapper springBootTestMapper;


    @Test
    public void testInsert(){
        Department department = new Department();
        department.setDepartmentName("财务部");
        departmentMapper.insertDepartment(department);
    }

    @Test
    public void testQuery(){
        Department department = departmentMapper.getDepartmentById(1);
        LOGGER.info("department : {}",department);
    }

    @Test
    public void testInsertSpringBootInfo(){
        SpringBootTestBean springBootTestBean = new SpringBootTestBean();
        springBootTestBean.setcTime(new Date());
        springBootTestBean.setRedpackedId(12);
        springBootTestBean.setType(1);
        springBootTestBean.setTvid("xxxxx-test1238792");
        try {
            springBootTestMapper.insertSpringBootTestInfo(springBootTestBean);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                LOGGER.error("---------------------------数据库唯一索引---------------------");
            }
            LOGGER.error("*******exception: ",e);
        }
    }



}
