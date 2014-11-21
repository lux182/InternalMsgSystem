package com.msg.component;

import org.springframework.stereotype.Component;

import com.msg.controller.event.SendMessageEvent;
import com.msg.enums.SendChannel;

@Component
public class SmsEngine extends SendEngine{

	public SmsEngine() {
		this.register(SendChannel.SMS);
	}

	@Override
	public void send(SendMessageEvent event) {
		System.out.print("SMS");
	}

	@Override
	public void initEngine() {
		
	}

}
