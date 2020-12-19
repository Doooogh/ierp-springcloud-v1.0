package com.easy.system.controller;

import com.easy.common.commons.CommonResult;
import com.easy.common.commons.PageBean;
import com.easy.common.entity.system.SysMenu;
import com.easy.common.validation.InsertActive;
import com.easy.common.validation.UpdateActive;
import com.easy.system.service.SysMenuService;
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
 * @Date 2020/9/15 14:07
 * @Version 1.0
 **/
@Api(tags = "SysMenuController", value = "菜单管理")
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;


    @ApiOperationSupport(ignoreParameters={"id","deleted","createDate","updateDate"})
    @ApiOperation("获取菜单分页列表")
    @GetMapping("/list")
    public CommonResult<PageInfo> list(SysMenu sysMenu,
             @ApiParam(value = "每页数") @RequestParam("pageSize") Integer pageSize, @ApiParam(value = "页码") @RequestParam("pageNum")Integer pageNum){
        PageBean <SysMenu> pageBean=new PageBean<>(sysMenu,pageNum,pageSize);
        PageInfo<SysMenu> list = sysMenuService.list(pageBean);
        return CommonResult.success(list);
    }


    @ApiOperationSupport(ignoreParameters={"sysMenu.id","sysMenu.deleted","sysMenu.createDate","sysMenu.updateDate"})
    @GetMapping("/allList")
    @ApiOperation("获取菜单所有")
    public CommonResult<SysMenu> allList( SysMenu sysMenu){
        List<SysMenu> list = sysMenuService.listAll(sysMenu);
        return CommonResult.success(list);
    }


    @ApiOperationSupport(ignoreParameters={"sysMenu.id","sysMenu.deleted","sysMenu.createDate","sysMenu.updateDate"})
    @ApiOperation("保存菜单")
    @PostMapping("/save")
    public CommonResult save(@Validated(InsertActive.class)  @RequestBody SysMenu sysMenu){
        sysMenuService.save(sysMenu);
        return CommonResult.success();
    }

    @ApiOperationSupport(ignoreParameters={"sysMenu.deleted","sysMenu.createDate","sysMenu.updateDate"})
    @ApiOperation("更新菜单")
    @PostMapping("/update")
    public CommonResult update(@Validated(UpdateActive.class) @RequestBody SysMenu sysMenu){
        sysMenuService.update(sysMenu);
        return CommonResult.success();
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("/delete")
    public CommonResult  delete(@ApiParam(value = "菜单id",required = true)  @RequestParam("id") Long id){
        sysMenuService.delete(id);
        return CommonResult.success();
    }

}
