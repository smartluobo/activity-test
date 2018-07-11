package com.redpacket.activity.mapper;

import com.redpacket.activity.bean.CouponRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRecordMapper {
    void insertCouponRecord(CouponRecord couponRecord);
}
