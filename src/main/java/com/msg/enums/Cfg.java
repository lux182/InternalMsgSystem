package com.msg.enums;

public enum Cfg {
	//Rabbit mq
	SL_RABBIT_MQ_EXCHANGE_NAME,
	SL_RABBIT_MQ_SERVER_HOST,
	SL_RABBIT_MQ_SERVER_HOST_PORT,
	SL_RABBIT_MQ_USER_NAME,
	SL_RABBIT_MQ_PASSWORD,
	SL_RABBIT_MQ_VHOST,
	SL_RABBIT_MQ_IS_ENABLED,
	
	//Baidu push serivce
	LC_BAIDU_PUSH_API_KEY,
	LC_BAIDU_PUSH_SECRET_KEY
	;
	private String value;

	public String getValue() {
		
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
