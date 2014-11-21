package com.msg.controller.event;

import javax.validation.constraints.NotNull;

import com.msg.enums.MessageStatus;
import com.msg.enums.MessageType;
import com.msg.utils.SystemMessage.Hint;

public class GetMessageEvent {
	
	@NotNull(message=Hint.RECID_COULD_NOT_BE_NULL)
	private Long recId;
	
	private MessageStatus status;
	
	private MessageType type;
	
	private int page=0;
	private int pageSize=10;
	private String sort="pubdate";
	private String order = "DESC";
	
	public Long getRecId() {
		return recId;
	}
	public void setRecId(Long recId) {
		this.recId = recId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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
