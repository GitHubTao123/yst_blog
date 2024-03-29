package com.yue.repostprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.yue.repostprovider.mapper")
public class RepostProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepostProviderApplication.class, args);
    }

}
