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
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping("/checkIfExist")
    public Map<String,Object> checkIfExist(@PathParam("user_name") String user_name){
        Map<String,Object> map = restTemplate.getForObject("http://login-provider/checkIfExist?user_name="+user_name,Map.class);
        return map;
    }

    @RequestMapping("/login")
    public String login(@RequestParam String user_name,@RequestParam String user_pw,ModelMap modelMap, HttpSession session){
        String page;
        Users user;
//        user = restTemplate.getForObject("http://login-provider/login?user_name="+user_name+"&user_pw=" + user_pw,Users.class);
        ResponseEntity<Users> userEntity = restTemplate.getForEntity("http://login-provider/login?user_name=" + user_name + "&user_pw=" + user_pw, Users.class);
        user = userEntity.getBody();
        if(user != null){
            int user_id = user.getUser_id();
            modelMap.put("user",user);
            modelMap.put("login_user",user);
            ResponseEntity<List> forEntity = restTemplate.getForEntity("http://arti-provider/getArticleByUserId?user_id=" + user_id, List.class);
            modelMap.put("artis",forEntity.getBody());
            session.setAttribute("login_user",user);
            session.setAttribute("user",user);
            page = "content/content";
        }else{
            modelMap.put("msg","用户名或密码错误");
            page = "index";
        }
        return page;
    }

    @RequestMapping("/toContPage")
    public String toContPage(@RequestParam int user_id,ModelMap modelMap, HttpServletRequest request){
        Users login_user = (Users)request.getSession().getAttribute("login_user");
        Users user = restTemplate.getForObject("http://user-provider/getUserInfo?user_id="+user_id,Users.class);
        request.getSession().setAttribute("user",user);
        List artis = restTemplate.getForObject("http://arti-provider/getArticleByUserId?user_id="+user_id,List.class);
        modelMap.put("artis",artis);
        modelMap.put("user",user);
        modelMap.put("login_user",login_user);
        System.out.println(modelMap);
        return "content/content";
    }

    @RequestMapping("/toRegisPage")
    public String toRegisPage(@PathParam("user_name")String user_name,ModelMap map){
        map.put("user_name",user_name);
        return "login/register";
    }

    @RequestMapping("/regis_user")
    public String regis_user(@PathParam("user_name") String user_name, @PathParam("user_pw") String user_pw,@PathParam("user_addr") String user_addr){
        Users users = new Users(0,user_name,user_pw,user_addr);
        Map<String,Object> map = restTemplate.postForObject("http://login-provider/regis_user",users,Map.class);
        return "login/success";
    }
}
