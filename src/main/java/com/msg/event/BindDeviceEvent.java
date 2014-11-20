package com.msg.event;

import org.springframework.beans.BeanUtils;

import com.msg.controller.fields.UserDeviceField;

public class BindDeviceEvent {
	private Long aid;
	private String userId;
	private Long channelId;
	
	
	public BindDeviceEvent(UserDeviceField fields) {
		BeanUtils.copyProperties(fields, this);
	}

	public Long getAid() {
		return aid;
	}
	public void setAid(Long aid) {
		this.aid = aid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	
	
}
