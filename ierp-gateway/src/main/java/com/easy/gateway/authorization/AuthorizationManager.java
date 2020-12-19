package com.easy.gateway.authorization;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.easy.common.commons.RedisOperationEnum;
import com.easy.common.constant.AuthConstant;
import com.easy.common.entity.auth.AuthUser;
import com.easy.common.service.RedisService;
import com.easy.gateway.config.IgnoreUrlsConfig;
import com.nimbusds.jose.JWSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 * Created by macro on 2020/6/19.
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private RedisService redisService;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;  //这个可以放在配置中心

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径直接放行
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        //对应跨域的预检请求直接放行
        if(request.getMethod()==HttpMethod.OPTIONS){
            return Mono.just(new AuthorizationDecision(true));
        }
        //不同用户体系登录不允许互相访问
        try {
            String token = request.getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
            if(StrUtil.isEmpty(token)){
                return Mono.just(new AuthorizationDecision(false));
            }
            String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
//            String realToken = token;
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            //token 转化为对象实体
            AuthUser authUser = JSONUtil.toBean(userStr, AuthUser.class);
            //判断请求路径和 对应的平台相对应 不能相互访问
            if(!checkClientAndUri(authUser,uri)){
                return Mono.just(new AuthorizationDecision(false));
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return Mono.just(new AuthorizationDecision(false));
        }
       /* //非前端或者后端路径直接放行
        if (!pathMatcher.match(AuthConstant.FRONT_MANAGER_URL_PATTERN, uri.getPath())) {
            return Mono.just(new AuthorizationDecision(true));
        }*/
        Map<String, Object> resourceRolesMap= (Map<String, Object>) redisService.get(AuthConstant.RESOURCE_INTERFACE_MAP_KEY, RedisOperationEnum.MAP_OPERATION.getMethod());
//        Map<Object, Object> resourceRolesMap =redisTemplate.opsForHash().entries("ierpCached-"+AuthConstant.RESOURCE_INTERFACE_MAP_KEY);
//        CommonResult<Map<String,String>> interfaceMapResult = redisService.get(AuthConstant.RESOURCE_INTERFACE_MAP_KEY, RedisOperationEnum.MAP_OPERATION.getMethod());
//        Map<String, String> resourceRolesMap = interfaceMapResult.getData();
        //前后端路径需校验权限
//        Map<Object, Object> resourceRolesMap = new HashMap<>();
//        Map<Object, Object> resourceRolesMap = redisTemplate.opsForHash().entries(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        //对resourceRolesMap 进行判空， 如果为空进行 请求


        Iterator<String> iterator = resourceRolesMap.keySet().iterator();
        //url 访问路径需要的permissionCode 集合 一般只有该list的size 为0
        List<String> needRoles = new ArrayList<>();
        while (iterator.hasNext()) {
            String roleOfUrl = iterator.next();
            if (pathMatcher.match(AuthConstant.CLIENT_MANAGER_URL_PATTERN+roleOfUrl, uri.getPath())) {
                //如果该url需要的permissionCode 是多个 那需要将全部的如果该url需要的permissionCode  加到list中
                needRoles.addAll(Convert.toList(String.class, resourceRolesMap.get(roleOfUrl)));
            }
        }

        //该url 需要的角色集合
        needRoles = needRoles.stream().map(i -> i = AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        //认证通过且角色匹配的用户可访问当前路径

        if(needRoles.size()==0){
            return  Mono.just(new AuthorizationDecision(true));
        }

        return mono
                //已经认证通过
                .filter(Authentication::isAuthenticated)
                //获取该用户所有的permissionCode
                .flatMapIterable(Authentication::getAuthorities)
                .doOnEach(item->{
                    System.out.println(item);
                })
                //遍历集合 将集合中的对象  获取改对象的权限
                .map(GrantedAuthority::getAuthority)
                .any(needRoles::contains)
                .map(AuthorizationDecision::new)
                //如果在完成时没有发出任何通知，那么发出给定的值  在这里相当于不给验证通过
                .defaultIfEmpty(new AuthorizationDecision(true));
    }

    /**
     * @Author li long
     * @Description --判断 请求 路径和平台是否对应
     * @Date 2020/9/7 10:39
     * @Param [mono, authUser]
     * @return void
     **/
    private boolean checkClientAndUri(AuthUser authUser, URI uri){
        PathMatcher pathMatcher = new AntPathMatcher();
        //如果不是前台对应的路径
        if (AuthConstant.FRONT_MANAGER_ID.equals(authUser.getClientId()) && !pathMatcher.match(AuthConstant.FRONT_MANAGER_URL_PATTERN, uri.getPath())) {
            return false;
        }

        //如果不是后台对应的路径
        if (AuthConstant.BACK_MANAGER_ID.equals(authUser.getClientId()) && pathMatcher.match(AuthConstant.FRONT_MANAGER_URL_PATTERN, uri.getPath())) {
            return false;
        }
        return true;

    }

}
