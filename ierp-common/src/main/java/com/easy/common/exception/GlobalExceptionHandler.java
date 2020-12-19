package com.easy.common.exception;

import cn.hutool.core.bean.BeanUtil;
import com.easy.common.commons.CommonResult;
import com.easy.common.commons.ResultStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;


/**
 * @Author li long
 * @Description -- 统一异常处理  已经进入到controller捕获的异常
 * @Date 2020/5/20 22:08
 * @Param
 * @return
 **/
@ControllerAdvice("com.easy")
@Slf4j
@RestController
public class GlobalExceptionHandler {


	@ExceptionHandler(value = ApiException.class)
	public CommonResult exceptionHandler(ApiException exception){
		log.error("系统错误！错误信息:{ "+exception.getMessage()+"}",exception);
		if(!BeanUtil.isEmpty(exception.getCode())){
			return CommonResult.error(exception.getCode(),exception.getMessage());
		}
		return CommonResult.error(ResultStatusEnum.ERROR.getCode(),exception.getMessage());
	}





	/**
	 * 参数校验/转换错误
	 *
	 */
	@ExceptionHandler(Exception.class)
	public CommonResult<String> allExceptionHandler(Exception exception){
		exception.printStackTrace();
		 if(exception instanceof HttpMessageNotReadableException){
			log.error("参数校验错误:{ 请求数据错误}");
			return CommonResult.errorOfMessage("参数校验错误:{ 请求数据错误}");
		}else if(exception instanceof HttpMessageConversionException){  //转化错误
			log.error("参数类型转化错误:{ 原因:"+exception.getMessage()+"}");
			return CommonResult.errorOfMessage("参数类型转化错误:{ 原因:"+exception.getMessage()+"}");
		}else if(exception instanceof UnexpectedTypeException){  //类型不对应
			log.error("参数类型转化错误:{ 原因:"+exception.getMessage()+"}");
			return CommonResult.errorOfMessage("参数类型转化错误:{ 原因:"+exception.getMessage()+"}");
		}else if(exception instanceof MethodArgumentNotValidException){ //校验失败
			MethodArgumentNotValidException exception1=(MethodArgumentNotValidException)exception;
			BindingResult bindingResult = exception1.getBindingResult();
			//返回第一个错误的信息
			String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
			String code = bindingResult.getFieldError().getCode();
			String field = bindingResult.getFieldError().getField();
			log.error("参数校验错误:{ 参数:"+field+",原因:"+defaultMessage+"}");
			return CommonResult.errorOfMessage("参数校验错误:{ 参数:"+field+",原因:"+defaultMessage+"}");
		}else if(exception instanceof MissingServletRequestParameterException){
			MissingServletRequestParameterException exception1= (MissingServletRequestParameterException) exception;
			log.error("请求参数 " + exception1.getParameterName() + " 不能为空");
			return CommonResult.errorOfMessage("请求参数 " + exception1.getParameterName() + " 不能为空");
		}else if(exception instanceof BindException){  //参数绑定错误
			 BindException exception1=(BindException)exception;
			 BindingResult bindingResult = exception1.getBindingResult();
			 //返回第一个错误的信息
			 String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
			 String code = bindingResult.getFieldError().getCode();
			 String field = bindingResult.getFieldError().getField();
			 log.error("参数校验错误:{ 参数:"+field+",原因:"+defaultMessage+"}");
			 return CommonResult.errorOfMessage("参数校验错误:{ 参数:"+field+",原因:"+defaultMessage+"}");
		 }else if(exception instanceof ConstraintViolationException){
		 	ConstraintViolationException exception1= (ConstraintViolationException) exception;
		 	String errorMsg="参数校验错误:{"+exception1.getLocalizedMessage();
			 errorMsg+=("}");
			 log.error(errorMsg);
			 return CommonResult.errorOfMessage(errorMsg);
		 }
		log.error("系统错误！错误信息:{ "+exception.getMessage()+"}",exception);
		return CommonResult.errorOfRestltEnum(ResultStatusEnum.ERROR);

	}

	/*@ExceptionHandler(value = {Exception.class})
	public CommonResult exceptionHandler2(Exception exception){
		log.error("系统错误！错误信息:{ "+exception.getMessage()+"}",exception);
		return CommonResult.errorOfRestltEnum(ResultStatusEnum.ERROR);
	}*/







}
