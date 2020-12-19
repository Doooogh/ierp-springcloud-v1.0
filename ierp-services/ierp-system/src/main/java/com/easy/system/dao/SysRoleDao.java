package com.easy.system.dao;

import com.easy.common.base.dao.BaseDao;
import com.easy.common.entity.system.SysRole;

import java.util.List;

public interface SysRoleDao extends BaseDao<SysRole> {


    List<SysRole> listByUserId(Long userId);


}
