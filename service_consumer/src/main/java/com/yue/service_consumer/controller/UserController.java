package com.yue.service_consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping("/getUserInfo")
    public Map<String,Object> getUserInfoById(@RequestParam int user_id){
        Map<String,Object> map = restTemplate.getForObject("http://user-provider/getUserInfo?user_id="+user_id,Map.class);
        return map;
    }
}
