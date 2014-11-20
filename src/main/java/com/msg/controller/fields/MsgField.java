package com.msg.controller.fields;

import org.hibernate.validator.constraints.NotEmpty;

import com.msg.enums.MessageType;
import com.msg.enums.SendChannel;
import com.msg.event.SendMessageEvent;
import com.msg.utils.SystemMessage.Submit;
import com.msg.validator.Email;
import com.msg.validator.PhoneNumber;

public class MsgField {
	
	private String title;
	
	@NotEmpty(message=Submit.MESSAGE_CONTENT_COULD_NOT_BE_EMPTY)
	private String content;
	
	private boolean timeLimit=false;
	private int indateDay;
	private MessageType type = MessageType.PUBLIC;
	private Long recId;
	
	@Email(emptyable=true)
	private String email;
	
	@PhoneNumber(emptyable=true)
	private String phone;
	
	private SendChannel chanel;
	
	private boolean persistence=false;
	
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public SendChannel getChanel() {
		return chanel;
	}
	public void setChanel(SendChannel chanel) {
		this.chanel = chanel;
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
	public boolean isPersistence() {
		return persistence;
	}
	public void setPersistence(boolean persistence) {
		this.persistence = persistence;
	}
	
}
