package com.yue.artiprovider.entity;

import java.io.Serializable;

public class Article implements Serializable {

    private int arti_id;
    private int user_id;
    private String arti_title;
    private String arti_cont;
    private int watched_times;
    private long create_time;


    @Override
    public String toString() {
        return "Article{" +
                "arti_id=" + arti_id +
                ", user_id=" + user_id +
                ", arti_title='" + arti_title + '\'' +
                ", arti_cont='" + arti_cont + '\'' +
                ", watched_times=" + watched_times +
                ", create_time=" + create_time +
                '}';
    }

    public Article() {
    }

    public Article(int arti_id, int user_id, String arti_title, String arti_cont, int watched_times, long create_time) {
        this.arti_id = arti_id;
        this.user_id = user_id;
        this.arti_title = arti_title;
        this.arti_cont = arti_cont;
        this.watched_times = watched_times;
        this.create_time = create_time;
    }

    public int getArti_id() {
        return arti_id;
    }

    public void setArti_id(int arti_id) {
        this.arti_id = arti_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getArti_title() {
        return arti_title;
    }

    public void setArti_title(String arti_title) {
        this.arti_title = arti_title;
    }

    public String getArti_cont() {
        return arti_cont;
    }

    public void setArti_cont(String arti_cont) {
        this.arti_cont = arti_cont;
    }

    public int getWatched_times() {
        return watched_times;
    }

    public void setWatched_times(int watched_times) {
        this.watched_times = watched_times;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }
}
