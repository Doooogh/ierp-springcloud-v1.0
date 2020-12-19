package com.easy.system.controller;

import com.easy.common.commons.CommonResult;
import com.easy.common.dto.system.RoleResourceAndMenuDTO;
import com.easy.system.service.SysRoleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/17 13:21
 * @Version 1.0
 **/

@Api(tags = "SysRoleResourceController", value = "角色资源关联操作")
@RestController
@RequestMapping("/sysRoleResource")
public class SysRoleResourceController {


    @Autowired
    private SysRoleResourceService sysRoleResourceService;


    @ApiOperation("根据角色id 增加、更新 角色资源id")
    @PostMapping("batchSaveByRoleId")
    public CommonResult batchSaveByRoleId(@Validated  @RequestBody RoleResourceAndMenuDTO roleDTO){
        sysRoleResourceService.batchSave(roleDTO);
        return CommonResult.success();
    }


}
