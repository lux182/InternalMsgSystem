package com.msg;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.msg.service.AdminService;

public class AdminCreate extends BaseTest{
	
	@Resource
	AdminService adminService;
	
	@Test
	@Rollback(value=false)
	public void createAdmin(){
		adminService.createAdmin("admin", "dianrong", "管理员");
	}
}
