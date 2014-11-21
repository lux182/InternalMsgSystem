package com.msg.component;

import org.springframework.stereotype.Component;

import com.msg.controller.event.SendMessageEvent;
import com.msg.enums.SendChannel;

@Component
public class EmailEngine extends SendEngine{

	public EmailEngine() {
		this.register(SendChannel.EMAIL);
	}

	@Override
	public void send(SendMessageEvent event) {
		System.out.print("Email");
	}

	@Override
	public void initEngine() {
		// TODO Auto-generated method stub
		
	}

}
