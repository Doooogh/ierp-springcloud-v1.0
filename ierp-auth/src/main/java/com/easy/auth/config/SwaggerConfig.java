package com.easy.auth.config;

import com.easy.common.config.IerpSwaggerConfig;
import com.easy.common.entity.config.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author li long
 * @Description --Swagger API文档相关配置  用于单个服务 接口的说明
 * @Date 2020/9/16 15:27
 * @Param
 * @return
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig extends IerpSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.easy.auth.controller")
                .title("ierp-auth认证中心")
                .description("ierp-auth认证中心相关接口文档")
                .contactName("li")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
