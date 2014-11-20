package com.msg.event;

import org.springframework.beans.BeanUtils;

import com.msg.controller.fields.PageField;

public class PageEvent {
	private Long recId;
	private int page=1;
	private int pageSize=10;
	private String sort="id";
	private String order = "DESC";
	
	public PageEvent(PageField field, Long recId) {
		BeanUtils.copyProperties(field, this);
		this.recId=recId;
	}
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
	
}
