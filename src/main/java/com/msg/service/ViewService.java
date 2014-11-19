package com.msg.service;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;

@Validated
public interface ViewService {
	
	ViewService model(String viewName);
	
	ModelAndView build();
	
	ViewService put(String key, Object value);
	
	ViewService putCfgs();

	ViewService putMessage();
}
