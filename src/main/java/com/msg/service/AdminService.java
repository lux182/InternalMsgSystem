package com.msg.service;

import com.msg.domain.Admin;

public interface AdminService extends BaseService<Admin>{

	Admin getAdminByUsername(String username);

}
