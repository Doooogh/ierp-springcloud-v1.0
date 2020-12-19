package com.easy.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/7 16:53
 * @Version 1.0
 **/
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class IerpClient {
    public static void main(String[] args) {

        SpringApplication.run(IerpClient.class,args);
    }
}
