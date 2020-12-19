package com.easy.common.exception;


import com.easy.common.commons.ErrorEnum;
import com.easy.common.commons.ResultStatusEnum;
import lombok.Data;

/**
 * @Author li long
 * @Description --
 * @Date 2020/9/9 14:43
 * @Param
 * @return
 **/

@Data
public class ApiException extends RuntimeException {

    private Integer code;



    public ApiException (Integer code,String message){
        super(message);
        this.code=code;
    }

    public ApiException(ErrorEnum errorEnum){
       this(errorEnum.getMessage());
    }


    public ApiException(ResultStatusEnum errorEnum){
        this(errorEnum.getMessage());
    }

    public ApiException(String message) {
        super(message);
        this.code = ResultStatusEnum.ERROR.getCode();
    }

}
