package com.msg.service.impl;

import org.springframework.stereotype.Service;

import com.msg.domain.Admin;
import com.msg.service.AdminService;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService{

	@Override
	public Admin getAdminByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
