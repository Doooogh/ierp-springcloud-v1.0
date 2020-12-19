package com.easy.system.dao;

import com.easy.common.base.dao.BaseDao;
import com.easy.common.entity.system.SysResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourceDao extends BaseDao<SysResource> {


    List<SysResource> listByRoleIds(@Param("roleIds") List<String> roleIds);

    /**
     * @Author li long
     * @Description --根据角色ids来查询资源  type为类型 1 为接口 2 为按钮
     * @Date 2020/9/7 14:53
     * @Param [roleIds, type]
     * @return java.util.List<com.easy.common.entity.system.SysResource>
     **/
    List<SysResource> listByRoleIdsWithType(@Param("roleIds")List<String> roleIds, @Param("type") String type);


}
