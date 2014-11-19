package com.msg.service;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.msg.domain.Admin;
import com.msg.event.LoginEvent;
import com.msg.utils.SystemMessage.Hint;

@Validated
public interface SecurityService {

	public void login(@NotNull LoginEvent loginEvent);

	public void logout();

	public boolean isAccessAuth();
	
	public Admin getAuthedUser();
	
}
