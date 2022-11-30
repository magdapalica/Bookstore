package com.fdmgroup.bookstoreproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository repo;

	@Autowired
	public UserServiceImpl(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public User getCurrentUser(String username) {
		Optional<User> currentUser = repo.findByUsername(username);
		return currentUser.orElse(null);
	}

	@Override
	public User updateUser(User user) {
		Optional<User> outputUser = repo.findByUsername(user.getUsername());
		User updatedUser = outputUser.orElse(new User());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setNumber(user.getNumber());
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		return repo.save(updatedUser);
	}

}
