package com.redpacket.activity;

public class TestData {

    private int id;

    private String msg;

    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
