package com.msg.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msg.domain.Admin;
import com.msg.enums.MessageStatus;
import com.msg.enums.MessageType;
import com.msg.event.BindDeviceEvent;
import com.msg.event.GetMessageEvent;
import com.msg.event.SendMessageEvent;
import com.msg.event.UpdateMessageEvent;
import com.msg.service.MessageService;
import com.msg.service.SecurityService;
import com.msg.service.UserDeviceService;
import com.msg.utils.NormalException;
import com.msg.utils.Result;
import com.msg.utils.StaticMethod;
import com.msg.utils.SystemMessage.Hint;
import com.msg.utils.SystemName;

@Controller
@RequestMapping("api")
public class ApiController {
	
	@Resource
	MessageService messageService;
	
	@Resource
	SecurityService securityService;
	
	@Resource
	UserDeviceService userDeviceService;
	
	@RequestMapping(value="msg",method=RequestMethod.POST)
	@ResponseBody
	public Object sendInternalMsg(SendMessageEvent event){
		
		if(null == event.getSendId()||StringUtils.isEmpty(event.getSenderName())){
			Admin sender = securityService.getAuthedUser();
			if(MessageType.PUBLIC.equals(event.getType())){
				if(sender==null){
					sender=new Admin();
					sender.setId(0L);
					sender.setNickname(StaticMethod.getMessage(SystemName.ADMIN_SENDER_NAME));
				}
			}else{
				if(sender==null){
					throw new NormalException(Hint.PRIVATE_MESSAGE_HAVE_TO_SET_SENDER);
				}
			}
			event.setSendId(sender.getId());
			event.setSenderName(sender.getNickname());
		}
		
		if(MessageType.PRIVATE.equals(event.getType())){
			if(event.getRecId()==null){
				throw new NormalException(Hint.RECID_COULD_NOT_BE_NULL);
			}
		}
		
		if(StringUtils.isEmpty(event.getTitle())){
			event.setTitle(StaticMethod.getMessage(SystemName.DEFAULT_MESSAGE_TITLE));
		}
		
		messageService.sendMessage(event);
		return Result.setMessage(Hint.MESSAGE_HAS_SEND);
	}
	@RequestMapping(value="{recId}/msg",method=RequestMethod.GET)
	@ResponseBody
	public Object getMsg(@PathVariable Long recId,GetMessageEvent event){
		event.setRecId(recId);
		return messageService.getMessage(event);
	}
	@RequestMapping(value="{recId}/{msgId}",method=RequestMethod.PUT)
	@ResponseBody
	public Object updateMsg(@PathVariable Long recId,@PathVariable Long msgId,UpdateMessageEvent event){
		event.setRecId(recId);
		event.setMsgId(msgId);
		messageService.updateMsg(event);
		return Result.setMessage(Hint.MESSAGE_HAS_BEEN_UPDATED);
	}
	@RequestMapping(value="{recId}/{msgId}",method=RequestMethod.DELETE)
	@ResponseBody
	public Object deleteMsg(@PathVariable Long recId,@PathVariable Long msgId){	
		UpdateMessageEvent event = new UpdateMessageEvent();
		event.setRecId(recId);
		event.setMsgId(msgId);
		event.setStatus(MessageStatus.DELETE);
		messageService.updateMsg(event);
		return Result.setMessage(Hint.MESSAGE_HAS_BEEN_DELETED);
	}
	@RequestMapping(value="userDevice",method=RequestMethod.POST)
	@ResponseBody
	public Object sendInternalMsg(BindDeviceEvent event){
		userDeviceService.bindUserDevice(event);
		return Result.setMessage(Hint.MESSAGE_HAS_SEND);
	}
}
