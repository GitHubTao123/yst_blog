package com.yue.service_consumer.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

    private int comm_id;
    private int user_id;
    private int arti_id;
    private String comm_cont;
    private Date comm_time;
    private int watched_times;

    public int getComm_id() {
        return comm_id;
    }

    public void setComm_id(int comm_id) {
        this.comm_id = comm_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getArti_id() {
        return arti_id;
    }

    public void setArti_id(int arti_id) {
        this.arti_id = arti_id;
    }

    public String getComm_cont() {
        return comm_cont;
    }

    public void setComm_cont(String comm_cont) {
        this.comm_cont = comm_cont;
    }

    public Date getComm_time() {
        return comm_time;
    }

    public void setComm_time(Date comm_time) {
        this.comm_time = comm_time;
    }

    public int getWatched_time() {
        return watched_times;
    }

    public void setWatched_time(int watched_time) {
        this.watched_times = watched_time;
    }

    public Comment(int comm_id, int user_id, int arti_id, String comm_cont, Date comm_time, int watched_time) {
        this.comm_id = comm_id;
        this.user_id = user_id;
        this.arti_id = arti_id;
        this.comm_cont = comm_cont;
        this.comm_time = comm_time;
        this.watched_times = watched_time;
    }

    public Comment(){
        super();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comm_id=" + comm_id +
                ", user_id=" + user_id +
                ", arti_id=" + arti_id +
                ", comm_cont='" + comm_cont + '\'' +
                ", comm_time=" + comm_time +
                ", watched_time=" + watched_times +
                '}';
    }
}
