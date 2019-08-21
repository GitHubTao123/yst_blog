package com.yue.user_provider.controller;

import com.yue.user_provider.service.UserService;
import com.yue.user_provider.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    public Users getUserInfoById(@RequestParam int user_id){
        Map<String,Object> map = new HashMap<>();
        Users user = userService.getUserInfoById(user_id);
        return user;
    }

    @RequestMapping("/getSearchArea_user")
    public List<Users> getSearchArea_user(@PathParam("user_name") String user_name){
        List<Users> users = userService.getSearchArea_user(user_name);
        return users;
    }
}
