package com.easy.common.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 返回实体封装类
 * @Author li long
 * @Date 2020/7/28 12:25
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>{
    private Integer code;
    private String message;
    private T data;



    private static <T> CommonResult getInstance(Integer code, String message, T data){
        return new CommonResult(code,message,data);
    }

    private static <T> CommonResult getErrorInstance(Integer code, String message){
        return  getInstance(code,message,null);
    }


    public static <T> CommonResult success(){
        return new CommonResult(ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage(),null);
    }

    public static <T> CommonResult success(T data){
        return new CommonResult(ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage(),data);
    }
    public static <T> CommonResult successOfRestltEnum(ResultStatusEnum resultStatusEnum, T data){
        return new CommonResult(resultStatusEnum.getCode(), resultStatusEnum.getMessage(),data);
    }

    public static <T> CommonResult error(){
        return getErrorInstance(ResultStatusEnum.ERROR.getCode(), ResultStatusEnum.ERROR.getMessage());
    }

    public static <T> CommonResult error(Integer code,String message){
        return getErrorInstance(code, message);
    }

    public static <T> CommonResult errorOfRestltEnum(ResultStatusEnum resultStatusEnum){
        return getErrorInstance(resultStatusEnum.getCode(), resultStatusEnum.getMessage());
    }

    public static <T> CommonResult errorOfMessage(String message){
        return getErrorInstance(ResultStatusEnum.ERROR.getCode(),message);
    }

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



}
