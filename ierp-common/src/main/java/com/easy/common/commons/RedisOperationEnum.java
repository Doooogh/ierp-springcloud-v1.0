package com.easy.common.commons;


import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @Author li long
 * @Description --redis操作类型枚举
 * @Date 2020/9/9 14:03
 * @Param
 * @return
 **/

@Getter
@AllArgsConstructor
public enum RedisOperationEnum {

    NORMAL_OPERATION(1,"normal","普通操作"),
    SET_OPERATION(2,"hash","SET操作"),
    LIST_OPERATION(3,"list","LIST 操作"),
    MAP_OPERATION(4,"map","MAP 操作"),
    HASH_OPERATION(5,"hash","HASH 操作");



    private Integer code;
    private String method;
    private String message;


}
