package com.course3.beans;

import java.util.Date;

public class Reply {
    private Integer rid;
    private String uname;
    private Integer aid;
    private String details;
    private Date time;

    public Reply() {
    }

    public Reply(String uname, Integer aid, String details, Date time) {
        this.uname = uname;
        this.aid = aid;
        this.details = details;
        this.time = time;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "rid=" + rid +
                ", uname='" + uname + '\'' +
                ", aid=" + aid +
                ", details='" + details + '\'' +
                ", time=" + time +
                '}';
    }
}
