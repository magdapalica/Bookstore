package com.fdmgroup.bookstoreproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.bookstoreproject.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRolename(String rolename);
}
