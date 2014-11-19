package com.msg.service;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.msg.domain.CfgDomain;

@Validated
public interface CfgService extends BaseService<CfgDomain>{
	void initCfg();

	void update(@NotNull Map<String, String[]> parameterMap);
}
