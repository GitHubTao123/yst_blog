package com.yue.followprovider.controller;

import com.yue.followprovider.entity.Follow;
import com.yue.followprovider.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;

    @RequestMapping("/getMyFollow")
    public List<Follow> getMyFollow(@PathParam("user_id")int user_id){
        return followService.getMyFollow(user_id);
    }

    @RequestMapping("/getMyFollowed")
    public List<Follow> getMyFollowed(@PathParam("user_id")int user_id){
        return followService.getMyFollowed(user_id);
    }

    @RequestMapping("/checkIfFollow")
    public Map<String,Integer> checkIfFollow(@PathParam("user_id")int user_id, @PathParam("login_user_id")int login_user_id){
        Map<String,Integer> map = new HashMap<>();
        map.put("count",followService.checkIfFollow(user_id,login_user_id));
        return map;
    }

    @RequestMapping("/add_followed_user")
    public void add_followed_user(@RequestParam int login_user_id, @RequestParam int user_id){
        followService.add_followed_user(login_user_id,user_id);
    }

    @RequestMapping("/cancel_followed_user")
    public void cancel_followed_user(@RequestParam int login_user_id, @RequestParam int user_id){
        followService.cancel_followed_user(login_user_id,user_id);
    }
}
