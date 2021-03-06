package com.msg.enums;

import java.util.ArrayList;
import java.util.List;

public enum Cfg {
	SESSIONINFO_URL,
	//Baidu push serivce
	BAIDU_PUSH_API_KEY,
	BAIDU_PUSH_SECRET_KEY
	;
	private String value;

	public String getValue() {
		
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static List<String> names(){
		ArrayList<String> cfgNames = new ArrayList<String>();
		for(Cfg cfg : Cfg.values()){
			cfgNames.add(cfg.name());
		}
		return cfgNames;
	}
}
