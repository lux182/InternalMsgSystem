package com.msg.event;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.msg.enums.MessageType;
import com.msg.enums.SendChannel;
import com.msg.utils.SystemMessage.Hint;
import com.msg.validator.Email;
import com.msg.validator.PhoneNumber;

public class SendMessageEvent {
	
	@NotNull(message=Hint.SEND_CHANNEL_COULD_NOT_BE_NULL)
	private SendChannel chanel;
	
	@NotEmpty(message=Hint.MESSAGE_CONTENT_COULD_NOT_BE_EMPTY)
	private String content;
	
	@Email(emptyable=true)
	private String email;
	
	private Date indate;	
	private int indateDay;
	
	private boolean persistence=false;
	
	@PhoneNumber(emptyable=true)
	private String phone;
	
	private Long recId;
	@NotEmpty(message=Hint.SENDERNAME_COULD_NOT_BE_NULL)
	private String senderName;	
	@NotNull(message=Hint.SENDID_COULD_NOT_BE_NULL)
	private Long sendId;
	private boolean timeLimit=false;

	@NotEmpty(message=Hint.MESSAGE_TITLE_COULD_NOT_BE_EMPTY)
	private String title;
	
	private MessageType type = MessageType.PUBLIC;
	private String url;
	
	public SendChannel getChanel() {
		return chanel;
	}
	public String getContent() {
		return content;
	}
	public String getEmail() {
		return email;
	}
	
	public Date getIndate() {
		if(this.timeLimit){
			long time = System.currentTimeMillis()+this.getIndateDay()*24*60*60*1000;
			this.indate=new Date(time);
			return null;
		}
		return indate;
	}
	
	public int getIndateDay() {
		return indateDay;
	}
	public String getPhone() {
		return phone;
	}	
	public Long getRecId() {
		return recId;
	}
	public String getSenderName() {
		return senderName;
	}
	public Long getSendId() {
		return sendId;
	}
	public String getTitle() {
		return title;
	}
	public MessageType getType() {
		return type;
	}
	public String getUrl() {
		return url;
	}
	public boolean isPersistence() {
		return persistence;
	}
	public boolean isTimeLimit() {
		return timeLimit;
	}
	public void setChanel(SendChannel chanel) {
		this.chanel = chanel;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	public void setIndateDay(int indateDay) {
		this.indateDay = indateDay;
	}
	public void setPersistence(boolean persistence) {
		this.persistence = persistence;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setRecId(Long recId) {
		this.recId = recId;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public void setSendId(Long sendId) {
		this.sendId = sendId;
	}
	public void setTimeLimit(boolean timeLimit) {
		this.timeLimit = timeLimit;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
