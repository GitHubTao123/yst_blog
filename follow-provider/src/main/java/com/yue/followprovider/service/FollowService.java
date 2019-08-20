package com.yue.followprovider.service;

import com.yue.followprovider.entity.Follow;

import java.util.List;

public interface FollowService {
    List<Follow> getMyFollow(int user_id);

    List<Follow> getMyFollowed(int user_id);

    int checkIfFollow(int user_id, int login_user_id);

    void cancel_followed_user(int login_user_id, int user_id);

    void add_followed_user(int login_user_id, int user_id);

}
