package com.easy.common.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @Author li long
 * @Description --错误枚举
 * @Date 2020/9/15 15:24
 * @Param
 * @return
 **/
@Getter
@AllArgsConstructor
public enum ErrorEnum {
    NORMAL_ERROR("系统错误！"),
    REDIS_SET_ERROR("redis 存数据时错误");

    private String message;

}
