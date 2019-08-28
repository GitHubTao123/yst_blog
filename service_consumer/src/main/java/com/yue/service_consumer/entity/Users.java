package com.yue.service_consumer.entity;

import lombok.*;

import java.io.Serializable;

public class Users implements Serializable {

    private int user_id;
    private String user_name;
    private String user_pw;
    private String user_addr;
    private String user_img;
    private String user_nickname;

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

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public Users(int user_id, String user_name, String user_pw, String user_addr, String user_img, String user_nickname) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pw = user_pw;
        this.user_addr = user_addr;
        this.user_img = user_img;
        this.user_nickname = user_nickname;
    }

    public Users(){}
}
