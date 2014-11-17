package com.msg.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Result {

	private String message;
	private String redirectUrl;
	private Map<Object,Object> content=new HashMap<>();
	
	public static Result setMessage(String message) {
		return setMessage(message,null);
	}
	public static Result setMessage(String message,Object [] args) {
		message= StaticMethod.getMessage(message, args);
		return new Result(message);
	}
	
	private Result(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public String getRedirectUrl() {
        return redirectUrl;
    }
    public Result setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
        return this;
    }
    public Map<Object, Object> getContent() {
		return content;
	}
	public Result setContent(Map<Object, Object> content) {
		this.content = content;
		return this;
	}
	
	public Result addContent(Object k,Object v){
		this.content.put(k, v);
		return this;
	}
}
