package com.fdmgroup.bookstoreproject.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.repository.UserRepository;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

	private UserRepository repo;

	@Autowired
	public DefaultUserDetailsService(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return new UserPrincipal(user);
	}

	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}

	public Optional<User> findById(int id) {
		return repo.findById(id);
	}

	public User save(User user) {
		return repo.save(user);
	}

	public User getCurrentUser(Authentication authentication) {
		String username = authentication.getName();
		return repo.findByUsername(username).get();
	}

	public User findUserByEmail(String userEmail) {
		return repo.findByEmail(userEmail);
	}


}
