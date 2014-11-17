package com.msg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexPageController {
    
	@RequestMapping
	public String indexPageView(){
		return "Hello World!";
	}
	
}
