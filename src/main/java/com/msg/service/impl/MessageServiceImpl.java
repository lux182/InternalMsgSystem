package com.msg.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msg.component.SendEngine;
import com.msg.domain.Message;
import com.msg.enums.MessageStatus;
import com.msg.enums.SendChannel;
import com.msg.event.PageEvent;
import com.msg.event.SendMessageEvent;
import com.msg.repo.MessageRepo;
import com.msg.service.MessageService;

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService{
	
	@Resource
	MessageRepo messageRepo;
	
	@Override
	@Transactional
	public void sendMessage(SendMessageEvent event) {
		SendEngine.sendMessage(event);
		if(event.isPersistence()){
			event.setChanel(SendChannel.INNER);
			SendEngine.sendMessage(event);
		}
	}

	@Override
	public Page<Object> getUnreadMsg(PageEvent event) {
		Sort sort = new Sort(new Order(Direction.fromString(event.getOrder().toUpperCase()), event.getSort()));
		Pageable page = new PageRequest(event.getPage(),event.getPageSize(),sort);	
		return messageRepo.findUnreadMsg(page,event.getRecId(),MessageStatus.UNREAD,new Date());
	}

	@Override
	public Page<Object> getReadMsg(PageEvent event) {
		Sort sort = new Sort(new Order(Direction.fromString(event.getOrder().toUpperCase()), event.getSort()));
		Pageable page = new PageRequest(event.getPage(),event.getPageSize(),sort);	
		return messageRepo.findReadMsg(page,event.getRecId(),MessageStatus.READ,new Date());
	}

}
