package com.yue.followprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@MapperScan("com.yue.followprovider.mapper")
@SpringBootApplication
public class FollowProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FollowProviderApplication.class, args);
    }

}
