package com.msg.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import com.msg.domain.Message;
import com.msg.event.PageEvent;
import com.msg.event.SendMessageEvent;

@Validated
public interface MessageService extends BaseService<Message>{

	void sendMessage(@NotNull SendMessageEvent sendMessageEvent);

	Page<Object> getUnreadMsg(@NotNull PageEvent event);

	Page<Object> getReadMsg(@NotNull PageEvent pageEvent);

}
