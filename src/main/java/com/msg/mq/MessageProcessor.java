package com.msg.mq;

public class MessageProcessor {

	public void execute(byte[] bytes){
		System.out.println(new String(bytes));
	}
}
