package com.easy.system.service.impl;

import com.easy.common.base.service.impl.BaseServiceImpl;
import com.easy.common.dto.system.RoleResourceAndMenuDTO;
import com.easy.common.entity.system.SysRoleMenu;
import com.easy.system.dao.SysRoleMenuDao;
import com.easy.system.service.SysRoleMenuService;
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
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;


    /**
     * @Author li long
     * @Description --根据role 和菜单id集合 批量存储
     * @Date 2020/9/17 13:40
     * @Param [resourceAndMenuDTO]
     * @return int
     **/
    @Override
    public int batchSave(RoleResourceAndMenuDTO resourceAndMenuDTO) {
        SysRoleMenu sysRoleMenu=new SysRoleMenu();
        sysRoleMenu.setRoleId(resourceAndMenuDTO.getRoleId());

        //该角色所拥有的菜单id集合
        List<Long> ids=new ArrayList<>();
        //该角色所拥有的资源id集合
        sysRoleMenuDao.listAll(sysRoleMenu).stream().forEach(menu->ids.add(menu.getId()));

        if(ids.size()>0){
            sysRoleMenuDao.batchDelete(ids);
        }


        for (Long id : resourceAndMenuDTO.getIds()) {
            SysRoleMenu sysRoleMenu1=new SysRoleMenu(resourceAndMenuDTO.getRoleId(),id);
            sysRoleMenuDao.save(sysRoleMenu1);
        }

        return 0;  //没有意义
    }


}
