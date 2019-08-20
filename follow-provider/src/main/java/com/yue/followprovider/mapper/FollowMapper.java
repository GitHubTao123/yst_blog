package com.yue.followprovider.mapper;

import com.yue.followprovider.entity.Follow;

import java.sql.Date;
import java.util.List;

public interface FollowMapper {

    List<Follow> getMyFollow(int user_id);

    List<Follow> getMyFollowed(int user_id);

    int checkIfFollow(int user_id, int login_user_id);

    void cancel_followed_user(int login_user_id, int user_id);

    void add_followed_user(int login_user_id, int user_id, Date date);

//    List<Integer> getMyCollect(int login_user_id);

//    int checkIfCollect(int login_user_id, int arti_id);
//
//    void add_followed_arti(int user_id, int arti_id);
//
//    void cancel_followed_arti(int user_id, int arti_id);

}
