package com.redpacket.activity.bean;

import java.util.Date;

public class CouponRecord {
    /**
     * 物理主键
     */
    private int id;
    /**
     * 场次id
     */
    private int roundId;
    /**
     * 用户唯一标识
     */
    private String tvid;
    /**
     * 优惠券id
     */
    private int poolId;
    /**
     * 优惠券金额
     */
    private double discountAmount;
    /**
     * 口令码
     */
    private String code;
    /**
     * 创建时间
     */
    private Date cTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getPoolId() {
        return poolId;
    }

    public void setPoolId(int poolId) {
        this.poolId = poolId;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public String getTvid() {
        return tvid;
    }

    public void setTvid(String tvid) {
        this.tvid = tvid;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CouponRecord{" +
                "id=" + id +
                ", roundId=" + roundId +
                ", tvid='" + tvid + '\'' +
                ", poolId=" + poolId +
                ", discountAmount=" + discountAmount +
                ", code='" + code + '\'' +
                ", cTime=" + cTime +
                '}';
    }
}
