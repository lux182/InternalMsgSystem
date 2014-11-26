package com.msg.component;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.msg.enums.SendChannel;
import com.msg.event.SendMessageEvent;

@Component
public class EmailEngine extends SendEngine{

	public EmailEngine() {
		this.register(SendChannel.EMAIL);
	}

	@Override
	public void send(SendMessageEvent event) {
		System.out.print("Email");
	}

	@PostConstruct
	@Override
	public void initEngine() {
		// TODO Auto-generated method stub
		
	}

}
