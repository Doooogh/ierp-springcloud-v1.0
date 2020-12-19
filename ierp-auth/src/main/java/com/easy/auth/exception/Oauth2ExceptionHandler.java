package com.easy.auth.exception;

import cn.hutool.core.bean.BeanUtil;
import com.easy.common.commons.CommonResult;
import com.easy.common.commons.ResultStatusEnum;
import com.easy.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局处理Oauth2抛出的异常
 */
@ControllerAdvice
@RestController
@Slf4j
public class Oauth2ExceptionHandler {

    @ExceptionHandler(value = OAuth2Exception.class)
    public CommonResult handleOauth2(OAuth2Exception e) {
        return CommonResult.errorOfMessage(e.getMessage());
    }

    //异常处理
    @ExceptionHandler(value = ApiException.class)
    public CommonResult exceptionHandler(ApiException exception){
        log.error("系统错误！错误信息:{ "+exception.getMessage()+"}",exception);
        if(!BeanUtil.isEmpty(exception.getCode())){
            return CommonResult.error(exception.getCode(),exception.getMessage());
        }
        return CommonResult.error(ResultStatusEnum.ERROR.getCode(),exception.getMessage());
    }



}
