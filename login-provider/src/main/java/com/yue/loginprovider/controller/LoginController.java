package com.yue.loginprovider.controller;

import com.yue.loginprovider.entity.Users;
import com.yue.loginprovider.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/checkIfExist")
    public Map<String,Object> checkIfExist(@PathParam("user_name") String user_name){
        Map<String,Object> map = new HashMap<>();
        boolean b = loginService.checkIfExist(user_name);
        map.put("checkIfExist",b);
        return map;
    }

    @RequestMapping("/login")
    public Users login(@RequestParam String user_name, @RequestParam String user_pw){
        Users user = loginService.login(user_name,user_pw);
        return user;
    }

    @RequestMapping("/regis_user")
    public Map<String,Object> regis_user(@PathParam("user_name") String user_name, @PathParam("user_pw") String user_pw,@PathParam("user_addr") String user_addr){
        Map<String,Object> map = new HashMap<>();
        Users user = new Users(0,user_name,user_pw,user_addr);
        loginService.regis_user(user);
        map.put("user",user);
        return map;
    }
}
