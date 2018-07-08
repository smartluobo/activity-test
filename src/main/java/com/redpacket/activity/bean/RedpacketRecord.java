package com.redpacket.activity.bean;

import java.util.Date;

public class RedpacketRecord {

    private Integer id;
    private String tvid;
    private Integer redpacketId;
    private Integer type;
    private Date cTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTvid() {
        return tvid;
    }

    public void setTvid(String tvid) {
        this.tvid = tvid;
    }

    public Integer getRedpacketId() {
        return redpacketId;
    }

    public void setRedpacketId(Integer redpacketId) {
        this.redpacketId = redpacketId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
                ", redpacketId=" + redpacketId +
                ", type=" + type +
                ", cTime=" + cTime +
                '}';
    }
}
