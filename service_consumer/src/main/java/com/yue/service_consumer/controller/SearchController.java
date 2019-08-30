package com.yue.service_consumer.controller;

import com.yue.service_consumer.entity.Article;
import com.yue.service_consumer.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/getSearchArea")
    public String getSearchArea(@PathParam("type") String type, @PathParam("sea_val") String sea_val,ModelMap modelMap, HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        Users loginUser = (Users) request.getSession().getAttribute("login_user");

        if(type.equals("user")){
            List<Map<String,Object>> infos = new ArrayList<>();
            List<Map<String,Object>> users  = restTemplate.getForObject("http://user-provider/getSearchArea_user?user_name=" + sea_val, List.class);
            for(int i = 0;i<users.size();i++){
                Map<String,Object> info = new HashMap<>();
                List<Map<String,Object>> artis = restTemplate.getForObject("http://arti-provider/getHotArtiInSingleUser?user_id="+users.get(i).get("user_id"),List.class);
                List<Map<String,Object>> artiAndCounts = new ArrayList<>();
                for(int j = 0;j<artis.size();j++){
                    Map<String,Object> artiAndCountMap = new HashMap<>();
                    Map<String,Object> count = restTemplate.getForObject("http://comment-provider/countComment?artiId="+artis.get(j).get("arti_id"), Map.class);
                    artiAndCountMap.put("count",Integer.parseInt(count.get("").toString()));
                    artiAndCountMap.put("arti",artis.get(j));
                    artiAndCounts.add(artiAndCountMap);
                }
                info.put("user",users.get(i));
                info.put("artis",artiAndCounts);
                infos.add(info);
            }
            modelMap.put("infos",infos);
            modelMap.put("headTitle","搜索用户列表");
            modelMap.put("user",user);
            modelMap.put("loginUser",loginUser);
            return "htmlPage/pages/follow/users";
        }else{
            List<Map<String,Object>> artis = restTemplate.getForObject("http://arti-provider/getSearchArea_arti?arti_title=" + sea_val, List.class);
            List<Map<String,Object>> infos = new ArrayList<>();
            for(int i = 0;i<artis.size();i++){
                Map<String,Object> info = new HashMap<>();
                Map<String,Object> count = restTemplate.getForObject("http://comment-provider/countComment?artiId="+artis.get(i).get("arti_id"), Map.class);
                Users userEntity = restTemplate.getForObject("http://user-provider/getUserInfo?user_id=" + artis.get(i).get("user_id"), Users.class);
                info.put("count",Integer.parseInt(count.get("").toString()));
                info.put("arti",artis.get(i));
                info.put("user",userEntity);
                infos.add(info);
            }
            modelMap.put("user",user);
            modelMap.put("loginUser",loginUser);
            modelMap.put("infos",infos);
            modelMap.put("headTitle","搜索文章列表");
            return "htmlPage/pages/follow/artis";
        }
    }
}
