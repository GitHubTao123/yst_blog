package com.yue.commentprovider.mapper;

import com.yue.commentprovider.entity.Comment;
import com.yue.commentprovider.entity.Users;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    void addIntoComment(Comment comment);

    void addIntoComm_User(Map<String,Object> map);

    void modComment(Comment comment);

    void cancelComment(int comm_id);

    void addIntoComm_Arti(Map<String, Object> map);

    void cancelComm_User(int comm_id);

    void cancelComm_Arti(int comm_id);

    void addIntoComm_Comm(Map<String, Object> map);

    List<Comment> getCommentByArtiId(int artiId);

    Users getCommentUserInfo(int comm_id);

    List<Comment> getMyComment(int login_user_id);

    List<Comment> getHotComment();

    int countComment(int arti_id);

    List<Comment> getReplyComment(int commId);
}
