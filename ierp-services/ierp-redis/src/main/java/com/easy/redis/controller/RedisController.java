package com.easy.redis.controller;

import com.easy.common.commons.CommonResult;
import com.easy.common.entity.redis.RedisDTO;
import com.easy.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/8 15:14
 * @Version 1.0
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {


    @Autowired
    private RedisService redisService;

    @GetMapping("/test")
    public CommonResult test(){
        int a=1/0;
        return CommonResult.success();
    }

    @PostMapping("/save")
    public CommonResult save(@RequestBody RedisDTO redisDTO){
     redisService.set(redisDTO);
     return CommonResult.success();
    }





    @GetMapping("/get")
    public CommonResult get(@RequestParam(value = "key") String key,@RequestParam(value = "resultType") String resultType){
        return CommonResult.success(redisService.get(key,resultType));
    }


}
