package com.yue.loginprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@MapperScan("com.yue.loginprovider.mapper")
@SpringBootApplication
public class LoginProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginProviderApplication.class, args);
    }

}
