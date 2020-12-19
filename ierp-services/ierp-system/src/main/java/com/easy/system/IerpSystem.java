package com.easy.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;



/**
 * @Description
 * @Author li long
 * @Date 2020/9/5 0:39
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"com.easy.system","com.easy.common"})
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.easy.system.dao")
public class IerpSystem {
    public static void main(String[] args) {
        SpringApplication.run(IerpSystem.class,args);
    }
}
