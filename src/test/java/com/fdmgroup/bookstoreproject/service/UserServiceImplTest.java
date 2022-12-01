package com.fdmgroup.bookstoreproject.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.verify;

import com.fdmgroup.bookstoreproject.model.Role;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;
	@MockBean
	UserRepository mockUserRepository;
	@Captor
	ArgumentCaptor<User> userArgumentCaptor;
	
	@Test
	public void test_getCurrentUser() {
		User user= new User();
		Mockito.doReturn(Optional.of(user)).when(mockUserRepository).findByUsername("name");
		User userService = userServiceImpl.getCurrentUser("name");
		verify(mockUserRepository).findByUsername("name");
		Assertions.assertEquals(userService, user);
	}
	
	@Test
	public void test_updateUser() {
		User user= new User
				(
				"username",
				"password",
				"firstName",
				"lastName",
				"email",
				11111L,
				null);
		
		Mockito.doReturn(Optional.of(user)).when(mockUserRepository)
		.findByUsername("name");
		Mockito.doReturn(new User()).when(mockUserRepository).save(Mockito.any());
		
		User serviceUser = userServiceImpl.updateUser(user);
		
		verify(mockUserRepository).save(userArgumentCaptor.capture());
		verify(mockUserRepository).findByUsername(user.getUsername());
		
		User value = userArgumentCaptor.getValue();
		
		Assertions.assertEquals(user.getEmail(), value.getEmail());
		Assertions.assertEquals(user.getNumber(), value.getNumber());
		Assertions.assertEquals(user.getFirstName(), value.getFirstName());
		Assertions.assertEquals(user.getLastName(), value.getLastName());
		
		
		Assertions.assertEquals(serviceUser, new User());
	}
		
		
		

}
