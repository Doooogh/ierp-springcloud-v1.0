package com.easy.system.dao;

import com.easy.common.base.dao.BaseDao;
import com.easy.common.dto.system.RoleResourceAndMenuDTO;
import com.easy.common.entity.system.SysRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleResourceDao extends BaseDao<SysRoleResource> {


    int batchDelete(@Param("ids") List<Long> ids);


    List<Long> findIdsByRoleId(@Param("roleId") Long roleId);



}
