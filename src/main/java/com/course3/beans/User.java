package com.course3.beans;

public class User {
    private Integer uid;
    private String name;
    private String salt;
    private String hashPwd;
    private String token;
    private boolean isAdmin;

    public User() {
    }

    public User(String name, String salt, String hashPwd) {
        this.uid = uid;
        this.name = name;
        this.salt = salt;
        this.hashPwd = hashPwd;
    }

    public User(String name, String salt, String hashPwd, boolean isAdmin) {
        this.uid = uid;
        this.name = name;
        this.salt = salt;
        this.hashPwd = hashPwd;
        this.isAdmin = isAdmin;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHashPwd() {
        return hashPwd;
    }

    public void setHashPwd(String hashPwd) {
        this.hashPwd = hashPwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", salt='" + salt + '\'' +
                ", hashPwd='" + hashPwd + '\'' +
                ", token='" + token + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
