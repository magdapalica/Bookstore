package com.fdmgroup.bookstoreproject.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.bookstoreproject.App;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = App.class)
public class ProfileControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService mockUserService;

	
	@Test
	@WithMockUser
	public void test_goToProfile() throws Exception {
	User toBeReturned = new User();
	Mockito.doReturn(toBeReturned).when(mockUserService).getCurrentUser("user");
	mockMvc.perform(get("/personal"))
	.andExpect(status().isOk())
	.andExpect(view().name("personal"))
	.andExpect(model().attribute("user", toBeReturned));
		
}
	
	@Test
	@WithMockUser
	public void test_updateProfile() throws Exception {
		User user = new User();
		Mockito.doReturn(user).when(mockUserService).updateUser(user);
		mockMvc.perform(post("/personal")
		.param("user",user.getUsername()))
		.andExpect(status().isOk())
		.andExpect(view().name("personal"))
		.andExpect(model().attribute("user", user));
	}

	
}