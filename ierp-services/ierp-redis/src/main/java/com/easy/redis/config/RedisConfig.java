package com.easy.redis.config;


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/8 14:05
 * @Version 1.0
 **/
@Configuration
public class RedisConfig {





    @Bean("redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        setSerializer(template); //设置序列化工具，默认使用的是jdk序列化方式   如果操作的实体类没有实现Serializable接口就会报IllegalArgumentException错误
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }


    @Bean("stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate stringRedisTemplate=new StringRedisTemplate(factory);
        setSerializer(stringRedisTemplate);
        // 开启事务
        stringRedisTemplate.setEnableTransactionSupport(true);
        return stringRedisTemplate;
    }




    @Bean
    public FastJson2JsonRedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }


    @RefreshScope
    private void setSerializer(RedisTemplate template) {
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer( new StringRedisSerializer());
        template.setHashValueSerializer(fastJson2JsonRedisSerializer());
        template.setDefaultSerializer( fastJson2JsonRedisSerializer());
    }






}
