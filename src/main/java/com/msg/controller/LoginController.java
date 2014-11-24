package com.msg.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msg.event.LoginEvent;
import com.msg.service.SecurityService;
import com.msg.utils.Result;
import com.msg.utils.SystemMessage.Hint;

@Controller
@RequestMapping("login")
public class LoginController {

	@Resource
	SecurityService securityService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Object doLogin(LoginEvent event){
		securityService.login(event);
		return Result.setMessage(Hint.LOGIN_SUCCESSFUL).setRedirectUrl("/");
	}
}
