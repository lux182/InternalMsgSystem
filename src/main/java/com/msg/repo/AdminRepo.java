package com.msg.repo;

import com.msg.domain.Admin;

public interface AdminRepo extends BaseRepo<Admin>{

	Admin findByUsername(String username);

}
