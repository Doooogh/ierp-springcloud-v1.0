package com.easy.system.controller;

import com.easy.common.commons.CommonResult;
import com.easy.common.commons.PageBean;
import com.easy.common.entity.system.SysRole;
import com.easy.common.validation.InsertActive;
import com.easy.common.validation.UpdateActive;
import com.easy.system.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/17 10:59
 * @Version 1.0
 **/

@Api(tags = "SysRoleController", value = "角色管理")
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;


    @ApiOperationSupport(ignoreParameters={"id","deleted","createDate","updateDate"})
    @ApiOperation("获取角色分页列表")
    @GetMapping("/list")
    public CommonResult<PageInfo> list(SysRole sysRole,  @ApiParam(value = "每页数") @RequestParam("pageSize") Integer pageSize, @ApiParam(value = "页码") @RequestParam("pageNum")Integer pageNum){
        PageBean <SysRole> pageBean=new PageBean<>( sysRole,pageNum,pageSize);
        PageInfo<SysRole> list = sysRoleService.list(pageBean);
        return CommonResult.success(list);
    }

    @ApiOperationSupport(ignoreParameters={"id","deleted","createDate","updateDate"})
    @GetMapping("/allList")
    @ApiOperation("获取角色所有")
    public CommonResult<SysRole> allList(SysRole sysRole){
        List<SysRole> list = sysRoleService.listAll(sysRole);
        return CommonResult.success(list);
    }

    @ApiOperationSupport(ignoreParameters={"sysRole.resourceIdList","sysRole.menuIdList","sysRole.id","sysRole.deleted","sysRole.createDate","sysRole.updateDate"})
    @PostMapping("/save")
    @ApiOperation("保存角色")
    public CommonResult save(@Validated(InsertActive.class)  @RequestBody SysRole sysRole){
        sysRoleService.save(sysRole);
        return CommonResult.success();
    }


    @ApiOperationSupport(ignoreParameters={"sysRole.resourceIdList","sysRole.menuIdList","SysRole.deleted","SysRole.createDate","SysRole.updateDate"})
    @ApiOperation("更新角色")
    @PostMapping("/update")
    public CommonResult update(@Validated(UpdateActive.class)  @RequestBody SysRole sysRole){
        sysRoleService.update(sysRole);
        return CommonResult.success();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/delete")
    public CommonResult  delete(@ApiParam("角色id") @RequestParam("id") Long id){
        sysRoleService.delete(id);
        return CommonResult.success();
    }









}
