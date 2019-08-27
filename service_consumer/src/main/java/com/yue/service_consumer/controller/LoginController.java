package com.yue.service_consumer.controller;

import com.yue.service_consumer.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public String toIndex(){
        return "htmlPage/pages/index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("form-username") String formUsername,@RequestParam("form-password") String formPassword,ModelMap modelMap, HttpServletRequest request){
        Map<String,Boolean> object = restTemplate.getForObject("http://login-provider/checkIfExist?user_name=" + formUsername, Map.class);
        Users users = new Users();
        users.setUser_name(formUsername);
        users.setUser_pw(formPassword);
        if(object.get("checkIfExist")){
            modelMap.put("msg","用户名已被占用");
            return "htmlPage/pages/index";
        }
//        测试 restTemplate.postForEntity
        Users loginUser = restTemplate.postForObject("http://login-provider/login", users, Users.class);
        if(loginUser != null){
            modelMap.put("user",loginUser);
            modelMap.put("loginUser",loginUser);
            List<Map<String,Object>> infos = new ArrayList<>();
            List<Map<String,Object>> artis = restTemplate.getForObject("http://arti-provider/getArticleByUserId?user_id=" + loginUser.getUser_id(), List.class);
            for(int i = 0;i<artis.size();i++){
                Map<String,Object> infoMap = new HashMap<>();
                Map<String,Object> count = restTemplate.getForObject("http://comment-provider/countComment?arti_id="+artis.get(i).get("arti_id"), Map.class);
                infoMap.put("arti",artis.get(i));
                infoMap.put("commCount",Integer.parseInt(count.get("").toString()));
                infos.add(infoMap);
            }
            modelMap.put("infos",infos);
            HttpSession session = request.getSession();
            session.setAttribute("login_user",loginUser);
            session.setAttribute("user",loginUser);
            return "htmlPage/pages/content/content";
        }else{
            modelMap.put("msg","用户名或密码错误");
            return  "htmlPage/pages/index";
        }
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
