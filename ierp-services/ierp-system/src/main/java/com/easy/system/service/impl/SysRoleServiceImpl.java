package com.easy.system.service.impl;

import com.easy.common.base.service.impl.BaseServiceImpl;
import com.easy.common.entity.system.SysRole;
import com.easy.system.dao.SysRoleDao;
import com.easy.system.dao.SysRoleMenuDao;
import com.easy.system.dao.SysRoleResourceDao;
import com.easy.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/17 11:01
 * @Version 1.0
 **/
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysRoleResourceDao sysRoleResourceDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;


}
