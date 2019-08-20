package com.yue.followprovider.entity;

import java.io.Serializable;
import java.sql.Date;

public class Follow implements Serializable {

    private int follow_id;
    private int follow_user_id;
    private int followed_user_id;
    private Date follow_time;

    public int getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(int follow_id) {
        this.follow_id = follow_id;
    }

    public int getFollow_user_id() {
        return follow_user_id;
    }

    public void setFollow_user_id(int follow_user_id) {
        this.follow_user_id = follow_user_id;
    }

    public int getFollowed_user_id() {
        return followed_user_id;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "follow_id=" + follow_id +
                ", follow_user_id=" + follow_user_id +
                ", followed_user_id=" + followed_user_id +
                ", follow_time=" + follow_time +
                '}';
    }

    public void setFollowed_user_id(int followed_user_id) {
        this.followed_user_id = followed_user_id;
    }

    public Date getFollow_time() {
        return follow_time;
    }

    public void setFollow_time(Date follow_time) {
        this.follow_time = follow_time;
    }
}
