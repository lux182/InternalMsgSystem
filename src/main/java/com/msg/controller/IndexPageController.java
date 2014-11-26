package com.msg.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msg.service.AdminService;
import com.msg.service.SecurityService;
import com.msg.utils.Views;

@Controller
@RequestMapping("/")
public class IndexPageController {
    
	@Resource
	AdminService adminService;
	
	@Resource
	SecurityService securityService;
	
	@RequestMapping
	public String indexPageView(){
		if(securityService.isAccessAuth()){
			return Views.REDIRECT_MANAGER_INDEX_CONTROLLER;
		}
		return Views.SITE_INDEX_PAGE;
	}
	
	@RequestMapping("logout")
	public String logout(){
		securityService.logout();
		return Views.REDIRECT_MANAGER_INDEX_CONTROLLER;
	}
	
	@RequestMapping(value="init",method=RequestMethod.GET)
	@ResponseBody
	public String initAdmin(){
		adminService.createAdmin("admin", "d1anr0ng","管理员");
		return "OK";
	}
	
}
