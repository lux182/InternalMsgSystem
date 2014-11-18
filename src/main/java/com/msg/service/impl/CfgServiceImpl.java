package com.msg.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.msg.domain.CfgDomain;
import com.msg.enums.Cfg;
import com.msg.repo.CfgRepo;
import com.msg.service.CfgService;

@Service
public class CfgServiceImpl extends BaseServiceImpl<CfgDomain> implements CfgService{

	@Resource
	CfgRepo cfgRepo;

	@Override
	public void initCfg() {
		List<Cfg> keys = Arrays.asList(Cfg.values());
		for(CfgDomain cfg :cfgRepo.findAll()){
			if(keys.contains(cfg.getCfgKey())){
				Cfg cfgEnum = Cfg.valueOf(cfg.getCfgKey());
				cfgEnum.setValue(cfg.getCfgVal());
			}
		}
	}
	
}
