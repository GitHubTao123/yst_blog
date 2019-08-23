package com.yue.commentprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@MapperScan("com.yue.commentprovider.mapper")
@SpringBootApplication
public class CommentProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentProviderApplication.class, args);
    }

}
