package com.easy.client.controller;

import com.easy.common.commons.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/10 11:25
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/testCon")
    public CommonResult test(){

        return CommonResult.success("testCon  result");

    }
}
