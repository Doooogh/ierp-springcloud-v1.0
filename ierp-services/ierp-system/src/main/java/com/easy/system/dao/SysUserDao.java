package com.easy.system.dao;


import com.easy.common.base.dao.BaseDao;
import com.easy.common.entity.system.SysUser;

public interface SysUserDao  extends BaseDao<SysUser> {

    SysUser findByUsername(String username);
}
