package com.fdmgroup.shazar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.verify;

import com.fdmgroup.shazar.model.Role;
import com.fdmgroup.shazar.repository.RoleRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class RoleServiceImplTest {

	@InjectMocks
	RoleServiceImpl roleServiceImpl;
	@MockBean
	RoleRepository mockRoleRepository;
	
	
	@Test
	public void test_findByRoleName() {
		Role role = new Role();
		Mockito.doReturn(role).when(mockRoleRepository).findByRolename("role");
		Role serviceRole = roleServiceImpl.findByRoleName("role");
		verify(mockRoleRepository).findByRolename("role");
		Assertions.assertEquals(serviceRole, role);
	}
	
	@Test
	public void test_addRole() {
		Role role = new Role();
		Mockito.doReturn(null).when(mockRoleRepository).save(role);
		roleServiceImpl.addRole(role);
		verify(mockRoleRepository).save(role);
	}
}
