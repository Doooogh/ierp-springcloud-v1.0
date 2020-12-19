package com.easy.system.service;

import com.easy.common.base.service.BaseService;
import com.easy.common.dto.system.RoleResourceAndMenuDTO;
import com.easy.common.entity.system.SysRoleMenu;

public interface SysRoleMenuService extends BaseService<SysRoleMenu> {

    int batchSave(RoleResourceAndMenuDTO resourceAndMenuDTO);

}
