package com.msg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.msg.enums.MessageStatus;

@Entity
@DynamicInsert
@DynamicUpdate
public class MessageLog {
	
	private Long id;
	private Long recId;
	private Message message;
	private MessageStatus status;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable=false)
	public Long getRecId() {
		return recId;
	}
	public void setRecId(Long recId) {
		this.recId = recId;
	}
	
	@ManyToOne
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public MessageStatus getStatus() {
		return status;
	}
	public void setStatus(MessageStatus status) {
		this.status = status;
	}
	
}
