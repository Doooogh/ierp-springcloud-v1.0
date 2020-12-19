package com.easy.system.service;

import com.easy.common.base.service.BaseService;
import com.easy.common.dto.system.RoleResourceAndMenuDTO;
import com.easy.common.entity.system.SysRoleResource;

public interface SysRoleResourceService extends BaseService<SysRoleResource> {

    int batchSave(RoleResourceAndMenuDTO resourceAndMenuDTO);

}
