package com.msg.component;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.msg.enums.SendChannel;
import com.msg.event.SendMessageEvent;

@Component
public class SmsEngine extends SendEngine{

	public SmsEngine() {
		this.register(SendChannel.SMS);
	}

	@Override
	public void send(SendMessageEvent event) {
		System.out.print("SMS");
	}

	@PostConstruct
	@Override
	public void initEngine() {
		
	}

}
