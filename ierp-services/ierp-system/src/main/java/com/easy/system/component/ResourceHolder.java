package com.easy.system.component;

import com.easy.system.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/8 0:03
 * @Version 1.0
 **/
@Component
public class ResourceHolder {

    @Autowired
    private SysResourceService sysResourceService;

    @PostConstruct
    public void initResourceMap(){
        sysResourceService.initResourceToRedis();
    }

    @PostConstruct
    public void test11(){
//        RedisUtils.test1();
        System.out.println("------------------------------");
        System.out.println(sysResourceService.toString());
    }

}
