package com.msg.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msg.enums.MessageType;

@Entity
@DynamicInsert
@DynamicUpdate
public class Message {
	
	private Long id;
	private Long sendId;
	private String senderName;
	private String title;
	private String content;
	private Date pubdate;
	private Date indate;
	private MessageType type;
	private List<MessageLog> messageLog;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable=false)
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
	@Column(nullable=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	@Enumerated(EnumType.ORDINAL)
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy="message")
	public List<MessageLog> getMessageLog() {
		return messageLog;
	}
	public void setMessageLog(List<MessageLog> messageLog) {
		this.messageLog = messageLog;
	}
	
}
