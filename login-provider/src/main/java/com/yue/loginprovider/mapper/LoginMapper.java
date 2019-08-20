package com.yue.loginprovider.mapper;

import com.yue.loginprovider.entity.Users;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper {

    int checkIfExist(String user_name);

    Users login(String user_name, String user_pw);

    void regis_user(@Param("users") Users users);
}
