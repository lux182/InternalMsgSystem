package com.msg.controller.event;

import javax.validation.constraints.NotNull;

import com.msg.enums.MessageStatus;
import com.msg.enums.MessageType;
import com.msg.utils.SystemMessage.Hint;

public class UpdateMessageEvent {
	
	@NotNull(message=Hint.RECID_COULD_NOT_BE_NULL)
	private Long recId;
	@NotNull(message=Hint.MSGID_COULD_NOT_BE_NULL)
	private Long msgId;
	private MessageStatus status;
	private MessageType type;
	public Long getRecId() {
		return recId;
	}
	public void setRecId(Long recId) {
		this.recId = recId;
	}
	public Long getMsgId() {
		return msgId;
	}
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	public MessageStatus getStatus() {
		return status;
	}
	public void setStatus(MessageStatus status) {
		this.status = status;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	
}
