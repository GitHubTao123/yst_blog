package com.yue.service_consumer.controller;

import com.yue.service_consumer.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FollowController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getMyFollow")
    public String getMyFollow(ModelMap map, HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        Users login_user = (Users) request.getSession().getAttribute("login_user");
        ResponseEntity<List> folsEntity = restTemplate.getForEntity("http://follow-provider/getMyFollow?user_id=" + user.getUser_id(),List.class);
        List<Map<String,Object>> fols = folsEntity.getBody();
        map.put("fols",fols);
        List<Users> users = new ArrayList<>();
        for(int i = 0;i<fols.size();i++){
            ResponseEntity<Users> userEntity = restTemplate.getForEntity("http://user-provider/getUserInfo?user_id=" + fols.get(i).get("followed_user_id"), Users.class);
            Users users1 = userEntity.getBody();
            users.add(users1);
        }
        map.put("users",users);
        if(user.getUser_id() != login_user.getUser_id()){
            map.put("headTitle","Ta的关注");
        }else{
            map.put("headTitle","我的关注");
        }
        System.out.println(map);
        return "follow/myFollow";
    }

    @RequestMapping("/getMyFollowed")
    public String getMyFollowed(ModelMap map, HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        Users login_user = (Users) request.getSession().getAttribute("login_user");
        ResponseEntity<List> foledsEntity = restTemplate.getForEntity("http://follow-provider/getMyFollowed?user_id=" + user.getUser_id(),List.class);
        List<Map<String,Object>> foleds = foledsEntity.getBody();
        map.put("fols",foleds);
        List<Users> users = new ArrayList<>();
        for(int i = 0;i<foleds.size();i++){
            ResponseEntity<Users> userEntity = restTemplate.getForEntity("http://user-provider/getUserInfo?user_id=" + foleds.get(i).get("follow_user_id"), Users.class);
            Users users1 = userEntity.getBody();
            users.add(users1);
        }
        map.put("users",users);
        if(user.getUser_id() != login_user.getUser_id()){
            map.put("headTitle","Ta的粉丝");
        }else{
            map.put("headTitle","我的粉丝");
        }
        return "follow/myFollow";
    }

    @ResponseBody
    @RequestMapping("/checkIfFollow")
    public boolean checkIfFollow(HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        Users login_user = (Users) request.getSession().getAttribute("login_user");
        Map map = restTemplate.getForObject("http://follow-provider/checkIfFollow?user_id=" + user.getUser_id() + "&login_user_id=" + login_user.getUser_id(), Map.class);
        int count = Integer.parseInt(map.get("count").toString());
        boolean ifFol = count == 0 ? false : true;
        return ifFol;
    }

    @ResponseBody
    @RequestMapping("/toggleFollowUser")
    public Map<String,Object> toggleFollowUser(@RequestParam boolean ifFol, HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        Users login_user = (Users) request.getSession().getAttribute("login_user");
        ModelMap map = new ModelMap();
        if(ifFol){
            restTemplate.delete("http://follow-provider/cancel_followed_user?user_id="+user.getUser_id()+"&login_user_id="+login_user.getUser_id());
            map.put("msg","取消关注成功!");
            map.put("ifFol",false);
        }else{
            restTemplate.put("http://follow-provider/add_followed_user?user_id="+user.getUser_id()+"&login_user_id="+login_user.getUser_id(),login_user.getUser_id(),user.getUser_id());
            map.put("msg","关注成功!");
            map.put("ifFol",true);
        }
        System.out.println(map);
        return map;
    }
}
