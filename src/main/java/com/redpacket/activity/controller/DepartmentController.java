package com.redpacket.activity.controller;

import com.redpacket.activity.bean.Department;
import com.redpacket.activity.mapper.DepartmentMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @Resource
    private DepartmentMapper departmentMapper;

    @GetMapping("/add")
    public Department insertDepartmentById(Department department){
        departmentMapper.insertDepartment(department);
        return department;
    }

    @GetMapping("/get/{id}")
    public Department getDepartmentById(@PathVariable("id") Integer id){
        Department department = departmentMapper.getDepartmentById(id);
        return department;
    }

    @GetMapping("/del/{id}")
    public int deleteRedpacketRecordById(@PathVariable("id") Integer id){
        int count = departmentMapper.deleteDepartmentById(id);
        return count;
    }

    @GetMapping("/update")
    public int updateRedpacketRecordById(Department department){
        int count = departmentMapper.updateDepartmentById(department);
        return count;
    }
}
