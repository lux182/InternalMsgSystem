package com.msg.component;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.msg.enums.SendChannel;
import com.msg.event.SendMessageEvent;
import com.msg.utils.NormalException;
import com.msg.utils.SystemMessage.Hint;

@Validated
public abstract class SendEngine {
		
	private static final Map<SendChannel,SendEngine> MAP = new HashMap<>();
		
	public abstract void send(@NotNull SendMessageEvent event);
	
	public void register(@NotNull SendChannel channel){
		SendEngine.MAP.put(channel, this);
	}
	
	public static void sendMessage(@NotNull SendMessageEvent event){
		if(SendEngine.MAP.get(event.getChanel())==null){
			throw new NormalException(Hint.NOT_SUPPORT_CHANNEL);
		}else{
			SendEngine.MAP.get(event.getChanel()).send(event);
		}
	}
}