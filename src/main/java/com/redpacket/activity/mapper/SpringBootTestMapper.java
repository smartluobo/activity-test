package com.redpacket.activity.mapper;

import com.redpacket.activity.bean.SpringBootTestBean;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringBootTestMapper {

    void insertSpringBootTestInfo(SpringBootTestBean springBootTestBean);
}
