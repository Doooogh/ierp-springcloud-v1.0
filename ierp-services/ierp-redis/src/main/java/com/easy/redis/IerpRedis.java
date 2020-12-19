package com.easy.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/8 13:51
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = "com.easy.redis")
public class IerpRedis {
    public static void main(String[] args) {
        SpringApplication.run(IerpRedis.class,args);
    }
}
