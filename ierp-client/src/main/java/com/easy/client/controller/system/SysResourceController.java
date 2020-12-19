package com.easy.client.controller.system;

import com.easy.client.service.SysResourceService;
import com.easy.common.commons.CommonResult;
import com.easy.common.entity.system.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/17 16:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/sysResource")
public class SysResourceController {


    @Autowired
    private SysResourceService resourceService;

    @GetMapping("/list")
    public  CommonResult list(SysResource sysResource, @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum){
        return resourceService.list(sysResource,pageSize,pageNum);
    }

    @PostMapping("/save")
    public CommonResult save(@RequestBody SysResource sysResource){
        return resourceService.save(sysResource);
    }
}
