package com.msg.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.msg.controller.fields.AdminField;
import com.msg.service.SecurityService;
import com.msg.utils.Views;

@Controller
@RequestMapping("login")
public class LoginController {

	@Resource
	SecurityService securityService;
	
	@RequestMapping(method=RequestMethod.POST)
	public String doLogin(@Valid AdminField field){
		securityService.login(field.toEvent());
		return Views.MANAGER_INDEX_PAGE;
	}
}
