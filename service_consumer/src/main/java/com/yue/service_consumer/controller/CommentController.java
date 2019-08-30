package com.yue.service_consumer.controller;

import com.yue.service_consumer.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/countComment")
    public int countComment(@PathParam("artiId") int artiId){
        return restTemplate.getForObject("http://comment-provider/countComment?artiId="+artiId,int.class);
    }

    @ResponseBody
    @RequestMapping("/addComment")
    public String addComment(@PathParam("user_id") int user_id,@PathParam("arti_id") int arti_id,@PathParam("comm_cont") String comm_cont) {
        restTemplate.getForObject("http://comment-provider/addComment?user_id="+user_id+"&arti_id="+arti_id+"&comm_cont="+comm_cont, String.class);
        return "success";
    }

    @RequestMapping("/modComment")
    public String modComment(@PathParam("comm_id") int comm_id,@PathParam("comm_cont") String comm_cont){
        restTemplate.getForObject("http://comment-provider/modComment?comm_id="+comm_id+"&comm_cont="+comm_cont,String.class);
        return "comm/success";
    }

    @RequestMapping("/cancelComment")
    public String cancelComment(@PathParam("comm_id")int comm_id){
        restTemplate.getForObject("http://comment-provider/cancelComment?comm_id=" + comm_id,String.class);
        return "comm/success";
    }

    @RequestMapping("/replyComment")
    public String replyComment(@PathParam("comm_id") int comm_id,@PathParam("user_id")int user_id,@PathParam("comm_cont")String comm_cont){
        restTemplate.getForObject("http://comment-provider/replyComment?comm_id="+comm_id+"&user_id="+user_id+"&comm_cont="+comm_cont,String.class);
        return "comm/success";
    }

    @ResponseBody
    @RequestMapping("getCommentByArtiId")
    public List<Map<String,Object>> getCommentByArtiId(@RequestParam("artiId") int artiId){
        List<Map<String,Object>> lists = restTemplate.getForObject("http://comment-provider/getCommentByArtiId?arti_id=" + artiId, List.class);
        return lists;
    }

    @ResponseBody
    @RequestMapping("/getMyComment")
    public List<Comment> getMyComment(){
        List<Comment> comms = restTemplate.getForObject("http://comment-provider/getMyComment",List.class);
        return comms;
    }

    @ResponseBody
    @RequestMapping("/getHotComment")
    public List<Comment> getHotComment(){
        List<Comment> hot_comm = restTemplate.getForObject("http://comment-provider/getHotComment",List.class);
        return hot_comm;
    }

    @ResponseBody
    @GetMapping("/getReplyComment")
    public List<Map<String,Object>> getReplyComment(@RequestParam int commId){
        List<Map<String,Object>> lists = restTemplate.getForObject("http://comm-provider/getReplyComment?commId=" + commId, List.class);
        return lists;
    }
}