package com.yue.artiprovider.service;

import com.yue.artiprovider.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    List<Article> getArticleByUserId(int id);

    void submitArti(int user_id, String arti_title, String m_textarea);

    void modiArti(int arti_id, String arti_title, String arti_cont);

    Article getContent(int arti_id);

    void delArti(int arti_id);

    void cancelFollowArti(int login_user_id, int arti_id);

    void addFollowArti(int login_user_id, int arti_id);

    List<Article> getMyCollect(int user_id);

    int checkIfCollect(int login_user_id, int arti_id);

    List<Article> getHotArti();
}
