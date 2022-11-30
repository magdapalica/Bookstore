package com.fdmgroup.bookstoreproject.service;

import com.fdmgroup.bookstoreproject.model.Role;

public interface RoleService {

	Role findByRoleName(String string);
	
	void addRole(Role role);
}
