package com.msg.mq;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.msg.event.SendMessageEvent;
import com.msg.service.MessageService;
import com.msg.utils.StaticMethod;

@Component("messageProcessor")
public class MessageProcessor {

	private static final Logger logger = Logger.getLogger(MessageProcessor.class);
	@Resource
	MessageService messageService;
	
	public void execute(byte[] bytes){
		if(bytes==null){
			return;
		}
		try{
			String msg = new String(bytes,"UTF-8");
			System.out.println("# Recived MSG:"+msg);
			if(StringUtils.isEmpty(msg)){
				return;
			}
			SendMessageEvent event = JSON.toJavaObject(JSON.parseObject(msg), SendMessageEvent.class);
			messageService.sendMessage(event);
		}catch(Exception e){
			logger.debug(StaticMethod.getMessage(e.getMessage()), e);
		}
	}
}
