package com.yue.artiprovider.service;

import com.yue.artiprovider.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    List<Article> getArticleByUserId(int userId);

    void submitArti(Article article);

    void modiArti(int arti_id, String arti_title, String arti_cont);

    Article getArticleByArtiId(int artiId);

    void delArti(int arti_id);

    void cancelFollowArti(int login_user_id, int arti_id);

    void addFollowArti(int login_user_id, int arti_id);

    List<Article> getMyCollect(int user_id);

    int checkIfCollect(int login_user_id, int arti_id);

    List<Article> getHotArti();

    List<Article> getSearchArea_arti(String arti_title);

    List<Article> getHotArtiInSingleUser(int user_id);
}
