package com.yue.service_consumer.controller;

import com.yue.service_consumer.entity.Article;
import com.yue.service_consumer.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping("/getSearchArea")
    public ModelMap getSearchArea(@PathParam("type") String type, @PathParam("sea_val") String sea_val){
        ModelMap map = new ModelMap();
        if(type.equals("user")){
            ResponseEntity<List> entity = restTemplate.getForEntity("http://user-provider/getSearchArea_user?user_name=" + sea_val, List.class);
            List<Users> users = entity.getBody();
            map.put("users",users);
        }else{
            ResponseEntity<List> entity = restTemplate.getForEntity("http://arti-provider/getSearchArea_arti?arti_title=" + sea_val, List.class);
            List<Map<String,Object>> artis = entity.getBody();
            List<Map<String,Object>> lists = new ArrayList<>();
            for(int i = 0;i<artis.size();i++){
                Map<String,Object> detailMap = new HashMap<>();
                Users user = restTemplate.getForObject("http://user-provider/getUserInfo?user_id=" + artis.get(i).get("user_id"), Users.class);
                detailMap.put("user",user);
                detailMap.put("arti",artis.get(i));
                lists.add(detailMap);
            }
            map.put("lists",lists);
        }
        System.out.println(map);
        return map;
    }
}
