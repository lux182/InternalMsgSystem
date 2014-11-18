package com.msg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.msg.utils.Views;

@Controller
@RequestMapping("/")
public class IndexPageController {
    
	@RequestMapping
	public String indexPageView(){
		return Views.SITE_INDEX_PAGE;
	}
	
}
