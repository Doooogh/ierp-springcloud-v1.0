package com.easy.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/5 0:18
 * @Version 1.0
 **/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.easy.auth")
public class IerpAuth {
    public static void main(String[] args) {
        SpringApplication.run(IerpAuth.class,args);
    }
}
