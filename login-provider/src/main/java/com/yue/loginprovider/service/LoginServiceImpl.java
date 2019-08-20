package com.yue.loginprovider.service;

import com.yue.loginprovider.entity.Users;
import com.yue.loginprovider.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public boolean checkIfExist(String user_name) {
        int count = loginMapper.checkIfExist(user_name);
        boolean b = count==0?false:true;
        return b;
    }

    @Override
    public Users login(String user_name, String user_pw) {
        Users user = loginMapper.login(user_name,user_pw);
        return user;
    }

    @Override
    public void regis_user(Users users) {
        System.out.println(users);
        loginMapper.regis_user(users);
    }
}
