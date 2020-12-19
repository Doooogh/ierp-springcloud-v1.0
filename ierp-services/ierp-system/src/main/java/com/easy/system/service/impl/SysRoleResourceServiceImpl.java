package com.easy.system.service.impl;

import com.easy.common.base.service.impl.BaseServiceImpl;
import com.easy.common.dto.system.RoleResourceAndMenuDTO;
import com.easy.common.entity.system.SysRoleResource;
import com.easy.system.dao.SysRoleResourceDao;
import com.easy.system.service.SysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/17 13:18
 * @Version 1.0
 **/

@Service
public class SysRoleResourceServiceImpl extends BaseServiceImpl<SysRoleResourceDao, SysRoleResource> implements SysRoleResourceService {

    @Autowired
    private SysRoleResourceDao sysRoleResourceDao;


    /**
     * @Author li long
     * @Description --根据role 和资源id集合 批量存储
     * @Date 2020/9/17 13:40
     * @Param [resourceAndMenuDTO]
     * @return int
     **/
    @Override
    public int batchSave(RoleResourceAndMenuDTO resourceAndMenuDTO) {
        SysRoleResource sysRoleResource=new SysRoleResource();
        sysRoleResource.setRoleId(resourceAndMenuDTO.getRoleId());

        //该角色所拥有的资源id集合
        List<Long> ids=new ArrayList<>();
        //该角色所拥有的资源id集合
        sysRoleResourceDao.listAll(sysRoleResource).stream().forEach(resource->ids.add(resource.getId()));

        if(ids.size()>0){
            sysRoleResourceDao.batchDelete(ids);
        }


        for (Long id : resourceAndMenuDTO.getIds()) {
            SysRoleResource sysRoleResource1=new SysRoleResource(resourceAndMenuDTO.getRoleId(),id);
            sysRoleResourceDao.save(sysRoleResource1);
        }

        return 0;  //没有意义
    }


}
