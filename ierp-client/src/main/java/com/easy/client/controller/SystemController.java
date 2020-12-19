package com.easy.client.controller;

import com.easy.client.service.SystemService;
import com.easy.common.commons.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/7 16:58
 * @Version 1.0
 **/
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @PostMapping("/login")
    public CommonResult login (String username,String password,String clientId){
        CommonResult loginResult = systemService.login(username, password, clientId);
        return loginResult;
    }
}
