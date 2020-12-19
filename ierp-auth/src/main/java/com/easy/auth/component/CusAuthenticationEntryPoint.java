package com.easy.auth.component;

import cn.hutool.json.JSONUtil;
import com.easy.common.commons.CommonResult;
import com.easy.common.commons.ResultStatusEnum;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 自定义oauth2异常
 * @Author li long
 * @Date 2020/9/18 13:49
 * @Version 1.0
 **/
public class CusAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        ResultStatusEnum resultEnum=null;
         if(exception instanceof BadCredentialsException){
            resultEnum=ResultStatusEnum.UNKNOW_CLIENT;
        }else {
             resultEnum=ResultStatusEnum.ERROR;
         }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.errorOfRestltEnum(resultEnum)));
        response.getWriter().flush();

    }
}
