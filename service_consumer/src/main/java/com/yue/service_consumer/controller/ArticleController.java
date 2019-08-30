package com.yue.service_consumer.controller;

import com.yue.service_consumer.entity.Article;
import com.yue.service_consumer.entity.Users;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getArticleByUserId")
    public String getArticleByUserId(@PathParam("userId") int userId,ModelMap modelMap,HttpServletRequest request){
        Users loginUser = (Users)request.getSession().getAttribute("login_user");
        Users user = restTemplate.getForObject("http://user-provider/getUserInfo?user_id="+userId,Users.class);
        request.getSession().setAttribute("user",user);
        List<Map<String,Object>> artis = restTemplate.getForObject("http://arti-provider/getArticleByUserId?userId=" + userId, List.class);
        List<Map<String,Object>> infos = new ArrayList<>();
        for(int i = 0;i<artis.size();i++){
            Map<String,Object> infoMap = new HashMap<>();
            Map<String,Object> count = restTemplate.getForObject("http://comment-provider/countComment?artiId="+artis.get(i).get("arti_id"), Map.class);
            infoMap.put("arti",artis.get(i));
            infoMap.put("commCount",Integer.parseInt(count.get("").toString()));
            infos.add(infoMap);
        }
        modelMap.put("loginUser",loginUser);
        modelMap.put("user",user);
        modelMap.put("infos",infos);
        return "htmlPage/pages/content/content";
    }

    @GetMapping("/getArticleByArtiId")
    public String getArticleByArtiId(@RequestParam int artiId, ModelMap modelMap,HttpServletRequest request){
        Users loginUser = (Users)request.getSession().getAttribute("login_user");
        Users user = (Users)request.getSession().getAttribute("user");

        Article article = restTemplate.getForObject("http://arti-provider/getArticleByArtiId?artiId=" + artiId, Article.class);
        List<Map<String,Object>> commInfos = restTemplate.getForObject("http://comment-provider/getCommentByArtiId?artiId="+artiId, List.class);
        Users artiUser = restTemplate.getForObject("http://user-provider/getUserInfo?user_id="+article.getUser_id(),Users.class);
        Map<String,Object> count = restTemplate.getForObject("http://comment-provider/countComment?artiId="+article.getArti_id(), Map.class);

        modelMap.put("loginUser",loginUser);
        modelMap.put("user",user);
        modelMap.put("artiUser",artiUser);
        modelMap.put("arti",article);
        modelMap.put("commCount",Integer.parseInt(count.get("").toString()));
        if(commInfos.size() != 0){
            modelMap.put("commInfos",commInfos);
        }
        return "htmlPage/pages/content/contentDetail";
    }

    @ResponseBody
    @GetMapping("/getHotArtiInSingleUser")
    public List<Article> getHotArtiInSingleUser(@RequestParam("user_id")int user_id){
        List<Article> articles = restTemplate.getForObject("http://arti-provider/getHotArtiInSingleUser?user_id="+user_id,List.class);
        return articles;
    }

//    @RequestMapping("/toAddArtiPage")
//    public String toEditArti(HttpServletRequest request,ModelMap map){
//        Users login_user = (Users)request.getSession().getAttribute("login_user");
//        map.put("login_user_id",login_user.getUser_id());
//        return "content/editPage";
//    }

    @PostMapping("/submitArti")
    public String submitArti(@RequestParam String arti_title, @RequestParam String m_textarea, @RequestParam String pro, @RequestParam String city,ModelMap modelMap, HttpServletRequest request){
        Users loginUser = (Users)request.getSession().getAttribute("login_user");
        Users user = (Users) request.getSession().getAttribute("user");
        Article article = new Article(0,loginUser.getUser_id(),arti_title,m_textarea,0,System.currentTimeMillis(),pro+city);
        restTemplate.put("http://arti-provider/submitArti",article);
        modelMap.put("loginUser",loginUser);
        modelMap.put("user",user);
        modelMap.put("headTitle","新增文章成功,即将返回页面");
        return "htmlPage/pages/success/success";
    }

    @RequestMapping("/modifyArti")
    public String modiArti(@RequestParam int arti_id,@RequestParam String arti_title,@RequestParam String arti_cont,HttpServletRequest request,ModelMap map){
        restTemplate.getForObject("http://arti-provider/modifyArti?arti_id="+arti_id+"&arti_title="+arti_title+"&arti_cont="+arti_cont,Object.class);
        Users login_user = (Users)request.getSession().getAttribute("login_user");
        map.put("user_id",login_user.getUser_id());
        return "content/success";
    }

    @RequestMapping("/delArti")
    public String del_arti(@RequestParam int arti_id,ModelMap map,HttpServletRequest request){
        restTemplate.getForObject("http://arti-provider/delArti?arti_id="+arti_id,Object.class);
        Users login_user = (Users)request.getSession().getAttribute("login_user");
        map.put("user_id",login_user.getUser_id());
        return "content/success";
    }

    @ResponseBody
    @RequestMapping("/toggleFollowArti")
    public Map<String,Object> toggleFollowArti(@RequestParam int arti_id, @RequestParam boolean ifColl,HttpServletRequest request){
        Users login_user = (Users) request.getSession().getAttribute("login_user");
        ModelMap map = new ModelMap();
        if(ifColl){
            restTemplate.delete("http://arti-provider/cancelFollowArti?login_user_id="+login_user.getUser_id()+"&arti_id="+arti_id);
            map.put("msg","取消收藏成功!");
            map.put("ifColl",false);
        }else{
            restTemplate.put("http://arti-provider/addFollowArti?login_user_id="+login_user.getUser_id()+"&arti_id="+arti_id,login_user,arti_id);
            map.put("msg","收藏成功!");
            map.put("ifColl",true);
        }
        return map;
    }

    @RequestMapping("/getMyCollect")
    public String getMyCollect(ModelMap modelMap,HttpServletRequest request){
        Users user = (Users)request.getSession().getAttribute("user");
        Users loginUser = (Users)request.getSession().getAttribute("login_user");
        List<Map<String,Object>> collections = restTemplate.getForObject("http://arti-provider/getMyCollect?user_id=" + loginUser.getUser_id(), List.class);
        List<Map<String,Object>> infos = new ArrayList<>();
        for(int i = 0;i<collections.size();i++){
            Map<String,Object> info = new HashMap<>();
            Article artiEnrity = restTemplate.getForObject("http://arti-provider/getArticleByArtiId?arti_id="+collections.get(i).get("arti_id"),Article.class);
            Map<String,Object> count = restTemplate.getForObject("http://comment-provider/countComment?arti_id="+artiEnrity.getArti_id(), Map.class);
            Users userEntity = restTemplate.getForObject("http://user-provider/getUserInfo?user_id=" + artiEnrity.getUser_id(), Users.class);
            info.put("arti",artiEnrity);
            info.put("user",userEntity);
            info.put("count",Integer.parseInt(count.get("").toString()));
            infos.add(info);
        }
        modelMap.put("user",user);
        modelMap.put("loginUser",loginUser);
        modelMap.put("infos",infos);
        modelMap.put("headTitle","MY Collections");
        return "htmlPage/pages/follow/artis";
    }

    @ResponseBody
    @RequestMapping("/checkIfCollect")
    public boolean checkIfCollect(@RequestParam int arti_id,HttpServletRequest request){
        Users login_user = (Users)request.getSession().getAttribute("login_user");
        Map<String,Object> map = restTemplate.getForObject("http://arti-provider/checkIfCollect?login_user_id="+login_user.getUser_id()+"&arti_id=" + arti_id, Map.class);
        boolean ifCol = Integer.parseInt(map.get("count").toString()) == 0 ? false : true;
        return ifCol;
    }

    @RequestMapping("/getHotArti")
    public String getHotArti(ModelMap modelMap,HttpServletRequest request){
        Users user = (Users)request.getSession().getAttribute("user");
        Users loginUser = (Users)request.getSession().getAttribute("login_user");
        List<Map<String,Object>> hotArtis = restTemplate.getForObject("http://arti-provider/getHotArti",List.class);
        List<Map<String,Object>> infos = new ArrayList<>();
        for(int i = 0;i<hotArtis.size();i++){
            Map<String,Object> info = new HashMap<>();
            Map<String,Object> count = restTemplate.getForObject("http://comment-provider/countComment?arti_id="+hotArtis.get(i).get("arti_id"), Map.class);
            Users userEntity = restTemplate.getForObject("http://user-provider/getUserInfo?user_id=" + hotArtis.get(i).get("user_id"), Users.class);
            info.put("arti",hotArtis.get(i));
            info.put("user",userEntity);
            info.put("count",Integer.parseInt(count.get("").toString()));
            infos.add(info);
        }
        modelMap.put("user",user);
        modelMap.put("loginUser",loginUser);
        modelMap.put("infos",infos);
        modelMap.put("headTitle","HOT Articles");
        return "htmlPage/pages/follow/artis";
    }

}
