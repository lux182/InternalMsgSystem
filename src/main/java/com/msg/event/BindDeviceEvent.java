package com.msg.event;

import javax.validation.constraints.NotNull;

import com.msg.utils.SystemMessage.Hint;

public class BindDeviceEvent {
	
	@NotNull(message=Hint.AID_COULD_NOT_BE_NULL)
	private Long aid;
	
	@NotNull(message=Hint.USERID_COULD_NOT_BE_NULL)
	private String userId;
	
	@NotNull(message=Hint.CHANNELID_COULD_NOT_BE_NULL)
	private Long channelId;

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
