package com.fdmgroup.bookstoreproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.bookstoreproject.model.LenderRating;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.security.DefaultUserDetailsService;
import com.fdmgroup.bookstoreproject.service.LenderRatingService;
import com.fdmgroup.bookstoreproject.service.ProductService;

public class LenderRatingController {

	@Autowired
	LenderRatingService service;
	@Autowired
	ProductService productService;
	@Autowired
	private DefaultUserDetailsService userService;

	@RequestMapping(value = "/rateLender")
	public String rateLender(Authentication authentication, ModelMap model, @RequestParam int productId,
			@RequestParam int lenderId, @RequestParam int rating) {
		User author = userService.getCurrentUser(authentication);
		User lender = userService.findById(lenderId).get();
		service.rateLender(new LenderRating(lender, rating, author));
		return "redirect:/products/" + productId;
	}
}