package com.yue.service_consumer.controller;

import com.yue.service_consumer.entity.Article;
import com.yue.service_consumer.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ArticleController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getArticleByUserId")
    public String getArticleByUserId(@PathParam("user_id") int user_id, ModelMap map, HttpServletRequest request){
        List artis = restTemplate.getForObject("http://arti-provider/getArticleByUserId?user_id=" + user_id, List.class);
        Users login_user = (Users)request.getSession().getAttribute("login_user");
        Users user = restTemplate.getForObject("http://user-provider/getUserInfo?user_id="+user_id,Users.class);
        request.getSession().setAttribute("user",user);
        map.put("user",user);
        map.put("login_user",login_user);
        map.put("artis",artis);
        return "content/content";
    }

    @RequestMapping("/getArticleByArtiId")
    public String getArticleByArtiId(@RequestParam int arti_id, ModelMap map,HttpServletRequest request){
        Article article = restTemplate.getForObject("http://arti-provider/getArticleByArtiId?arti_id=" + arti_id, Article.class);
        Users login_user = (Users)request.getSession().getAttribute("login_user");
        map.put("login_user_id",login_user.getUser_id());
        map.put("arti",article);
        return "content/contentDetail";
    }

    @RequestMapping("/toAddArtiPage")
    public String toEditArti(HttpServletRequest request,ModelMap map){
        Users login_user = (Users)request.getSession().getAttribute("login_user");
        map.put("login_user_id",login_user.getUser_id());
        return "content/editPage";
    }

    @RequestMapping("/submitArti")
    public String submitArti(@RequestParam String arti_title,@RequestParam String m_textarea,ModelMap map,HttpServletRequest request){
        Users login_user = (Users)request.getSession().getAttribute("login_user");
        restTemplate.put("http://arti-provider/submitArti?login_user_id="+login_user.getUser_id()+"&arti_title="+arti_title+"&arti_cont="+m_textarea,login_user,arti_title,m_textarea);
        map.put("user_id",login_user.getUser_id());
        return "content/success";
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
    public String getMyCollect(ModelMap map,HttpServletRequest request){
        Users user = (Users)request.getSession().getAttribute("user");
        Users login_user = (Users)request.getSession().getAttribute("login_user");
        List<Map<String,Object>> collections = restTemplate.getForObject("http://arti-provider/getMyCollect?user_id=" + user.getUser_id(), List.class);
        List<Article> artis = new ArrayList<>();
        List<Users> users = new ArrayList<>();
        for(int i = 0;i<collections.size();i++){
            Article artiEnrity = restTemplate.getForObject("http://arti-provider/getArticleByArtiId?arti_id="+collections.get(i).get("arti_id"),Article.class);
            artis.add(artiEnrity);
            Users userEntity = restTemplate.getForObject("http://user-provider/getUserInfo?user_id=" + collections.get(i).get("follow_user_id"), Users.class);
            users.add(userEntity);
        }
        map.put("artis",artis);
        map.put("users",users);
        return "article/collect";
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
    public String getHotArti(ModelMap map){
        List<Map<String,Object>> hotArtis = restTemplate.getForObject("http://arti-provider/getHotArti",List.class);
        List<Users> users = new ArrayList<>();
        for(int i = 0;i<hotArtis.size();i++){
            int user_id = Integer.parseInt(hotArtis.get(i).get("user_id").toString());
            Users userEntity = restTemplate.getForObject("http://user-provider/getUserInfo?user_id=" + user_id, Users.class);
            users.add(userEntity);
        }
        map.put("hotArtis",hotArtis);
        map.put("users",users);
        return "article/hotArti";
    }

}
