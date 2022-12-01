package com.fdmgroup.bookstoreproject.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.bookstoreproject.exception.ProductNotFoundException;
import com.fdmgroup.bookstoreproject.model.Picture;
import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.ProductRating;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.security.DefaultUserDetailsService;
import com.fdmgroup.bookstoreproject.service.ProductRatingService;
import com.fdmgroup.bookstoreproject.service.ProductService;
import com.fdmgroup.bookstoreproject.service.BuyService;



@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	@Autowired
	private BuyService buyService;
//	@Autowired
//	private LenderRatingService lenderRatingService;
	@Autowired
	private ProductRatingService productRatingService;
	@Autowired
	private BuyController buyController;

	@Autowired
	private DefaultUserDetailsService userService;

	@GetMapping(value = "/")
	public String index(Authentication authentication, ModelMap model) {
		populateModel(model, authentication);
		return "index";
	}


	@RequestMapping(value = "/productList")
	public String productList(ModelMap model, @RequestParam String search, @RequestParam String author,
			@RequestParam String category, @RequestParam String maxPrice, @RequestParam String title) throws ParseException {
//		Date start = Time.htmlToSqlDate(startDate);
//		Date end = Time.htmlToSqlDate(endDate);
		double price = Double.parseDouble(maxPrice);
		List<Product> products = service.findProducts(title, author, category, price);
		model.addAttribute("products", products);
		return "productList";
	}

	@GetMapping(value = "/add")
	public String addNewProduct(ModelMap model, Authentication authentication) {
		populateModel(model, authentication);
		return "add";
	}

	@PostMapping(value = "/add")
	public String addNewProduct(Authentication authentication, ModelMap model, @RequestParam MultipartFile[] file,
			@RequestParam String title, @RequestParam String description, @RequestParam String author,
			@RequestParam String category, @RequestParam String price) {
		double priceDouble = Double.parseDouble(price);
		User buyer = userService.getCurrentUser(authentication);
		Product product = new Product(title, description, author, category, priceDouble, buyer);
//		service.createNewProduct(product);

		InputStream inputStream;

		List<Picture> pictures = new ArrayList<>();

		for (MultipartFile photo : file) {
			if (!photo.getOriginalFilename().isEmpty()) {

				Picture picture = new Picture(photo.getOriginalFilename());

				pictures.add(picture);

				Path destinationFile = Paths.get(System.getProperty("user.dir"), "src/main/webapp/product-pictures",

						photo.getOriginalFilename());

				try {
					inputStream = photo.getInputStream();
					Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
					return "add";
				}
			}

		}
		product.setPictures(pictures);

		service.saveProduct(product);

		return "redirect:/add";
	}

	@GetMapping(value = "/products/{id}")
	public String goToDetails(Authentication authentication, ModelMap model, @PathVariable int id)
			throws ProductNotFoundException {
		Product product = service.findProductbyId(id);
		model.addAttribute("product", product);

//		User user = userService.getCurrentUser(authentication);
//		boolean userHasBuyedProduct = buyService.userHasBuyedProduct(user, product);
//		model.addAttribute("userHasBuyedProduct", userHasBuyedProduct);

		List<ProductRating> productRatings = productRatingService.findProductRatings(product);
		double productRating = productRatings.stream().mapToDouble(r -> r.getRating()).average().orElse(0);
		model.addAttribute("productRating", productRating);
		model.addAttribute("productVotes", productRatings.size());

//		List<LenderRating> lenderRatings = lenderRatingService.findLenderRatings(product.getOwner());
//		double lenderRating = lenderRatings.stream().mapToDouble(r -> r.getRating()).average().orElse(0);
//		model.addAttribute("lenderRating", lenderRating);
//		model.addAttribute("lenderVotes", lenderRatings.size());

		buyController.setNotificationCount(model, authentication);

		return "details";
	}

	@PostMapping(value = "/deleteProduct")
	public String deleteProduct(ModelMap model, @RequestParam("id") int id) {
		service.deleteProduct(id);
		return "redirect:/";
	}

	@ExceptionHandler(value = ProductNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ModelAndView productNotFound(ProductNotFoundException ex) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("productNotFound");
		mav.addObject("message", ex.getMessage());

		return mav;
	}

	private void populateModel(ModelMap model, Authentication authentication) {
		model.addAttribute("products", service.findAllProducts());
		model.addAttribute("authors", service.allAuthor());
		model.addAttribute("categories", service.allCategories());
		model.addAttribute("titles", service.allTitles());
		buyController.setNotificationCount(model, authentication);
	}

	@RequestMapping(value = "/productBooking")
	public String productBooking(ModelMap model, @RequestParam int productId , @RequestParam String startDate,
			@RequestParam String endDate) throws ParseException, NumberFormatException, ProductNotFoundException {
//		Date start = Time.htmlToSqlDate(startDate);
//		Date end = Time.htmlToSqlDate(endDate);
		Product product = service.findProductbyId(productId);
//		double price = (Time.getDifferenceInDays(start ,end ) + 1) * product.getPrice();
		double price = product.getPrice();
		boolean available = service.getProductAvailability(product);
		model.addAttribute("available", available);
		model.addAttribute("price", price);
		return "productBooking";
	}
}
