package com.msg.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msg.controller.fields.MsgField;
import com.msg.service.MessageService;
import com.msg.utils.Result;
import com.msg.utils.SystemMessage.Hint;

@Controller
@RequestMapping("api")
public class ApiController {
	
	@Resource
	MessageService messageService;
	
	@RequestMapping(value="msg",method=RequestMethod.POST)
	@ResponseBody
	public Object sendInternalMsg(@Valid MsgField fields){
		messageService.sendMessage(fields.toSendMessageEvent());
		return Result.setMessage(Hint.INTERNAL_MESSAGE_HAS_SEND);
	}
}
