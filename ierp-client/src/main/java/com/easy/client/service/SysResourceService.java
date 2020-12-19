package com.easy.client.service;

import com.easy.common.commons.CommonResult;
import com.easy.common.entity.system.SysResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ierp-system")
public interface SysResourceService {

    @GetMapping("/sysResource/list")
     CommonResult list(@RequestParam SysResource sysResource, @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum);

    @PostMapping("/sysResource/save")
    CommonResult save(@RequestBody SysResource sysResource);


}
