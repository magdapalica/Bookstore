package com.fdmgroup.bookstoreproject.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fdmgroup.bookstoreproject.model.Picture;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.security.DefaultUserDetailsService;
import com.fdmgroup.bookstoreproject.service.RoleService;

@Controller
public class LoginAndRegisterController {

	@Autowired
	private DefaultUserDetailsService userService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RoleService roleService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/passwordreset")
	public String passwordreset() {
		return "passwordreset";
	}

	@PostMapping("/passwordreset")
	public String resetpassword(String username, String password) {
		Optional<User> user = userService.findByUsername(username);
		if (user.isPresent()) {
		 user.get().setPassword(encoder.encode(password));
		 userService.save(user.get());
		 return "login";
		}
		return "passwordreset";
	}
	
	
	@PostMapping("/register")
	public String registerSubmit(@ModelAttribute("user") User user, @RequestParam MultipartFile file, ModelMap model) {
		Optional<User> userFromDatabase = userService.findByUsername(user.getUsername());
		if (userFromDatabase.isPresent()) {
			model.addAttribute("message", "This user name already exists");
			return "register";
		}
		InputStream inputStream;

		if (!file.getOriginalFilename().isEmpty()) {

			Picture picture = new Picture(file.getOriginalFilename());

			Path destinationFile = Paths.get(System.getProperty("user.dir"), "src/main/webapp/profile-pictures",

					file.getOriginalFilename());

			try {
				inputStream = file.getInputStream();
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
				return "index";
			}
			user.setPictures(picture);
		}

		registerNewUser(user);
		return "index";
	}

	public void registerNewUser(User user) {
		user.setRole(roleService.findByRoleName("User"));
		user.setPassword(encoder.encode(user.getPassword()));
		userService.save(user);
	}
}
