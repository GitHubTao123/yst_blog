package com.yue.followprovider.controller;

import com.yue.followprovider.entity.Follow;
import com.yue.followprovider.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;

    @RequestMapping("/getMyFollow")
    public List<Follow> getMyFollow(@PathParam("user_id")int user_id){
        List<Follow> fols = followService.getMyFollow(user_id);
        System.out.println(fols);
        return fols;
    }

    @RequestMapping("/getMyFollowed")
    public List<Follow> getMyFollowed(@PathParam("user_id")int user_id){
        List<Follow> foleds = followService.getMyFollowed(user_id);
        return foleds;
    }

    @RequestMapping("/checkIfFollow")
    public Map<String,Integer> checkIfFollow(@PathParam("user_id")int user_id, @PathParam("login_user_id")int login_user_id){
        Map<String,Integer> map = new HashMap<>();
        map.put("count",followService.checkIfFollow(user_id,login_user_id));
        return map;
    }

    @RequestMapping("/add_followed_user")
    public void add_followed_user(@RequestParam int login_user_id, @RequestParam int user_id){
        followService.add_followed_user(login_user_id,user_id);
    }

    @RequestMapping("/cancel_followed_user")
    public void cancel_followed_user(@RequestParam int login_user_id, @RequestParam int user_id){
        followService.cancel_followed_user(login_user_id,user_id);
    }

//    @ResponseBody
//    @RequestMapping("/toggle_follow_arti")
//    public Map<String,Object> toggle_follow_arti(@PathParam("user_id")int user_id, @PathParam("arti_id")int arti_id, @PathParam("ifColl") boolean ifColl){
//        ModelMap map = new ModelMap();
//        if(ifColl){
//            followService.cancel_followed_arti(user_id,arti_id);
//            map.put("msg","取消收藏成功!");
//            map.put("ifColl",false);
//        }else{
//            followService.add_followed_arti(user_id,arti_id);
//            map.put("msg","收藏成功!");
//            map.put("ifColl",true);
//        }
//        return map;
//    }

//    @RequestMapping("/getMyCollect")
//    public String getMyCollect(@PathParam("user_id") int user_id,ModelMap map,HttpServletRequest request){
//        Users user = (Users)request.getSession().getAttribute("login_user");
//        List<Integer> collections = followService.getMyCollect(user.getUser_id());
//        map.put("coll",collections);
//        map.put("login_user_id",user.getUser_id());
//        map.put("user_id",user_id);
//        return "follow/collect";
//    }

//    @ResponseBody
//    @RequestMapping("/checkIfCollect")
//    public boolean checkIfCollect(@PathParam("login_user_id") int login_user_id,@PathParam("arti_id") int arti_id){
//        boolean b = followService.checkIfCollect(login_user_id,arti_id);
//        return b;
//    }

//    @RequestMapping("/getHotArti")
//    public String getHotArti(@PathParam("user_id") int user_id,ModelMap map,HttpServletRequest request){
//        List<Map<String,Object>> hot_artiAndUser = followService.getHotArti();
//        Users user = (Users)request.getSession().getAttribute("login_user");
//        map.put("hot_artiAndUser",hot_artiAndUser);
//        map.put("login_user_id",user.getUser_id());
//        System.out.println(map);
//        return "follow/hotArti";
//    }
}
