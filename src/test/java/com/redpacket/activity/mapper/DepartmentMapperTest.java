package com.redpacket.activity.mapper;

import com.redpacket.activity.bean.Department;
import com.redpacket.activity.bean.RedpacketRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentMapperTest.class);
    @Resource
    private DepartmentMapper departmentMapper;


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



}
