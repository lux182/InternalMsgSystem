package com.msg.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msg.service.CfgService;
import com.msg.utils.Result;
import com.msg.utils.SystemMessage.Hint;
import com.msg.utils.Views;

@Controller
@RequestMapping("cfg")
public class CfgController {

	@Resource
	CfgService cfgService;
	
	@RequestMapping(value="init",method=RequestMethod.GET)
	public String init(){
		cfgService.initCfg();
		return Views.REDIRECT_MANAGER_INDEX_CONTROLLER;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request){
		cfgService.update(request.getParameterMap());
		return Result.setMessage(Hint.CFG_UPDATE_SUCCESSFUL).setRedirectUrl("/");
	}
}
