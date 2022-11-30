package com.fdmgroup.bookstoreproject.dataimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fdmgroup.bookstoreproject.model.Role;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.repository.UserRepository;

@Component
public class DataImport implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (!userRepository.findByUsername("admin").isPresent()) {
			Role roleAdmin = new Role("Admin");
			Role roleCustomer = new Role("User");

			User admin = new User("admin", encoder.encode("123"), roleAdmin);
			userRepository.save(admin);

			User customer = new User("user", encoder.encode("123"), roleCustomer);
			userRepository.save(customer);
		}
	}


}
