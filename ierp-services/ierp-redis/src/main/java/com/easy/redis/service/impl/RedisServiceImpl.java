package com.easy.redis.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.easy.common.commons.ErrorEnum;
import com.easy.common.entity.redis.RedisDTO;
import com.easy.common.exception.ApiException;
import com.easy.redis.service.RedisService;
import com.easy.redis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/8 14:01
 * @Version 1.0
 **/

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {


    //默认过期时间 24小时
    @Value("${ierp.redis.expire}")
    private long expire;

    //key 前缀
    @Value("${ierp.redis.keyPerfix}")
    private  String keyPerfix;

    @Autowired
    private RedisUtil redisUtils;

    @Override
    public boolean set(RedisDTO redisDTO) {
        if(BeanUtil.isEmpty(redisDTO)){
            throw new ApiException(ErrorEnum.REDIS_SET_ERROR);
        }
        String key=redisDTO.getKey();
        Object object=redisDTO.getObject();
        long time=redisDTO.getTime();
        if(StrUtil.isEmpty(key)||BeanUtil.isEmpty(object)){
            throw new ApiException(ErrorEnum.REDIS_SET_ERROR);
        }
        key=keyPerfix+key;

        if(time==-1){
            time=expire;
        }
        System.out.println("json str--------------------------");
       String operation=redisDTO.getOperation();
       if(BeanUtil.isEmpty(operation)){
           throw new ApiException(ErrorEnum.REDIS_SET_ERROR);
       }
        switch (operation){
            case  "normal":
                redisUtils.set(key,object,time);
                break;
            case"set":
                //将object转化为数组
                Object []objectArr=((List<Object>)object).toArray();
                redisUtils.sSet(key,objectArr,time);
                break; //可选
            case  "map":
                Map<String,Object> map= (Map<String, Object>) object;
                redisUtils.hmset(key,map,time);
                break;
            case  "list":
                List<Object> list= (List<Object>) object;
                redisUtils.lSet(key,list,time);
            default:
                throw new ApiException("存入redis时，没有该指定类型");
        }

        return true;

    }

    @Override
    public Object get(String key,String resultType) {

        Object object=new Object();

        key=keyPerfix+key;


        switch (resultType){
            case "normal":
                object=redisUtils.get(key);
                break;

            case "map":
                object=redisUtils.hmget(key);
                break;
            case "set":
                object=redisUtils.sGet(key);
                break;
            case "list":
                object=redisUtils.lGet(key);
                break;
            default:
                throw new ApiException("没有该返回类型:{"+resultType+"}");

        }
        return object;
    }

    @Override
    public String getString(String key) {
        return redisUtils.get(key).toString();
    }

    @Override
    public List<Object> getList(String key) {
        return redisUtils.lGet(key);
    }

    @Override
    public Set<Object> getSet(String key) {
        return redisUtils.sGet(key);
    }

    @Override
    public Map<String, Object> getMap(String key) {
        return (Map<String,Object>)redisUtils.hget(key,key);
    }

    @Override
    public boolean delete(String... keys) {
        redisUtils.del(keys);
        return true;
    }

    @Override
    public boolean delete(String key) {
        return redisUtils.del(key);
    }


    @Override
    public boolean setExpire(String key, long time) {
        return redisUtils.expire(key,time);
    }

    @Override
    public boolean hasKey(String key) {
        return redisUtils.hasKey(key);
    }

    @Override
    public boolean hHasKey(String key, String item) {
        return hasKey(key);
    }

    @Override
    public boolean sHasKey(String key, Object value) {
        return sHasKey(key,value);
    }

}
