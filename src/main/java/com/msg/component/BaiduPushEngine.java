package com.msg.component;

import org.springframework.stereotype.Component;

import com.msg.enums.SendChannel;
import com.msg.event.SendMessageEvent;

@Component
public class BaiduPushEngine extends SendEngine {

	
	public BaiduPushEngine() {
		this.register(SendChannel.BAIDU_PUSH);
	}

	@Override
	public void send(SendMessageEvent event) {
		
	}
	
}
