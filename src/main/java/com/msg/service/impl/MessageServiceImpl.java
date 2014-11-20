package com.msg.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msg.component.SendEngine;
import com.msg.domain.Message;
import com.msg.enums.SendChannel;
import com.msg.event.SendMessageEvent;
import com.msg.service.MessageService;

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService{
	
	@Override
	@Transactional
	public void sendMessage(SendMessageEvent event) {
		SendEngine.sendMessage(event);
		if(event.isPersistence()){
			event.setChanel(SendChannel.INNER);
			SendEngine.sendMessage(event);
		}
	}

}
