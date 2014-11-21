package com.msg.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import com.msg.controller.event.GetMessageEvent;
import com.msg.controller.event.SendMessageEvent;
import com.msg.controller.event.UpdateMessageEvent;
import com.msg.domain.Message;

@Validated
public interface MessageService extends BaseService<Message>{

	void sendMessage(@Valid SendMessageEvent sendMessageEvent);

	Page<Object> getMessage(@Valid GetMessageEvent event);

	void updateMsg(@Valid UpdateMessageEvent event);

}
