//package com.yue.service_consumer.controller;
//
//import com.yue.yst_vblog.entity.Users;
//import com.yue.yst_vblog.service.SearchService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.websocket.server.PathParam;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class SearchController {
//
//    @ResponseBody
//    @RequestMapping("/getSearchArea")
//    public Map<String,Object> getSearchArea(@PathParam("type") String type,@PathParam("sea_val")String sea_val){
//        ModelMap map = new ModelMap();
//        map.put("type",type);
//        if(type.equals("user")){
//            List<Users> users = searchService.getSearchArea_user(sea_val);
//            map.put("users",users);
//        }else{
//            List<Map<String,Object>> list = searchService.getSearchArea_arti(sea_val);
//            map.put("lists",list);
//        }
//        System.out.println(map);
//        return map;
//    }
//}
