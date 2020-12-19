package com.easy.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/15 15:33
 * @Version 1.0
 **/
public class CusValidatorClass implements ConstraintValidator<CusValidator,Integer> {
    private String[] values;
    @Override
    public void initialize(CusValidator flagValidator) {
        this.values = flagValidator.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        if(value==null){
            //当状态为空时使用默认值
            return true;
        }
        for(int i=0;i<values.length;i++){
            if(values[i].equals(String.valueOf(value))){
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
