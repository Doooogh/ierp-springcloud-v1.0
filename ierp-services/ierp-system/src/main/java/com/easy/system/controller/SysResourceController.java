package com.easy.system.controller;

import com.easy.common.commons.CommonResult;
import com.easy.common.commons.PageBean;
import com.easy.common.entity.system.SysResource;
import com.easy.common.validation.InsertActive;
import com.easy.common.validation.UpdateActive;
import com.easy.system.service.SysResourceService;
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
 * @Date 2020/9/17 11:04
 * @Version 1.0
 **/
@Api(tags = "SysResourceController", value = "资源管理")
@RestController
@RequestMapping("/sysResource")
public class SysResourceController {


    @Autowired
    private SysResourceService sysResourceService;


    @ApiOperationSupport(ignoreParameters={"id","deleted","createDate","updateDate"})
    @ApiOperation("获取资源分页列表")
    @GetMapping("/list")
    public CommonResult<PageInfo> list(SysResource SysResource, @ApiParam(value = "每页数") @RequestParam("pageSize") Integer pageSize, @ApiParam(value = "页码") @RequestParam("pageNum")Integer pageNum){
        PageBean<SysResource> pageBean=new PageBean<>(SysResource,pageNum,pageSize);
        PageInfo<SysResource> list = sysResourceService.list(pageBean);
        return CommonResult.success(list);
    }

    @ApiOperationSupport(ignoreParameters={"id","deleted","createDate","updateDate"})
    @GetMapping("/allList")
    @ApiOperation("获取资源所有")
    public CommonResult<SysResource> allList(SysResource SysResource){
        List<SysResource> list = sysResourceService.listAll(SysResource);
        return CommonResult.success(list);
    }



    @PostMapping("/save")
    @ApiOperation("保存资源")
    @ApiOperationSupport(ignoreParameters={"sysResource.id","sysResource.deleted","sysResource.createDate","sysResource.updateDate"})
    public CommonResult save(@Validated(InsertActive.class)  @RequestBody SysResource sysResource){
        sysResourceService.save(sysResource);
        return CommonResult.success();
    }

    @ApiOperationSupport(ignoreParameters={"sysResource.deleted","sysResource.createDate","sysResource.updateDate"})
    @ApiOperation("更新资源")
    @PostMapping("/update")
    public CommonResult update(@Validated(UpdateActive.class) @RequestBody SysResource SysResource){
        sysResourceService.update(SysResource);
        return CommonResult.success();
    }

    @ApiOperation("删除资源")
    @DeleteMapping("/delete")
    public CommonResult  delete(@ApiParam("资源id") @RequestParam("id") Long id){
        sysResourceService.delete(id);
        return CommonResult.success();
    }
}
