package com.yue.artiprovider.entity;

import java.io.Serializable;

public class Followed_arti implements Serializable {

    private int f_arti_id;
    private int arti_id;
    private int follow_user_id;

    public int getF_arti_id() {
        return f_arti_id;
    }

    public void setF_arti_id(int f_arti_id) {
        this.f_arti_id = f_arti_id;
    }

    public int getArti_id() {
        return arti_id;
    }

    public void setArti_id(int arti_id) {
        this.arti_id = arti_id;
    }

    public int getFollow_user_id() {
        return follow_user_id;
    }

    public void setFollow_user_id(int follow_user_id) {
        this.follow_user_id = follow_user_id;
    }

    @Override
    public String toString() {
        return "Followed_arti{" +
                "f_arti_id=" + f_arti_id +
                ", arti_id=" + arti_id +
                ", follow_user_id=" + follow_user_id +
                '}';
    }
}
