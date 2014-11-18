package com.msg.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msg.controller.fields.AdminField;
import com.msg.service.AdminService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Resource
	AdminService adminService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Object createAdmin(@Valid AdminField field){
		return adminService.createAdmin(field.getUsername(),field.getPassword());
	}
}
