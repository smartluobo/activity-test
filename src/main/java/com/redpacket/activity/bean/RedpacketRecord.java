package com.redpacket.activity.bean;

import java.util.Date;

public class RedpacketRecord {

    /**
     * 物理主键
     */
    private int id;
    /**
     * 用户唯一标识
     */
    private String tvid;
    /**
     * 记录类型1普通2升级3奖金池
     */
    private String type;
    /**
     * 场次id
     */
    private int roundId;
    /**
     * 红包id
     */
    private int poolId;
    /**
     * 红包金额 单位为分
     */
    private int rValue;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 城市
     */
    private String city;
    /**
     * 省份
     */
    private String province;
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

    public String getTvid() {
        return tvid;
    }

    public void setTvid(String tvid) {
        this.tvid = tvid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getrValue() {
        return rValue;
    }

    public void setrValue(int rValue) {
        this.rValue = rValue;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    @Override
    public String toString() {
        return "RedpacketRecord{" +
                "id=" + id +
                ", tvid='" + tvid + '\'' +
                ", type='" + type + '\'' +
                ", roundId=" + roundId +
                ", poolId=" + poolId +
                ", rValue=" + rValue +
                ", ip='" + ip + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", cTime=" + cTime +
                '}';
    }
}
