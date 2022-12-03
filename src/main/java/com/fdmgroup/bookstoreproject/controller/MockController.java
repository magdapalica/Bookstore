//package com.fdmgroup.bookstoreproject.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//
////import com.fdmgroup.bookstoreproject.model.LenderRating;
//import com.fdmgroup.bookstoreproject.model.Picture;
//import com.fdmgroup.bookstoreproject.model.Product;
////import com.fdmgroup.bookstoreproject.model.ProductRating;
//import com.fdmgroup.bookstoreproject.model.Rent;
//import com.fdmgroup.bookstoreproject.model.Role;
//import com.fdmgroup.bookstoreproject.model.User;
//import com.fdmgroup.bookstoreproject.security.DefaultUserDetailsService;
////import com.fdmgroup.bookstoreproject.service.LenderRatingService;
////import com.fdmgroup.bookstoreproject.service.ProductRatingService;
//import ccom.fdmgroup.bookstoreproject.service.ProductService;
//import com.fdmgroup.bookstoreproject.service.RentService;
//importcom.fdmgroup.bookstoreproject.service.RoleService;

//import org.springframework.ui.ModelMap;
//
//@Controller
//public class MockController {
//
//	@Autowired
//	private ProductService productService;
//	@Autowired
//	private DefaultUserDetailsService userService;
//	@Autowired
//	private RoleService roleService;
//	@Autowired
//	private RentService rentService;
//	@Autowired
//	private PasswordEncoder encoder;

////	@Autowired
////	private ProductRatingService productRatingService;
//
//	@GetMapping(value = "/mock")
//	public String addMockData(ModelMap model) {
//		Role userRole = roleService.findByRoleName("User");
//		User alice = new User("Alice", encoder.encode("a"), "Alice","John","alice.smith@gmail.com",432123432, userRole);
//		Picture picture5 = new Picture("rys2.PNG");
//		alice.setPictures(picture5);
//		userService.save(alice);
//		User bob = new User("Bob", encoder.encode("b"),"Bob","Smith","bob.smith@gmail.com",432123432, userRole);
//		Picture picture4 = new Picture("rys2.PNG");
//		bob.setPictures(picture4);
//		userService.save(bob);
//		User carl = new User("Carl", encoder.encode("c"), "Carl","Lee","lee.smith@gmail.com",432123432, userRole);
//		Picture picture6 = new Picture("rys2.PNG");
//		carl.setPictures(picture6);
//		userService.save(carl);
//
//		Product bicycle = new Product("Pegasus Bicycle", "A bicycle for children.", "Blue", "Bicycles", 5, alice);
//		productService.createNewProduct(bicycle);
//		Product ferrari = new Product("Ferrari", "Blazingly fast sports car!", "Red", "Cars", 700, alice);
//		productService.createNewProduct(ferrari);
//		Product shovel = new Product("Shovel", "Very handy for digging holes.", "Brown", "Tools", 0.75, bob);
//		List<Picture> pictures = new ArrayList<>();
//		List<Picture> pictures2 = new ArrayList<>();
//		List<Picture> pictures3 = new ArrayList<>();
//		Picture picture1 = new Picture("shovel2.jfif");
//		Picture picture2 = new Picture("images.jfif");
//		Picture picture3 = new Picture("bicycle.jpg");
//		pictures.add(picture1);
//		pictures2.add(picture2);
//		pictures3.add(picture3);
//		shovel.setPictures(pictures);
//		ferrari.setPictures(pictures2);
//		bicycle.setPictures(pictures3);
//		productService.createNewProduct(shovel);
//
//		rentService.createNewRent(new Rent(bob, bicycle, Time.newSqlDate(2022, 11, 10), Time.newSqlDate(2022, 11, 10)));
//		rentService.createNewRent(new Rent(bob, bicycle, Time.newSqlDate(2022, 11, 18), Time.newSqlDate(2022, 11, 20)));
//
////		lenderRatingService.rateRating(new LenderRating(alice, 5, bob));
////		lenderRatingService.rateRating(new LenderRating(bob, 4, carl));
////		lenderRatingService.rateRating(new LenderRating(alice, 4, carl));
////
////		productRatingService.rateProduct(new ProductRating(bicycle, 5, bob));
////		productRatingService.rateProduct(new ProductRating(ferrari, 2, bob));
////		productRatingService.rateProduct(new ProductRating(ferrari, 3, carl));
//
//		return "redirect:/";
//	}
//
//}
