package com.yue.user_provider.service;

import com.yue.user_provider.entity.Users;

import java.util.List;

public interface UserService {
    Users getUserInfoById(int user_id);

    List<Users> getSearchArea_user(String user_name);
}
