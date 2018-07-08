package com.redpacket.activity.controller;

import com.redpacket.activity.bean.RedpacketPool;
import com.redpacket.activity.mapper.RedpacketPoolMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redpool")
public class RedpacketPoolController {

    @Resource
    RedpacketPoolMapper redpacketPoolMapper;

    @GetMapping("/get/{id}")
    public RedpacketPool getRedpacketPoolById(@PathVariable("id") Integer id){
        RedpacketPool redpacketPool = redpacketPoolMapper.getRedpacketPoolById(id);
        return redpacketPool;
    }

    @GetMapping("/add")
    public RedpacketPool insertRedpacketPool(RedpacketPool redpacketPool){
        redpacketPoolMapper.insertRedpacketPool(redpacketPool);
        return redpacketPool;
    }
}
