package com.redpacket.activity.bean;

import java.util.Date;

public class SpringBootTestBean {

    private int id;

    private String tvid;

    private int redpackedId;

    private int type;

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

    public int getRedpackedId() {
        return redpackedId;
    }

    public void setRedpackedId(int redpackedId) {
        this.redpackedId = redpackedId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
        return "SpringBootTestBean{" +
                "id=" + id +
                ", tvid='" + tvid + '\'' +
                ", redpackedId=" + redpackedId +
                ", type=" + type +
                ", cTime=" + cTime +
                '}';
    }
}
