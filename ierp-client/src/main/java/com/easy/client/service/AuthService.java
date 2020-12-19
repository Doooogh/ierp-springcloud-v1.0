package com.easy.client.service;

import com.easy.common.commons.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("ierp-auth")
public interface AuthService {


    @PostMapping(value = "/oauth/token")
    CommonResult getLoginSuccessToken(@RequestParam Map<String, String> parameters);
}
