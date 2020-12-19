package com.easy.common.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author li long
 * @Description --用户验证状态是否在指定范围内的注解
 * @Date 2020/9/15 15:34
 * @Param
 * @return
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = CusValidatorClass.class)
public @interface CusValidator {

    String[] value() default {};

    String message() default "没有找到预期设定的值";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
