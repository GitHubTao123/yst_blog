package com.yue.commentprovider.service;

import com.yue.commentprovider.entity.Comment;
import com.yue.commentprovider.entity.Users;

import java.util.List;
import java.util.Map;

public interface CommentService {
    void addComment(Map<String,Object> map);

    void modComment(Comment comment);

    void cancelComment(int comm_id);

    void replyComment(Map<String, Object> map);

    List<Map<String, Object>> getCommentByArtiId(int artiId);

    List<Comment> getMyComment(int login_user_id);

    List<Comment> getHotComment();

    int countComment(int arti_id);

    List<Map<String,Object>> getReplyComment(int commId);
}
