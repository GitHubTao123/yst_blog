package com.yue.loginprovider.service;

import com.yue.loginprovider.entity.Users;

public interface LoginService {

    boolean checkIfExist(String user_name);

    Users login(Users users);

    void regis_user(Users users);
}
