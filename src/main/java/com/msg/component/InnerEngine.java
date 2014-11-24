package com.msg.component;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.msg.domain.Message;
import com.msg.domain.MessageLog;
import com.msg.enums.MessageStatus;
import com.msg.enums.MessageType;
import com.msg.enums.SendChannel;
import com.msg.event.SendMessageEvent;
import com.msg.repo.MessageLogRepo;
import com.msg.repo.MessageRepo;
import com.msg.service.SecurityService;
import com.msg.utils.NormalException;
import com.msg.utils.SystemMessage.Hint;

@Component
public class InnerEngine extends SendEngine{

	@Resource
	SecurityService securityService;
	
	@Resource
	MessageRepo messageRepo;
	
	@Resource
	MessageLogRepo messageLogRepo;
	
	public InnerEngine() {
		this.register(SendChannel.INNER);
	}

	@Override
	@Transactional
	public void send(SendMessageEvent event) {
		
		if(event.isTimeLimit()){
			Date indate = event.getIndate();
			if(indate==null||System.currentTimeMillis()>=indate.getTime()){
				throw new NormalException(Hint.INDATE_ERROR);
			}
		}
		if(MessageType.PUBLIC.equals(event.getType())){
			
			Message message = new Message();
			message.setTitle(event.getTitle());
			message.setContent(event.getContent());
			message.setPubdate(new Date());
			message.setSenderName(event.getSenderName());
			message.setSendId(event.getSendId());
			message.setType(MessageType.PUBLIC);
			if(event.isTimeLimit()){
				message.setIndate(event.getIndate());
			}
			
			messageRepo.save(message);
			
		}else if(MessageType.PRIVATE.equals(event.getType())){
			
			
			Message message = new Message();
			message.setTitle(event.getTitle());
			message.setContent(event.getContent());
			message.setPubdate(new Date());
			message.setSenderName(event.getSenderName());
			message.setSendId(event.getSendId());
			message.setType(MessageType.PRIVATE);
			if(event.isTimeLimit()){
				message.setIndate(event.getIndate());
			}
			
			MessageLog messageLog = new MessageLog();
			messageLog.setRecId(event.getRecId());
			messageLog.setStatus(MessageStatus.UNREAD);
			messageLog.setMessage(message);
			messageRepo.save(message);
			messageLogRepo.save(messageLog);
		}
	}

	@Override
	public void initEngine() {
		
	}

}
