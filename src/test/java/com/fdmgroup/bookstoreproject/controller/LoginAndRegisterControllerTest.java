package com.fdmgroup.bookstoreproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.bookstoreproject.App;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.security.DefaultUserDetailsService;
import com.fdmgroup.bookstoreproject.service.RoleService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = App.class)
public class LoginAndRegisterControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private LoginAndRegisterController controller;

	@MockBean
	DefaultUserDetailsService mockUserService;

	@MockBean
	PasswordEncoder mockEncoder;

	@MockBean
	RoleService mockRoleService;

	@Test
	@WithMockUser
	public void test_login() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"));
	}

	@Test
	@WithMockUser
	public void test_register() throws Exception {
		mockMvc.perform(get("/register")).andExpect(status().isOk()).andExpect(view().name("register"));
	}

	@Test
	@WithMockUser
	public void test_passwordreset() throws Exception {
		mockMvc.perform(get("/passwordreset")).andExpect(status().isOk()).andExpect(view().name("passwordreset"));
	}

	@Test
	public void test_registerNewUser() throws Exception {
		User user = new User("username", "password", null);
		controller.registerNewUser(user);
		assertEquals(user.getRole().getRolename(), "ROLE_User");
		assertEquals(user.getPassword(), mockEncoder.encode("password"));
	}

}