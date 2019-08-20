package com.yue.user_provider.controller;

import com.yue.user_provider.service.UserService;
import com.yue.user_provider.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/getUserInfo")
    public Users getUserInfoById(@RequestParam int user_id){
        Map<String,Object> map = new HashMap<>();
        Users user = userService.getUserInfoById(user_id);
        return user;
    }
}
