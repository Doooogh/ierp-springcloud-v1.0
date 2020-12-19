package com.easy.common.entity.auth;

import com.easy.common.entity.system.SysRole;
import com.easy.common.entity.system.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 登录用户信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AuthUser {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private String clientId;
    private List<SysRole> roles;

    public AuthUser(Long id, String username, String password, Integer status, String clientId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.clientId = clientId;
    }

    public AuthUser(SysUser sysUser, List<SysRole> roles) {
        this.id=sysUser.getId();
        this.username=sysUser.getUsername();
        this.password=sysUser.getPassword();
        this.status=sysUser.getStatus();
        this.clientId=sysUser.getClientId();
        this.roles = roles;
    }






}
