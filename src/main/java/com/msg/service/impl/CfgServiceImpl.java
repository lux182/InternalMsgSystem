package com.msg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.msg.component.SendEngine;
import com.msg.domain.CfgDomain;
import com.msg.enums.Cfg;
import com.msg.repo.CfgRepo;
import com.msg.service.CfgService;

@Service
public class CfgServiceImpl extends BaseServiceImpl<CfgDomain> implements CfgService{

	private static final Logger logger = Logger.getLogger(CfgServiceImpl.class);
	
	@Resource
	CfgRepo cfgRepo;
	
	@Override
	public void initCfg() {
		initCfgEnum();
		SendEngine.init();
	}

	@Override
	@Transactional
	public void update(Map<String, String[]> parameterMap) {
		List<String> keys = Cfg.names();
		for(String key:keys){
			String[] value = parameterMap.get(key);
			if(value!=null&&value.length>0&&!StringUtils.isEmpty(value[0])){
				Cfg cfg = Cfg.valueOf(key);
				if(!value[0].equals(cfg.getValue())){
					CfgDomain cfgDomain = cfgRepo.findByCfgKey(key);
					if(cfgDomain==null){
						cfgDomain=new CfgDomain();
						cfgDomain.setCfgKey(key);
					}
					cfgDomain.setCfgVal(value[0]);
					cfg.setValue(value[0]);
					cfgRepo.save(cfgDomain);
				}
			}
		}
		SendEngine.init();
	}

	@PostConstruct
	@Override
	public void initCfgEnum() {
		List<String> keys = Cfg.names();
		for(CfgDomain cfg :cfgRepo.findAll()){
			if(keys.contains(cfg.getCfgKey())){
				Cfg cfgEnum = Cfg.valueOf(cfg.getCfgKey());
				cfgEnum.setValue(cfg.getCfgVal());
			}
		}
	}
	
}
