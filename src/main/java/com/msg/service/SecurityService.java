package com.msg.service;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.msg.controller.event.LoginEvent;
import com.msg.domain.Admin;

@Validated
public interface SecurityService {

	public void login(@Valid LoginEvent loginEvent);

	public void logout();

	public boolean isAccessAuth();
	
	public Admin getAuthedUser();
	
}
