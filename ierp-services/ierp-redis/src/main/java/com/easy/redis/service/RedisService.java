package com.easy.redis.service;

import com.easy.common.entity.redis.RedisDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description redis 操作类
 * @Author li long
 * @Date 2020/9/8 14:01
 * @Version 1.0
 **/
public interface RedisService {


     boolean set(RedisDTO redisDTO);

     Object get(String key,String resultType);

     String getString(String key);

     List<Object> getList(String key);

     Set<Object> getSet(String key);

     Map<String,Object> getMap(String key);


     boolean delete(String ...key);

     boolean delete (String key);

     boolean setExpire(String key,long time);

//     判断key是否存在
     boolean hasKey(String key);


     //判断hash表中是否有该项的值
     boolean hHasKey(String key, String item);


     //根据value从一个set中查询,是否存在
     boolean sHasKey(String key,Object value);

}
