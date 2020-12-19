package com.easy.system.config;

import com.easy.common.config.IerpSwaggerConfig;
import com.easy.common.entity.config.SwaggerProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
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
@EnableKnife4j
public class SwaggerConfig extends IerpSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.easy.system.controller")
                .title("ierp-system接口文档")
                .description("系统类型接口")
                .contactName("li")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
