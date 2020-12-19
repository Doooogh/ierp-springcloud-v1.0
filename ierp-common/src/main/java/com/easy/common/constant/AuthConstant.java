package com.easy.common.constant;

/**
 * 权限相关常量定义
 * Created by macro on 2020/6/19.
 */
public interface AuthConstant {

    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 后台管理client_id
     */
    String BACK_MANAGER_ID = "back-manager";

    /**
     * 前台client_id
     */
    String FRONT_MANAGER_ID = "front-manager";

    /**
     * 后台管理接口路径匹配
     */
    String BACK_MANAGER_URL_PATTERN = "/ierp-client/backManager/**";

    /**
     * 前台接口路径匹配
     **/

    String FRONT_MANAGER_URL_PATTERN = "/ierp-client/frontManager/**";

    String CLIENT_MANAGER_URL_PATTERN="/ierp-client";

    /**
     * Redis缓存接口资源规则key
     */
    String RESOURCE_INTERFACE_MAP_KEY = "auth:interfaceMap";

    /**
     * Redis缓存接口资源规则key
     */
    String RESOURCE_BUTTON_MAP_KEY = "auth:buttonMap";



    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 用户信息Http请求头
     */
    String USER_TOKEN_HEADER = "user";

}
