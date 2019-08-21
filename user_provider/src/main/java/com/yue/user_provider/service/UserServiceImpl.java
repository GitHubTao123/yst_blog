package com.yue.user_provider.service;

import com.yue.user_provider.entity.Users;
import com.yue.user_provider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Users getUserInfoById(int user_id) {
        Users user = userMapper.getUserInfoById(user_id);
        return user;
    }

    @Override
    public List<Users> getSearchArea_user(String user_name) {
        List<Users> users = userMapper.getSearchArea_user(user_name);
        return users;
    }
}
