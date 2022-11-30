package com.fdmgroup.bookstoreproject.service;

import com.fdmgroup.bookstoreproject.model.User;

public interface UserService {

	User getCurrentUser(String username);
	
	User updateUser(User user);
	
}
