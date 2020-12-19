package com.easy.system.dao;

import com.easy.common.base.dao.BaseDao;
import com.easy.common.entity.system.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/17 11:14
 * @Version 1.0
 **/
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {

    int batchDelete(@Param("ids") List<Long> ids);

    List<Long> findIdsByRoleId(@Param("roleId") Long roleId);
}
