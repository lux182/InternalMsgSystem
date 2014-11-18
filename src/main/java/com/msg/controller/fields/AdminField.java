package com.msg.controller.fields;

import org.hibernate.validator.constraints.NotEmpty;

import com.msg.event.LoginEvent;
import com.msg.utils.SystemMessage.Submit;

public class AdminField {
	
	@NotEmpty(message=Submit.USERNAME_COULD_NOT_BE_EMPTY)
	private String username;
	
	@NotEmpty(message=Submit.PASSWORD_COULD_NOT_BE_EMPTY)
	private String password;
	
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
	
	public LoginEvent toEvent(){
		return new LoginEvent(this);
	}
	
}
