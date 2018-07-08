package com.redpacket.activity.mapper;

import com.redpacket.activity.bean.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    Department getDepartmentById(Integer id);

    @Delete("delete from department where id = #{id}")
    int deleteRedpacketRecordById(Integer id);

    @Update("update department set departmentName = #{departmentName} where id = #{id}")
    int updateRedpacketRecordById(Department department);

    @Options(useGeneratedKeys = true)
    @Insert("insert into department (departmentName) values (#{departmentName}) ")
    int insertDepartment(Department department);

}
