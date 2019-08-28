package com.yue.artiprovider.service;

import com.yue.artiprovider.entity.Article;
import com.yue.artiprovider.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getArticleByUserId(int user_id) {
        List<Article> list_arti = articleMapper.getArticleByUserId(user_id);
        return list_arti;
    }

    @Override
    public void submitArti(int user_id, String arti_title, String m_textarea) {
        articleMapper.submitArti(user_id,arti_title,m_textarea);
    }

    @Override
    public void modiArti(int arti_id, String arti_title, String arti_cont) {
        articleMapper.modiArti(arti_id,arti_title,arti_cont);
    }

    @Override
    public Article getArticleByArtiId(int arti_id) {
        Article arti = articleMapper.getArticleByArtiId(arti_id);
        int watched_times = arti.getWatched_times();
        articleMapper.update_watch_times(watched_times + 1,arti_id);
        return arti;
    }

    @Override
    public void delArti(int arti_id) {
        articleMapper.delArti(arti_id);
    }

    @Override
    public void cancelFollowArti(int login_user_id, int arti_id) {
        articleMapper.cancelFollowArti(login_user_id,arti_id);
    }

    @Override
    public void addFollowArti(int login_user_id, int arti_id) {
        articleMapper.addFollowArti(login_user_id,arti_id);
    }

    @Override
    public List<Article> getMyCollect(int user_id) {
        List<Article> collections = articleMapper.getMyCollect(user_id);
        return collections;
    }

    @Override
    public int checkIfCollect(int login_user_id, int arti_id) {
        int count = articleMapper.checkIfCollect(login_user_id,arti_id);
        return count;
    }

    @Override
    public List<Article> getHotArti() {
        List<Article> artis = articleMapper.getHotArti();
        return artis;
    }

    @Override
    public List<Article> getSearchArea_arti(String arti_title) {
        List<Article> list = articleMapper.getArtiByLike(arti_title);
        return list;
    }

    @Override
    public List<Article> getHotArtiInSingleUser(int user_id) {
        return articleMapper.getHotArtiInSingleUser(user_id);
    }
}
