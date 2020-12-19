package com.easy.system.controller;

import com.easy.common.commons.CommonResult;
import com.easy.common.entity.auth.AuthUser;
import com.easy.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/5 12:46
 * @Version 1.0
 **/
@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/loadUserByUsername/{username}")
    public CommonResult<AuthUser> loadUserByUsername(@PathVariable("username") String username){
        AuthUser sysUser = sysUserService.findAuthByUsername(username);
        return CommonResult.success(sysUser);
    }
}
