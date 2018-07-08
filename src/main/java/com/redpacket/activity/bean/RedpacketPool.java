package com.redpacket.activity.bean;

import java.util.Date;

public class RedpacketPool {
    private Integer id;
    private Integer status;
    private Integer amount;
    private Integer roundId;
    private Date cTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    @Override
    public String toString() {
        return "RedpacketPool{" +
                "id=" + id +
                ", status=" + status +
                ", amount=" + amount +
                ", roundId=" + roundId +
                ", cTime=" + cTime +
                '}';
    }
}
