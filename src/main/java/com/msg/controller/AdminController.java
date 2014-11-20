package com.msg.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.msg.service.AdminService;
import com.msg.service.ViewService;
import com.msg.utils.Views;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Resource
	AdminService adminService;
	
	@Resource
	ViewService viewService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView createAdmin(){
		return viewService.model(Views.MANAGER_INDEX_PAGE).putCfgs().putMessage().build();
	}
}
