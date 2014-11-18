package com.msg.repo;

import com.msg.domain.CfgDomain;

public interface CfgRepo extends BaseRepo<CfgDomain>{
	CfgDomain findByCfgKey(String key);
}
