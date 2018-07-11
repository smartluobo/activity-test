package com.redpacket.activity.mapper;

import com.redpacket.activity.bean.RedpacketRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface RedPacketRecordMapper {
    void insertRedPacketRecord(RedpacketRecord redPacketRecord);
}
