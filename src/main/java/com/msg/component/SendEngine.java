package com.msg.component;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.msg.enums.SendChannel;
import com.msg.event.SendMessageEvent;

@Validated
public abstract class SendEngine {
		
	public static final Map<SendChannel,SendEngine> MAP = new HashMap<>();
		
	public abstract void send(@NotNull SendMessageEvent event);
	
	public void register(@NotNull SendChannel channel){
		SendEngine.MAP.put(channel, this);
	}
}