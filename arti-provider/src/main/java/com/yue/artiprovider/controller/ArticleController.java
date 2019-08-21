package com.yue.artiprovider.controller;

import com.yue.artiprovider.entity.Article;
import com.yue.artiprovider.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/getArticleByUserId")
    public List<Article> getArticleByUserId(@PathParam("user_id") int user_id){
        List<Article> artis = articleService.getArticleByUserId(user_id);
        return artis;
    }

    @RequestMapping("/getArticleByArtiId")
    public Article toDetailCont(@RequestParam int arti_id){
        Article arti = articleService.getContent(arti_id);
        return arti;
    }

    @RequestMapping("/submitArti")
    public void submitArti(@RequestParam int login_user_id,@RequestParam String arti_title, @RequestParam String arti_cont){
        articleService.submitArti(login_user_id,arti_title,arti_cont);
    }

    @RequestMapping("/modifyArti")
    public void modiArti(@RequestParam  int arti_id,@RequestParam String arti_title,@RequestParam String arti_cont){
        articleService.modiArti(arti_id,arti_title,arti_cont);
    }

    @RequestMapping("/delArti")
    public void delArti(@RequestParam int arti_id){
        articleService.delArti(arti_id);
    }

    @RequestMapping("/cancelFollowArti")
    public void cancelFollowArti(@RequestParam int login_user_id,@RequestParam int arti_id){
        articleService.cancelFollowArti(login_user_id,arti_id);
    }

    @RequestMapping("/addFollowArti")
    public void addFollowArti(@RequestParam int login_user_id,@RequestParam int arti_id){
        articleService.addFollowArti(login_user_id,arti_id);
    }

    @RequestMapping("/getMyCollect")
    public List<Article> getMyCollect(@RequestParam int user_id){
        List<Article> collections = articleService.getMyCollect(user_id);
        return collections;
    }

    @RequestMapping("/checkIfCollect")
    public Map<String,Integer> checkIfCollect(@PathParam("login_user_id") int login_user_id, @PathParam("arti_id") int arti_id){
        Map<String,Integer> map = new HashMap<>();
        int count = articleService.checkIfCollect(login_user_id,arti_id);
        map.put("count",count);
        return map;
    }

    @RequestMapping("/getHotArti")
    public List<Article> getHotArti(){
        List<Article> hot_artiAndUser = articleService.getHotArti();
        return hot_artiAndUser;
    }

    @RequestMapping("/getSearchArea_arti")
    public List<Article> getSearchArea_arti(@PathParam("arti_title") String arti_title){
        List<Article> lists = articleService.getSearchArea_arti(arti_title);
        System.out.println(lists);
        return lists;
    }
}
