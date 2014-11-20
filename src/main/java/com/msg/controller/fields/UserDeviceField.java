package com.msg.controller.fields;

import javax.validation.constraints.NotNull;

import com.msg.event.BindDeviceEvent;
import com.msg.utils.SystemMessage.Submit;

public class UserDeviceField {
	
	@NotNull(message=Submit.AID_COULD_NOT_BE_NULL)
	private Long aid;
	
	@NotNull(message=Submit.USERID_COULD_NOT_BE_NULL)
	private String userId;
	
	@NotNull(message=Submit.CHANNELID_COULD_NOT_BE_NULL)
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

	public BindDeviceEvent toBindDeviceEvent() {
		return new BindDeviceEvent(this);
	}

}
