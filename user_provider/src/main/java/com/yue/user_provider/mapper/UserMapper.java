package com.yue.user_provider.mapper;

import com.yue.user_provider.entity.Users;

import java.util.List;

public interface UserMapper {
    Users getUserInfoById(int user_id);

    List<Users> getSearchArea_user(String user_name);
}
