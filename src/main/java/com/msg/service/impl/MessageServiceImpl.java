package com.msg.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msg.component.SendEngine;
import com.msg.domain.Message;
import com.msg.domain.MessageLog;
import com.msg.enums.MessageStatus;
import com.msg.enums.SendChannel;
import com.msg.event.GetMessageEvent;
import com.msg.event.SendMessageEvent;
import com.msg.event.UpdateMessageEvent;
import com.msg.repo.MessageLogRepo;
import com.msg.repo.MessageRepo;
import com.msg.service.MessageService;
import com.msg.utils.NormalException;
import com.msg.utils.SystemMessage.Hint;

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService{
	
	@Resource
	MessageRepo messageRepo;
	@Resource 
	MessageLogRepo messageLogRepo;
	
	@Override
	@Transactional
	public void sendMessage(SendMessageEvent event) {
		SendEngine.sendMessage(event);
		if(event.isPersistence()){
			event.setChannel(SendChannel.INNER);
			SendEngine.sendMessage(event);
		}
	}

	@Override
	public Page<Object> getMessage(GetMessageEvent event) {
		Sort sort = new Sort(Direction.fromString(event.getOrder()),event.getSort());
		Pageable page = new PageRequest(event.getPage(), event.getPageSize(),sort);
		if(event.getStatus()==null){
			return messageRepo.findAllMsg(page,event.getRecId(),event.getType());
		}else if(MessageStatus.UNREAD.equals(event.getStatus())){
			return messageRepo.findUnreadMsg(page,event.getRecId(),event.getStatus(),event.getType(),new Date());
		}else{
			return messageRepo.findReadMsg(page,event.getRecId(),event.getStatus(),event.getType());
		}		
	}

	@Override
	public void updateMsg(UpdateMessageEvent event) {
		Message msg = messageRepo.findById(event.getMsgId());
		if(msg==null){
			throw new NormalException(Hint.MESSAGE_HAS_NOT_EXIST);
		}
		MessageLog msglog = messageLogRepo.findByRecIdAndMessage(event.getRecId(), msg);
		if(msglog==null){
			msglog = new MessageLog();
			msglog.setRecId(event.getRecId());
			msglog.setMessage(msg);
		}
		msglog.setStatus(event.getStatus());
		messageLogRepo.save(msglog);
	}

}
