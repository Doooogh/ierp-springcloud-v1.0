package com.easy.redis.exception;

import cn.hutool.core.bean.BeanUtil;
import com.easy.common.commons.CommonResult;
import com.easy.common.commons.ResultStatusEnum;
import com.easy.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author li long
 * @Description -- 统一异常处理  已经进入到controller捕获的异常
 * @Date 2020/5/20 22:08
 * @Param
 * @return
 **/
@ControllerAdvice("com.easy.redis")
@Slf4j
public class GlobalExceptionHandler {


	@ExceptionHandler(value = ApiException.class)
	@ResponseBody
	public CommonResult exceptionHandler(ApiException exception){
		log.error("errorMessage:{"+exception.getMessage()+"}",exception);

		if(!BeanUtil.isEmpty(exception.getCode())){
			return CommonResult.error(exception.getCode(),exception.getMessage());
		}
		return CommonResult.error(ResultStatusEnum.ERROR.getCode(),exception.getMessage());
	}



	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public CommonResult exceptionHandler2(Exception exception){
		log.error("errorMessage:{"+exception.getMessage()+"}",exception);
		return CommonResult.errorOfRestltEnum(ResultStatusEnum.ERROR);
	}



}
