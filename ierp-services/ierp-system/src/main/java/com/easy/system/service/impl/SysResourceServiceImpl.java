package com.easy.system.service.impl;

import com.easy.common.base.service.impl.BaseServiceImpl;
import com.easy.common.commons.RedisOperationEnum;
import com.easy.common.constant.AuthConstant;
import com.easy.common.entity.redis.RedisDTO;
import com.easy.common.entity.system.SysResource;
import com.easy.common.entity.system.SysRole;
import com.easy.common.entity.system.SysRoleResource;
import com.easy.common.service.RedisService;
import com.easy.system.dao.SysResourceDao;
import com.easy.system.dao.SysRoleDao;
import com.easy.system.dao.SysRoleResourceDao;
import com.easy.system.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/8 0:04
 * @Version 1.0
 **/
@Service
public class SysResourceServiceImpl  extends BaseServiceImpl<SysResourceDao, SysResource> implements SysResourceService {

    @Autowired
    private SysResourceDao sysResourceDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysRoleResourceDao sysRoleResourceDao;

    @Autowired
    private RedisService redisService;



    @Override
    public Map initResourceToRedis() {

        List<SysResource> resourceList = sysResourceDao.listAll(new SysResource());
        List<SysRole> roleList = sysRoleDao.listAll(new SysRole());
        List<SysRoleResource> roleResourcesList = sysRoleResourceDao.listAll(new SysRoleResource());

        Map<String,List<String>> redisMap=new HashMap<>();

        for (SysResource sysResource : resourceList) {
            List<Long> roleIdList = roleResourcesList.stream().filter(roleResource -> roleResource.getResourceId().equals(sysResource.getId())).map(SysRoleResource::getRoleId).collect(Collectors.toList());
            List<String> roleNameList = roleList.stream().filter(role -> roleIdList.contains(role.getId())).map(SysRole::getEnName).collect(Collectors.toList());
            redisMap.put(sysResource.getUrl(),roleNameList);
        }
        RedisDTO resourceRoleMap=RedisDTO.getIntance(AuthConstant.RESOURCE_INTERFACE_MAP_KEY, redisMap, RedisOperationEnum.MAP_OPERATION);
        redisService.set(resourceRoleMap);

        return redisMap;

    }
}
