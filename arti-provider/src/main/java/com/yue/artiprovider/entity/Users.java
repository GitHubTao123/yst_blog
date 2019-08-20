package com.yue.artiprovider.entity;

import java.io.Serializable;

public class Users implements Serializable {

    private int user_id;
    private String user_name;
    private String user_pw;
    private String user_addr;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_addr() {
        return user_addr;
    }

    public void setUser_addr(String user_addr) {
        this.user_addr = user_addr;
    }

    public Users(int user_id, String user_name, String user_pw, String user_addr) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pw = user_pw;
        this.user_addr = user_addr;
    }

    public Users(){
        super();
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_pw='" + user_pw + '\'' +
                ", user_addr='" + user_addr + '\'' +
                '}';
    }
}
