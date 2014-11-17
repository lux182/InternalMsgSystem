package com.msg.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by leoyang on 14-7-22.
 */
public class NormalException extends RuntimeException {

	private static final long serialVersionUID = 1266597584282388563L;
	
	private Object[] args = null;
	
	private Map<Object,Object> content=new HashMap<>();
	
	public NormalException(String message) {
		super(message);
	}
	
	public NormalException(String message,Object[] args) {
		super(message);
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Map<Object, Object> getContent() {
		return content;
	}
	public void setContent(Map<Object, Object> content) {
		this.content = content;
	}
	
	public NormalException addContent(Object k,Object v){
		this.content.put(k, v);
		return this;
	}
}
