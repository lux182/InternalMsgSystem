package com.msg.service;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.msg.domain.Message;
import com.msg.event.SendMessageEvent;

@Validated
public interface MessageService extends BaseService<Message>{

	void sendMessage(@NotNull SendMessageEvent sendMessageEvent);

}
