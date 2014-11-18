package com.msg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
public class CfgDomain {
	
	private Long id;
	private String cfgKey;
	private String cfgVal;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable=false,unique=true)
	public String getCfgKey() {
		return cfgKey;
	}
	public void setCfgKey(String cfgKey) {
		this.cfgKey = cfgKey;
	}
	
	@Column(nullable=false)
	public String getCfgVal() {
		return cfgVal;
	}
	public void setCfgVal(String cfgVal) {
		this.cfgVal = cfgVal;
	}
	
}
