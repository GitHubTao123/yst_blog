package com.yue.commentprovider.controller;

import com.yue.commentprovider.entity.Comment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

//    @PathVariable int user_id,@PathVariable int arti_id,@PathVariable String comm_cont
    @RequestMapping("/addComment")
    public void addComment(@PathVariable Comment comment){
        System.out.println(comment);
        System.out.println("--------------------------------------> yess");
    }
}
