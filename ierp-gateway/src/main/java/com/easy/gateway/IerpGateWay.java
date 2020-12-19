package com.easy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/4 16:59
 * @Version 1.0
 **/
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.easy.gateway","com.easy.common"})
public class IerpGateWay {
    public static void main(String[] args) {
        SpringApplication.run(IerpGateWay.class,args);
    }
}
