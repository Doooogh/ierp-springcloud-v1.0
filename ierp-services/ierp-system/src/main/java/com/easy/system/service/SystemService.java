package com.easy.system.service;

import com.easy.common.commons.CommonResult;

public interface SystemService {

    CommonResult login(String username, String password, String clientId);
}
