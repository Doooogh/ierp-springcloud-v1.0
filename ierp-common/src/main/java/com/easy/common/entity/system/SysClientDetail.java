package com.easy.common.entity.system;

import lombok.Data;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/18 13:29
 * @Version 1.0
 **/
@Data
public class SysClientDetail {

    private String clientId;

    //秘钥
    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;
}
