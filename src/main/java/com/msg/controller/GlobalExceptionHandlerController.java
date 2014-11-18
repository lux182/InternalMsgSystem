package com.msg.controller;

import static com.msg.utils.StaticMethod.getMessage;

import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.msg.utils.NormalException;
import com.msg.utils.Result;

@ControllerAdvice
public class GlobalExceptionHandlerController {
	private static final Logger logger = Logger.getLogger(GlobalExceptionHandlerController.class);

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object processBeanValidationError(BindException ex) {
		logger.info("BindException Error", ex);
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		FieldError error = errors.get(0);
		for(FieldError e : errors){
			if(e.getDefaultMessage().length()<error.getDefaultMessage().length()){
				error=e;
			}
		}
		return Result.setMessage(error.getDefaultMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object processMethodValidationError(ConstraintViolationException ex) {
		logger.info("ConstraintViolationException Error", ex);
		String msg=null;
		for(ConstraintViolation<?> e:ex.getConstraintViolations()){
			msg=getMessage(e.getMessageTemplate());
			break;
		}
		return  Result.setMessage(msg);
	}

	@ExceptionHandler(NormalException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object processNormalError(NormalException ex) {
		return Result.setMessage(ex.getMessage(),ex.getArgs()).setContent(ex.getContent());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND )
	@ResponseBody
	public Object processMethodNotSupportedError(HttpRequestMethodNotSupportedException ex) {
		return null;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object processUnexpectedError(Exception ex) {
		logger.info("Unexpected Exception Error", ex);
		return ex;
	}

}
