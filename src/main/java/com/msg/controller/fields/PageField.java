package com.msg.controller.fields;

import com.msg.event.PageEvent;

public class PageField {
	
	private int page=1;
	private int pageSize=10;
	private String sort="pubdate";
	private String order = "DESC";
	
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
	public PageEvent toPageEvent(Long recId) {
		return new PageEvent(this,recId);
	}
	
	
}
