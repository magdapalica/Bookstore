package com.fdmgroup.bookstoreproject.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.bookstoreproject.exception.ProductNotFoundException;
import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.Buy;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.security.DefaultUserDetailsService;
import com.fdmgroup.bookstoreproject.service.ProductService;
import com.fdmgroup.bookstoreproject.service.BuyService;


@Controller
public class BuyController {

	@Autowired
	private BuyService buyService;
	@Autowired
	private DefaultUserDetailsService userService;
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/buyProduct")
	public String buyProduct(Authentication authentication, ModelMap model, @RequestParam String productId)
			throws ParseException, NumberFormatException, ProductNotFoundException {
		User buyer = userService.getCurrentUser(authentication);
		Product product = productService.findProductbyId(Integer.parseInt(productId));
		buyService.createNewBuy(new Buy(buyer, product)); 
		return "redirect:/";
	}

	@GetMapping("/ownProducts")
	public String ownProducts(Authentication authentication, ModelMap model) {
		User owner = userService.getCurrentUser(authentication);
		List<Buy> requestedBuy = buyService.findRequestedBuyForUser(owner);
		model.addAttribute("requestedBuy", requestedBuy);
		List<Buy> confirmedBuy = buyService.findConfirmedBuyForUser(owner);
		model.addAttribute("confirmedBuy", confirmedBuy);
		setNotificationCount(model, authentication);
		return "/ownProducts";
	}

	@PostMapping("/rejectBuy")
	public String rejectBuy(@RequestParam String buyId) {
		Buy buy = buyService.findBuyById(Integer.parseInt(buyId));
		buyService.rejectBuy(buy);
		return "redirect:/ownProducts";
	}

	@PostMapping("/confirmBuy")
	public String confirmBuy(@RequestParam String buyId) {
		Buy buy = buyService.findBuyById(Integer.parseInt(buyId));
		buyService.confirmBuy(buy);
		return "redirect:/ownProducts";
	}

//	@PostMapping("/confirmReturn")
//	public String confirmReturn(@RequestParam String buyId) {
//		Buy buy = buyService.findBuyById(Integer.parseInt(buyId));
//		buyService.confirmReturn(buy);
//		return "redirect:/ownProducts";
//	}

	public void setNotificationCount(ModelMap model, Authentication authenticaton) {
		if (authenticaton != null) {
			User user = userService.getCurrentUser(authenticaton);
			model.addAttribute("notificationCount", buyService.findUnfinishedBuy(user).size());
		}
	}
}
