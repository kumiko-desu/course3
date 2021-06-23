package com.course3.beans;

public class Favor {
    private Integer uid;
    private Integer aid;

    public Favor() {
    }

    public Favor(Integer uid, Integer aid) {
        this.uid = uid;
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Favor{" +
                "uid=" + uid +
                ", aid=" + aid +
                '}';
    }
}
