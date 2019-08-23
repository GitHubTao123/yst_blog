package com.yue.commentprovider.controller;

import com.yue.commentprovider.entity.Comment;
import com.yue.commentprovider.entity.Users;
import com.yue.commentprovider.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/countComment")
    public int countComment(@PathParam("arti_id")int arti_id){
        return commentService.countComment(arti_id);
    }

    @RequestMapping("/addComment")
    public String addComment(@PathParam("user_id") int user_id,@PathParam("arti_id") int arti_id,@PathParam("comm_cont") String comm_cont){
        Date date = new Date();
        java.sql.Date d = new java.sql.Date(date.getTime());
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("arti_id",arti_id);
        map.put("comm_time",d);
        map.put("comm_cont",comm_cont);
        commentService.addComment(map);
        return "";
    }

    @RequestMapping("modComment")
    public String modComment(@PathParam("comm_id") int comm_id,@PathParam("comm_cont") String comm_cont){
        Date date = new Date();
        java.sql.Date d = new java.sql.Date(date.getTime());
        Comment comment = new Comment(comm_id,comm_cont,d ,0);
        commentService.modComment(comment);
        return "";
    }

    @RequestMapping("/cancelComment")
    public String cancelComment(@PathParam("comm_id") int comm_id){
        commentService.cancelComment(comm_id);
        return "";
    }

    @RequestMapping("/replyComment")
    public String replyComment(@PathParam("comm_id") int comm_id,@PathParam("user_id")int user_id,@PathParam("comm_cont")String comm_cont){
        Date date = new Date();
        java.sql.Date d = new java.sql.Date(date.getTime());
        Map<String,Object> map = new HashMap<>();
        map.put("comm_id",comm_id);
        map.put("user_id",user_id);
        map.put("comm_cont",comm_cont);
        map.put("comm_time",d);
        commentService.replyComment(map);
        return "";
    }

    @RequestMapping("/getCommentByArtiId")
    public List<Map<String,Object>> getCommentByArtiId(@PathParam("arti_id")int arti_id){
        List<Map<String,Object>> lists = commentService.getCommentByArtiId(arti_id);
        return lists;
    }

    @RequestMapping("/getMyComment")
    public List<Comment> getMyComment(HttpServletRequest request){
        Users loginUser = (Users) request.getSession().getAttribute("login_user");
        return commentService.getMyComment(loginUser.getUser_id());
    }

    @RequestMapping("/getHotComment")
    public List<Comment> getHotComment(){
        return commentService.getHotComment();
    }
}