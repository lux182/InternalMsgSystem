package com.msg.event;

import org.hibernate.validator.constraints.NotEmpty;

import com.msg.utils.SystemMessage.Hint;

public class LoginEvent {
	
	@NotEmpty(message=Hint.USERNAME_COULD_NOT_BE_EMPTY)
	private String username;
	
	@NotEmpty(message=Hint.PASSWORD_COULD_NOT_BE_EMPTY)
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
	
}
