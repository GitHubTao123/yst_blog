package com.yue.commentprovider.service;

import com.yue.commentprovider.entity.Comment;
import com.yue.commentprovider.entity.Users;
import com.yue.commentprovider.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int countComment(int arti_id) {
        return commentMapper.countComment(arti_id);
    }

    @Override
    public void addComment(Map<String,Object> map) {
        Comment comment = new Comment();
        comment.setComm_cont((String)map.get("comm_cont"));
        comment.setComm_time((Date)map.get("comm_time"));
        commentMapper.addIntoComment(comment);
        map.put("comm_id",comment.getComm_id());
        commentMapper.addIntoComm_User(map);
        commentMapper.addIntoComm_Arti(map);
    }

    @Override
    public void modComment(Comment comment) {
        commentMapper.modComment(comment);
    }

    @Override
    public void cancelComment(int comm_id) {
        commentMapper.cancelComment(comm_id);
        commentMapper.cancelComm_User(comm_id);
        commentMapper.cancelComm_Arti(comm_id);
    }

    @Override
    public void replyComment(Map<String, Object> map) {
        Comment comment = new Comment();
        comment.setComm_cont((String)map.get("comm_cont"));
        comment.setComm_time((Date)map.get("comm_time"));
        commentMapper.addIntoComment(comment);
        map.put("second_comm_id",comment.getComm_id());
        commentMapper.addIntoComm_User(map);
        commentMapper.addIntoComm_Comm(map);
    }

    @Override
    public List<Map<String, Object>> getCommentByArtiId(int arti_id) {
        List<Map<String, Object>> lists = new ArrayList<>();
        List<Comment> comms = commentMapper.getCommentByArtiId(arti_id);
        for(int i = 0;i<comms.size();i++){
            Map<String,Object> map = new HashMap<>();
            int comm_id = comms.get(i).getComm_id();
            Users user = commentMapper.getCommentUserInfo(comm_id);
            map.put("comm",comms.get(i));
            map.put("user",user);
            lists.add(map);
        }
        return lists;
    }

    @Override
    public List<Comment> getMyComment(int login_user_id) {
        return commentMapper.getMyComment(login_user_id);
    }

    @Override
    public List<Comment> getHotComment() {
        return commentMapper.getHotComment();
    }

}
