package com.redpacket.activity.bean;

import java.util.Date;


public class CouponPool {
    /**
     * 主键id
     */
    private int id;
    /**
     * 场次id
     */
    private int roundId;
    /**
     * 口令码
     */
    private String code;
    /**
     * 优惠金额
     */
    private double discountAmount;
    /**
     * 生成时间
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }


    @Override
    public String toString() {
        return "CouponPool{" +
                "id=" + id +
                ", roundId=" + roundId +
                ", code='" + code + '\'' +
                ", discountAmount=" + discountAmount +
                ", cTime=" + cTime +
                '}';
    }
}
