package com.msg.event;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.msg.controller.fields.MsgField;
import com.msg.enums.MessageType;
import com.msg.utils.DateFormat;

public class SendMessageEvent {
	
	private Long sendId;
	private String senderName;
	private String title;
	private String content;	
	private boolean timeLimit=false;
	private Date indate;
	private MessageType type = MessageType.PUBLIC;
	private Long recId;
	
	public SendMessageEvent(MsgField fields) {
		BeanUtils.copyProperties(fields, this);
		if(this.timeLimit){
			long time = System.currentTimeMillis()+fields.getIndateDay()*24*60*60*1000;
			time = (time/1000)*1000;
			this.indate=new Date(time);
		}
	}

	public Long getSendId() {
		return sendId;
	}

	public void setSendId(Long sendId) {
		this.sendId = sendId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

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

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public Long getRecId() {
		return recId;
	}

	public void setRecId(Long recId) {
		this.recId = recId;
	}
	
}
