package com.yue.followprovider.service;

import com.yue.followprovider.entity.Follow;
import com.yue.followprovider.mapper.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class FollowServiceImpl implements FollowService{

    @Autowired
    private FollowMapper followMapper;

    @Override
    public List<Follow> getMyFollow(int user_id) {
        List<Follow> fols = followMapper.getMyFollow(user_id);
        return fols;
    }

    @Override
    public List<Follow> getMyFollowed(int user_id) {
        List<Follow> foleds = followMapper.getMyFollowed(user_id);
        return foleds;
    }

    @Override
    public int checkIfFollow(int user_id, int login_user_id) {
        int count = followMapper.checkIfFollow(user_id,login_user_id);
        return count;
    }

    @Override
    public void cancel_followed_user(int login_user_id, int user_id) {
        followMapper.cancel_followed_user(login_user_id,user_id);
    }

    @Override
    public void add_followed_user(int login_user_id, int user_id) {
        java.util.Date d = new java.util.Date();
        Date date = new Date(d.getTime());
        followMapper.add_followed_user(login_user_id,user_id,date);
    }

//    @Override
//    public List<Integer> getMyCollect(int login_user_id) {
//        List<Map<String,Object>> collections = new ArrayList<>();
//        List<Integer> list_arti_id = followMapper.getMyCollect(login_user_id);
//        Iterator<Integer> iterator = list_arti_id.iterator();
//        while(iterator.hasNext()){
//            Map<String,Object> map = new HashMap<>();
//            Article article = articleMapper.getContent(iterator.next());
//            Users userInfo = userMapper.getUserInfoById(article.getUser_id());
//            map.put("userInfo",userInfo);
//            map.put("arti",article);
//            collections.add(map);
//        }
//        return list_arti_id;
//    }

//    @Override
//    public boolean checkIfCollect(int login_user_id, int arti_id) {
//        int count = followMapper.checkIfCollect(login_user_id,arti_id);
//        if(count == 0){
//            return false;
//        }
//        return true;
//    }

//    @Override
//    public void add_followed_arti(int user_id, int arti_id) {
//        followMapper.add_followed_arti(user_id,arti_id);
//    }
//
//    @Override
//    public void cancel_followed_arti(int user_id, int arti_id) {
//        followMapper.cancel_followed_arti(user_id,arti_id);
//    }

//    @Override
//    public List<Integer> getHotArti() {
//        List<Article> artis = articleMapper.getHotArti();
//        List<Map<String,Object>> hot_artiAndUser = new ArrayList<>();
//        for(int i = 0;i<artis.size();i++){
//            Map<String,Object> map = new HashMap<>();
//            Article article = articleMapper.getContent(artis.get(i).getArti_id());
//            Users user = userMapper.getUserInfoById(artis.get(i).getUser_id());
//            map.put("userInfo",user);
//            map.put("arti",article);
//            hot_artiAndUser.add(map);
//        }
//        return hot_artiAndUser;
//    }


}
