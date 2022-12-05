package com.fdmgroup.bookstoreproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.bookstoreproject.exception.ProductNotFoundException;
import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.ProductRating;
import com.fdmgroup.bookstoreproject.model.Top10;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.security.DefaultUserDetailsService;
import com.fdmgroup.bookstoreproject.service.ProductRatingService;
import com.fdmgroup.bookstoreproject.service.ProductService;

@Controller
public class ProductRatingController {

	@Autowired
	ProductRatingService service;
	@Autowired
	ProductService productService;
	@Autowired
	private DefaultUserDetailsService userService;

	@RequestMapping(value = "/rateProduct")
	public String rateProduct(Authentication authentication, ModelMap model, @RequestParam int productId,
			@RequestParam int rating) throws ProductNotFoundException {
		User author = userService.getCurrentUser(authentication);
		Product product = productService.findProductbyId(productId);
		service.rateProduct(new ProductRating(product, rating, author));
		return "redirect:/products/" + productId;
	}

	@RequestMapping(value = "/top10")
	public String getTop10 (ModelMap model) {
		model.addAttribute("top10", service.getTop10());
		return "top10";	
	}
}
