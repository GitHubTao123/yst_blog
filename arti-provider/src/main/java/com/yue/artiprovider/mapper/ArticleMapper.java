package com.yue.artiprovider.mapper;

import com.yue.artiprovider.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    List<Article> getArticleByUserId(@Param("user_id") int user_id);

    void submitArti(int user_id, String arti_title, String m_textarea);

    void modiArti(int arti_id, String arti_title, String arti_cont);

    Article getArticleByArtiId(int arti_id);

    void update_watch_times(int watched_times, int arti_id);

    List<Article> getHotArti();

    void delArti(int arti_id);

    void cancelFollowArti(int login_user_id, int arti_id);

    void addFollowArti(int login_user_id, int arti_id);

    int checkIfCollect(int login_user_id, int arti_id);

    List<Article> getMyCollect(int user_id);

    List<Article> getArtiByLike(String arti_title);

    List<Article> getHotArtiInSingleUser(int user_id);
}
