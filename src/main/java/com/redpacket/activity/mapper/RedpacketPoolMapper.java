package com.redpacket.activity.mapper;

import com.redpacket.activity.bean.RedpacketPool;
import org.springframework.stereotype.Repository;

@Repository
public interface RedpacketPoolMapper {

    RedpacketPool getRedpacketPoolById(Integer id);

    void insertRedpacketPool(RedpacketPool redpacketPool);

}
