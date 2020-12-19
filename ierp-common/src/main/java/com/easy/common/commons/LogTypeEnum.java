package com.easy.common.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogTypeEnum {

//    ERROR



    API_PARAM_ERROR(101,"api","");

    private Integer code;
    private String typeCode;
    private String message;
}
