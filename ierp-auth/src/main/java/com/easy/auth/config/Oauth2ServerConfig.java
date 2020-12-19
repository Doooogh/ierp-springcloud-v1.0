package com.easy.auth.config;

import com.easy.auth.component.CusAuthenticationEntryPoint;
import com.easy.auth.component.JwtTokenEnhancer;
import com.easy.auth.filter.CustomClientCredentialsTokenEndpointFilter;
import com.easy.auth.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务器配置
 * Created by macro on 2020/6/19.
 */
@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenEnhancer jwtTokenEnhancer;



    private final DataSource dataSource;



    /**
     * @Author li long
     * @Description --  配置客户端详细服务  给谁发令牌
     * @Date 2020/9/5 12:59
     * @Param [clients]
     * @return void
     **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*clients.inMemory()
                .withClient("back-manager")  //后端管理
                .secret(passwordEncoder.encode("xcyg1234"))  //秘钥
                .scopes("all")
                .authorizedGrantTypes("password", "refresh_token") //  //密码授权模式和刷新令牌
                .accessTokenValiditySeconds(3600*24)  //token有效期设置2个小时
                .refreshTokenValiditySeconds(3600*24*7)   //Refresh_token
                .and()
                .withClient("front-manager")  //前端页面
                .secret(passwordEncoder.encode("xcyg1234"))
                .scopes("all")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600*24)
                .refreshTokenValiditySeconds(3600*24*7);
        clients.jdbc(dataSource);*/
        //配置客户端存储到db
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        clients.withClientDetails(clientDetailsService);
    }


    //配置授权（authorization）以及令牌（token）的访问端点和令牌服务（token services）
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates); //配置JWT的内容增强器
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService) //配置加载用户信息的服务
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain);
    }

    //配置令牌端点（Token Endpoint）的安全约束
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        CustomClientCredentialsTokenEndpointFilter credentialsTokenEndpointFilter=new CustomClientCredentialsTokenEndpointFilter(security);
        credentialsTokenEndpointFilter.afterPropertiesSet();
        credentialsTokenEndpointFilter.setAuthenticationEntryPoint(cusAuthenticationEntryPoint());
        // 客户端认证之前的过滤器
        security.addTokenEndpointAuthenticationFilter(credentialsTokenEndpointFilter);
//        security.allowFormAuthenticationForClients();
    }

    @Bean
    public CusAuthenticationEntryPoint cusAuthenticationEntryPoint(){
        return new CusAuthenticationEntryPoint();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("ierp.jks"), "xcyg1234".toCharArray());
        return keyStoreKeyFactory.getKeyPair("ierp", "xcyg1234".toCharArray());
    }

}
