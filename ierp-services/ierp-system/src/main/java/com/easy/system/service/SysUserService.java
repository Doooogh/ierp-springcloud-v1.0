package com.easy.system.service;

import com.easy.common.base.service.BaseService;
import com.easy.common.entity.auth.AuthUser;
import com.easy.common.entity.system.SysUser;

public interface SysUserService extends BaseService<SysUser> {


    SysUser findByUsername(String username);

    AuthUser findAuthByUsername(String username);

    int register(SysUser sysUser);

}
