package com.yue.service_consumer.controller;

import com.yue.service_consumer.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FollowController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getMyFollow")
    public String getMyFollow(ModelMap modelMap,HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        Users loginUser = (Users) request.getSession().getAttribute("login_user");
        List<Map<String,Object>> fols = restTemplate.getForObject("http://follow-provider/getMyFollow?user_id=" + user.getUser_id(),List.class);

        List<Map<String,Object>> infos = new ArrayList<>();

        for(int i = 0;i<fols.size();i++){
            Map<String,Object> info = new HashMap<>();
            user = restTemplate.getForObject("http://user-provider/getUserInfo?user_id=" + fols.get(i).get("followed_user_id"), Users.class);
            List<Map<String,Object>> artis = restTemplate.getForObject("http://arti-provider/getHotArtiInSingleUser?user_id="+user.getUser_id(),List.class);
            List<Map<String,Object>> artiAndCounts = new ArrayList<>();
            for(int j = 0;j<artis.size();j++){
                Map<String,Object> artiAndCountMap = new HashMap<>();
                Map<String,Object> count = restTemplate.getForObject("http://comment-provider/countComment?artiId="+artis.get(j).get("arti_id"), Map.class);
                artiAndCountMap.put("count",Integer.parseInt(count.get("").toString()));
                artiAndCountMap.put("arti",artis.get(j));
                artiAndCounts.add(artiAndCountMap);
            }
            info.put("user",user);
            info.put("artis",artiAndCounts);
            infos.add(info);
        }
        modelMap.put("infos",infos);
        modelMap.put("user",user);
        modelMap.put("loginUser",loginUser);
        if(user.getUser_id() != loginUser.getUser_id()){
            modelMap.put("headTitle","Ta的关注");
        }else{
            modelMap.put("headTitle","我的关注");
        }
        return "htmlPage/pages/follow/users";
    }

    @RequestMapping("/getMyFollowed")
    public String getMyFollowed(ModelMap modelMap, HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        Users loginUser = (Users) request.getSession().getAttribute("login_user");
        List<Map<String,Object>> fols = restTemplate.getForObject("http://follow-provider/getMyFollowed?user_id=" + user.getUser_id(),List.class);

        List<Map<String,Object>> infos = new ArrayList<>();

        for(int i = 0;i<fols.size();i++){
            Map<String,Object> info = new HashMap<>();
            user = restTemplate.getForObject("http://user-provider/getUserInfo?user_id=" + fols.get(i).get("follow_user_id"), Users.class);
            List<Map<String,Object>> artis = restTemplate.getForObject("http://arti-provider/getHotArtiInSingleUser?user_id="+user.getUser_id(),List.class);
            List<Map<String,Object>> artiAndCounts = new ArrayList<>();
            for(int j = 0;j<artis.size();j++){
                Map<String,Object> artiAndCountMap = new HashMap<>();
                Map<String,Object> count = restTemplate.getForObject("http://comment-provider/countComment?artiId="+artis.get(j).get("arti_id"), Map.class);
                artiAndCountMap.put("count",Integer.parseInt(count.get("").toString()));
                artiAndCountMap.put("arti",artis.get(j));
                artiAndCounts.add(artiAndCountMap);
            }
            info.put("user",user);
            info.put("artis",artiAndCounts);
            infos.add(info);
        }
        modelMap.put("infos",infos);
        modelMap.put("user",user);
        modelMap.put("loginUser",loginUser);
        if(user.getUser_id() != loginUser.getUser_id()){
            modelMap.put("headTitle","Ta的粉丝");
        }else{
            modelMap.put("headTitle","我的粉丝");
        }
        return "htmlPage/pages/follow/users";
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
