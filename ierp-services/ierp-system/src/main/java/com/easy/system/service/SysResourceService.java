package com.easy.system.service;

import com.easy.common.base.service.BaseService;
import com.easy.common.entity.system.SysResource;

import java.util.Map;

public interface SysResourceService extends BaseService<SysResource> {

    Map initResourceToRedis();

}
