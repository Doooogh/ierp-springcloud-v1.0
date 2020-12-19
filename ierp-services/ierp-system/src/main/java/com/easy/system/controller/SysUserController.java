package com.easy.system.controller;

import com.easy.common.commons.CommonResult;
import com.easy.common.commons.PageBean;
import com.easy.common.entity.system.SysUser;
import com.easy.common.validation.FindActive;
import com.easy.common.validation.UpdateActive;
import com.easy.system.service.SysUserService;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/3 16:21
 * @Version 1.0
 **/

@Api(tags = "SysUserController", value = "用户管理")
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("根据用户名查找用户")
    @GetMapping("/findByUsername")
    public CommonResult<SysUser> findByUsername(@RequestParam("username") @ApiParam(value = "用户名",required = true) String username){
        SysUser sysUser= sysUserService.findByUsername(username);
        return CommonResult.success(sysUser);
    }



    @ApiOperationSupport(ignoreParameters={"id","deleted","createDate","updateDate"})
    @ApiOperation("获取用户分页列表")
    @GetMapping("/list")
    public CommonResult<PageInfo> list(@Validated(FindActive.class) SysUser sysUser, @ApiParam(value = "每页数") @RequestParam("pageSize") Integer pageSize, @ApiParam(value = "页码") @RequestParam("pageNum")Integer pageNum){
        PageBean <SysUser> pageBean=new PageBean<>(sysUser,pageNum,pageSize);
        PageInfo<SysUser> list = sysUserService.list(pageBean);
        return CommonResult.success(list);
    }





    @ApiOperationSupport(ignoreParameters={"sysUser.identity","sysUser.clientId","sysUser.password","sysUser.roles","sysUser.deleted","sysUser.createDate","sysUser.updateDate"})
    @ApiOperation("更新用户")
    @PostMapping("/update")
    public CommonResult update(@Validated(UpdateActive.class) @RequestBody SysUser sysUser){
        sysUserService.update(sysUser);
        return CommonResult.success();
    }



    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public CommonResult delete(@ApiParam("用户id")  @RequestParam("id") Long id){
        sysUserService.delete(id);
        return CommonResult.success();
    }

}
