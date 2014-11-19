package com.msg.controller.fields;

import org.hibernate.validator.constraints.NotEmpty;

import com.msg.enums.MessageType;
import com.msg.event.SendMessageEvent;
import com.msg.utils.SystemMessage.Submit;

public class MsgField {
	
	@NotEmpty(message=Submit.MESSAGE_TITLE_COULD_NOT_BE_EMPTY)
	private String title;
	
	@NotEmpty(message=Submit.MESSAGE_CONTENT_COULD_NOT_BE_EMPTY)
	private String content;
	
	private boolean timeLimit=false;
	private int indateDay;
	private MessageType type = MessageType.PUBLIC;
	private Long recId;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(boolean timeLimit) {
		this.timeLimit = timeLimit;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public MessageType getType() {
		return type;
	}
	public Long getRecId() {
		return recId;
	}
	public void setRecId(Long recId) {
		this.recId = recId;
	}
	
	public SendMessageEvent toSendMessageEvent(){
		return new SendMessageEvent(this);
	}
	public int getIndateDay() {
		return indateDay;
	}
	public void setIndateDay(int indateDay) {
		this.indateDay = indateDay;
	}
	
}
