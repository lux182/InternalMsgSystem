package com.msg.event;

import org.springframework.beans.BeanUtils;

import com.msg.controller.fields.AdminField;

public class LoginEvent {
	
	private String username;
	private String password;
	
	public LoginEvent(AdminField field) {
		BeanUtils.copyProperties(field, this);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
