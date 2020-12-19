package com.easy.redis.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @author : XCYG
 */

@Service
@Slf4j
public class RedisStringUtils {
    @Autowired
    private StringRedisTemplate redisStringTemplate;

    /*@Resource
    private RedisTemplate<String, Object> redisTemplate;*/





    public boolean exists(String key) {
        return this.redisStringTemplate.hasKey(key);
    }

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key,long time){
        try {
            if(time>0){
                redisStringTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * @Author li long
     * @Description --获取失效时间
     * @Date 2020/7/1 13:10
     * @Param [key]
     * @return java.lang.Long
     **/
    public Long getExpire(String key) {
        if (null == key) {
        }
        return redisStringTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    public void set(String key, String value) {
        this.redisStringTemplate.opsForValue().set(key, value);
    }


    public String get(String key) {
        String value = this.redisStringTemplate.opsForValue().get(key);
        return value;
    }


    public void del(String key) {
        if (this.exists(key)) {
            this.redisStringTemplate.delete(key);
        }

    }

    /**
     * 删除，根据key精确匹配
     *
     * @param key
     */
    public  void del(final String... key) {
        if(key!=null&&key.length>0){
            for (int i=0;i<key.length;i++){
                key[i]=key[i];
            }
        }
        redisStringTemplate.delete(Arrays.asList(key));
    }

    /**
     * 批量删除，根据key模糊匹配
     *
     * @param pattern
     */
    public  void delpn(final String... pattern) {
        for (String kp : pattern) {
            redisStringTemplate.delete(redisStringTemplate.keys(kp + "*"));
        }
    }

    public void setAndExpire(String key, String value, long seconds) {
        this.redisStringTemplate.opsForValue().set(key, value);
        this.redisStringTemplate.expire(key, (long) seconds, TimeUnit.SECONDS);
    }


    public Set keys(String pattern) {
        return redisStringTemplate.keys("*" + pattern);
    }

    public void delKeys(String pattern) {
        Set<String> keys = redisStringTemplate.keys(pattern);
        if (!CollectionUtils.isEmpty(keys)) {
            this.redisStringTemplate.delete(keys);
        }
    }


    public long incrby(String key, long increment) {
        if (null == key) {

        }
        return redisStringTemplate.opsForValue().increment(key, increment);
    }


    //===============================object=================================

    public void setValue(String key,Object value){
        set(key, JSON.toJSONString(value));
    }

    public void setValue(String key,Object value,long time){
        setValue(key,value);
        expire(key,time);
    }

    public Object getValue(String key){
        return JSON.parse(redisStringTemplate.opsForValue().get(key));
    }



    public <T> T getValue(String key,Class<T> cls){
        try{
            String valueStr = get(key);
            if(StringUtils.isEmpty(valueStr)){
                return null;
            }
            return JSON.parseObject(valueStr,cls);
        }catch (Exception e){
            log.error("获取redis中数据时出错，key为："+key);
            return null;
        }
    }



    //===============================list=================================

    public void setList(String key,List list){
        set(key,JSON.toJSONString(list));
    }

    public void setList(String key,List list,long time){
        try{

            setAndExpire(key,JSON.toJSONString(list),time);
        }catch (Exception e){
            log.error("存入缓存时出错，key:"+key+"-------------error:"+e.getMessage());
        }
    }


    public <T>List<T> getList(String key,Class<T> tClass){
        String valueStr = get(key);
        if(StringUtils.isEmpty(key)){
            return null;
        }
        try{
            return JSON.parseArray(valueStr, tClass);
        }catch (Exception e){
            log.error("解析redis中list时出错，key:"+key+",_________error:"+e.getMessage());
            return null;
        }
    }

    public List<Object> getList(String key){
        List<Object> list=new ArrayList<>();
        String valueStr = get(key);
        if(StringUtils.isEmpty(key)){
            return null;
        }
        try{
            JSONArray objects = JSON.parseArray(valueStr);
            for (Object object : objects) {
                list.add(object);
            }
        }catch (Exception e){
            log.error("解析redis中list时出错，key:"+key+",_________error:"+e.getMessage());
            return null;
        }
        return list;
    }

    public int getListSize(String key){
        try{
            return getList(key).size();
        }catch (Exception e){
            log.error("获取缓存list长度时出错,key:"+key);
            return 0;
        }
    }


}
