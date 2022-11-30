package com.fdmgroup.bookstoreproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.bookstoreproject.model.Role;
import com.fdmgroup.bookstoreproject.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repo;

	@Override
	public Role findByRoleName(String rolename) {
		return repo.findByRolename(rolename);
	}

	@Override
	public void addRole(Role role) {
		repo.save(role);
	}

}
