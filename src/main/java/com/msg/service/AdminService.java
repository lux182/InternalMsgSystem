package com.msg.service;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import com.msg.domain.Admin;

@Validated
public interface AdminService extends BaseService<Admin>{

	Admin getAdminByUsername(@NotEmpty String username);

	Admin createAdmin(@NotEmpty String username, @NotEmpty String password, String nickname);

}
