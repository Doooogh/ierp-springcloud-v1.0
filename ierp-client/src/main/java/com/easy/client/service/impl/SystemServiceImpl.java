package com.easy.client.service.impl;

import cn.hutool.core.util.StrUtil;
import com.easy.client.service.AuthService;
import com.easy.client.service.SystemService;
import com.easy.common.commons.CommonResult;
import com.easy.common.commons.ResultStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/7 17:23
 * @Version 1.0
 **/
@Service
@Slf4j
public class SystemServiceImpl implements SystemService {

    @Autowired
    private AuthService authService;

    @Override
    public CommonResult login(String username, String password, String clientId) {
        if(StrUtil.isEmpty(clientId)){
            return CommonResult.errorOfRestltEnum(ResultStatusEnum.UNKNOW_CLIENT);
        }
        if(StrUtil.isEmpty(username)||StrUtil.isEmpty(password)){
            return CommonResult.errorOfRestltEnum(ResultStatusEnum.USER_LOGIN_FAILED);
        }
        Map<String,String> params=new HashMap<>();
        params.put("client_id",clientId);
        params.put("client_secret","xcyg1234");
        params.put("grant_type","password");
        params.put("username",username);
        params.put("password",password);

        CommonResult getTokenRes = authService.getLoginSuccessToken(params);
        if(getTokenRes.getCode().equals(ResultStatusEnum.SUCCESS.getCode())){
            //TODO 日志记录
            log.info("登录成功！-------------");
        }
        return getTokenRes;
    }
}
