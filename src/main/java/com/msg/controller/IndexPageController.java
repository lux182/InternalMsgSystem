package com.msg.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.msg.service.SecurityService;
import com.msg.utils.Views;

@Controller
@RequestMapping("/")
public class IndexPageController {
    
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
	
}
