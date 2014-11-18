package com.msg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.msg.domain.Admin;
import com.msg.repo.AdminRepo;
import com.msg.service.AdminService;
import com.msg.utils.NormalException;
import com.msg.utils.StaticMethod;
import com.msg.utils.SystemMessage.Hint;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService{

	@Resource
	AdminRepo adminRepo;
	@Override
	public Admin getAdminByUsername(String username) {
		return adminRepo.findByUsername(username);
	}

	@Override
	public Admin createAdmin(String username, String password) {
		if(adminRepo.findByUsername(username)==null){
			Admin admin = new Admin();
			admin.setUsername(username);
			admin.setPassword(StaticMethod.generatePassword(username, password));
			return adminRepo.save(admin);
		}else{
			throw new NormalException(Hint.USERNAME_HAS_EXIST);
		}
	}

}
