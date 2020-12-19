package com.easy.system.dao;


import com.easy.common.base.dao.BaseDao;
import com.easy.common.entity.system.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuDao extends BaseDao<SysMenu> {


    List<SysMenu> listByRoleIds(@Param("roleIds") List<String> roleIds);

    
}
