package com.msg.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.msg.service.CfgService;
import com.msg.utils.Result;
import com.msg.utils.SystemMessage.Hint;

@Controller
@RequestMapping("cfg")
public class CfgController {

	@Resource
	CfgService cfgService;
	
	@RequestMapping(value="init",method=RequestMethod.GET)
	public Object init(){
		cfgService.initCfg();
		return Result.setMessage(Hint.CFG_INIT_SUCCESSFUL);
	}
}
