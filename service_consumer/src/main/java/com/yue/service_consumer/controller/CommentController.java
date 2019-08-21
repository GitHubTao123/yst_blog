package com.yue.service_consumer.controller;

import com.yue.service_consumer.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.sql.Date;

@Controller
public class CommentController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/addComment")
    public String addComment(@PathParam("user_id") int user_id, @PathParam("arti_id") int arti_id, @PathParam("comm_cont") String comm_cont){
        java.util.Date date = new java.util.Date();
        Date sql_date = new Date(date.getTime());
        Comment comment = new Comment(0,user_id,arti_id,comm_cont,sql_date,0);
        restTemplate.put("http://comment-provider/addComment",comment);
        return "comm/success";
    }
}
