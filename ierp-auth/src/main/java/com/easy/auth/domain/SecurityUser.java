package com.easy.auth.domain;

import com.easy.common.entity.auth.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 登录用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUser implements UserDetails {

    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态
     */
    private Boolean enabled;
    /**
     * 登录客户端ID
     */
    private String clientId;

//    //资源 权限集合
//    private List<String> permissionCodeList;



    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;



    //将个人权限code和 角色enName 初始化保存
    public SecurityUser(AuthUser authUser) {
        this.setId(authUser.getId());
        this.setUsername(authUser.getUsername());
        this.setPassword(authUser.getPassword());
        this.setEnabled(authUser.getStatus() == 1);
        this.setClientId(authUser.getClientId());

//        authUser.getPermissionCodeList().forEach(code->authorities.add(new SimpleGrantedAuthority(code)) );
//        this.setPermissionCodeList(authUser.getPermissionCodeList());
        if (authUser.getRoles() != null) {
            authorities = new ArrayList<>();
            authUser.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item.getEnName())));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
