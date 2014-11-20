package com.msg.component;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.msg.domain.Admin;
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
import com.msg.utils.StaticMethod;
import com.msg.utils.SystemMessage.Hint;
import com.msg.utils.SystemMessage.Submit;
import com.msg.utils.SystemName;

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
		if(StringUtils.isEmpty(event.getTitle())){
			event.setTitle(StaticMethod.getMessage(SystemName.DEFAULT_MESSAGE_TITLE));
		}
		if(StringUtils.isEmpty(event.getContent())){
			throw new NormalException(Submit.MESSAGE_CONTENT_COULD_NOT_BE_EMPTY);
		}
		if(event.isTimeLimit()){
			Date indate = event.getIndate();
			if(indate==null||System.currentTimeMillis()>=indate.getTime()){
				throw new NormalException(Submit.INDATE_ERROR);
			}
		}
		if(MessageType.PUBLIC.equals(event.getType())){
			Admin sender = securityService.getAuthedUser();		
			if(null == sender){
				sender = new Admin();
				sender.setId(0L);
				sender.setNickname(StaticMethod.getMessage(SystemName.ADMIN_SENDER_NAME));
			}
			Message message = new Message();
			message.setTitle(event.getTitle());
			message.setContent(event.getContent());
			message.setPubdate(new Date());
			message.setSenderName(sender.getNickname());
			message.setSendId(sender.getId());
			message.setType(MessageType.PUBLIC);
			if(event.isTimeLimit()){
				message.setIndate(event.getIndate());
			}
			
			messageRepo.save(message);
			
		}else if(MessageType.PRIVATE.equals(event.getType())){
			
			if(null == event.getSendId()||StringUtils.isEmpty(event.getSenderName())){
				Admin sender = securityService.getAuthedUser();
				if(sender==null){
					throw new NormalException(Hint.PRIVATE_MESSAGE_HAVE_TO_SET_SENDER);
				}else{
					event.setSendId(sender.getId());
					event.setSenderName(sender.getNickname());
				}
			}
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

}
