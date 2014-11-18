package com.msg.service;

import org.springframework.validation.annotation.Validated;

import com.msg.domain.CfgDomain;

@Validated
public interface CfgService extends BaseService<CfgDomain>{
	void initCfg();
}
