package com.easy.auth.service;

import com.easy.common.commons.CommonResult;
import com.easy.common.entity.auth.AuthUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "ierp-system")
@Component
public interface AuthUserService {

    @GetMapping("/auth/loadUserByUsername/{username}")
    CommonResult<AuthUser> loadUserByUsername(@PathVariable("username") String username);

}
