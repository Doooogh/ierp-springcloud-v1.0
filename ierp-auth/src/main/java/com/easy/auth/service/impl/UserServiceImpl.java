package com.easy.auth.service.impl;

import com.easy.auth.constant.MessageConstant;
import com.easy.auth.domain.SecurityUser;
import com.easy.auth.service.AuthUserService;
import com.easy.common.commons.CommonResult;
import com.easy.common.commons.ResultStatusEnum;
import com.easy.common.constant.AuthConstant;
import com.easy.common.entity.auth.AuthUser;
import com.easy.common.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理业务类
 * Created by macro on 2020/6/19.
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        AuthUser authUser=null;

        //请求为后端项目
        if(AuthConstant.BACK_MANAGER_ID.equals(clientId)){
            CommonResult<AuthUser> res = authUserService.loadUserByUsername(username);
            authUser=res.getData();
        }
        //TODO
        //请求为前端项目
        if(AuthConstant.FRONT_MANAGER_ID.equals(clientId)){
            CommonResult<AuthUser> res = authUserService.loadUserByUsername(username);
            System.out.println("-----------------------------");
            System.out.println(res.toString());
            authUser=res.getData();
        }

        if (authUser==null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        authUser.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(authUser);
        if (!securityUser.isEnabled()) {
            throw new ApiException(ResultStatusEnum.USER_IS_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new ApiException(ResultStatusEnum.USER_IS_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new ApiException(ResultStatusEnum.NOT_LOGIN_OR_TOKEN_OVERDUE);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new ApiException(ResultStatusEnum.USER_NEED_AUTHORITIES);
        }
        return securityUser;
    }

}
