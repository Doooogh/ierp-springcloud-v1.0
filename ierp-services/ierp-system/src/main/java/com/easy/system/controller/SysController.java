package com.easy.system.controller;

import com.easy.common.commons.CommonResult;
import com.easy.common.entity.system.SysClientDetail;
import com.easy.common.entity.system.SysUser;
import com.easy.common.validation.InsertActive;
import com.easy.system.service.SysUserService;
import com.easy.system.service.SystemService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/17 23:11
 * @Version 1.0
 **/
@Api(tags = "SysController")
@Validated
@RequestMapping("/system")
@RestController
public class SysController {

    @Autowired
    private SystemService systemService;

    @Autowired
    private SysUserService sysUserService;



    @ApiOperation(value = "添加客户端信息")
    @PostMapping("/addClientDetail")
    public CommonResult addClientDetail(SysClientDetail sysClientDetail){
        return CommonResult.success();
    }


    @ApiOperation("用户登录")
    @PostMapping("/login")
    public CommonResult login(@ApiParam("用户名") @NotBlank(message = "用户名不能为空")@RequestParam("username") String username,
                              @ApiParam("密码")@NotBlank(message = "密码不能为空") @RequestParam("password") String password,
                              @ApiParam("clientId")@NotBlank(message = "clientId不能为空") @RequestParam("clientId") String clientId){
        return systemService.login(username,password,clientId);
    }


    @ApiOperationSupport(ignoreParameters={"sysUser.status","sysUser.roles","sysUser.id","sysUser.deleted","sysUser.createDate","sysUser.updateDate"})
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public CommonResult register(@Validated(InsertActive.class) @RequestBody  SysUser sysUser){
        if(sysUserService.register(sysUser)>0){
            return CommonResult.success();
        }
        return CommonResult.errorOfMessage("注册失败");
    }



}
