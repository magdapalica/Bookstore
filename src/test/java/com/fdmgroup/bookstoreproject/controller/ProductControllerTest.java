package com.fdmgroup.bookstoreproject.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fdmgroup.bookstoreproject.App;
import com.fdmgroup.bookstoreproject.exception.ProductNotFoundException;
import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.ProductRating;
import com.fdmgroup.bookstoreproject.model.Role;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.security.DefaultUserDetailsService;
import com.fdmgroup.bookstoreproject.service.ProductRatingService;
import com.fdmgroup.bookstoreproject.service.ProductService;
import com.fdmgroup.bookstoreproject.service.BuyService;
import com.fdmgroup.bookstoreproject.service.RoleService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = App.class)
public class ProductControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@MockBean
	ProductService mockService;
	@MockBean
	RoleService roleService;
	@MockBean
	LoginAndRegisterController userService;

	@MockBean
	ProductRatingService mockProductRatingService;
	@MockBean
	DefaultUserDetailsService mockUserService;
	@MockBean
	BuyService mockBuyService;

	List<Product> expectedProductsList;
	Product expectedProduct;

	@BeforeEach
	public void init() {
		expectedProductsList = new ArrayList<>();
		Role role = roleService.findByRoleName("User");
		User user = new User("Alice", "a", role);
		userService.registerNewUser(user);
		expectedProductsList.add(new Product("title", "description", "author", "horror", 10D, user));
		expectedProductsList.add(new Product("title1", "description1", "author1", "horror1", 11D, user));
		expectedProductsList.add(new Product("title2", "description2", "author2", "comic", 12D, user));
		expectedProduct = new Product("title1", "description1", "author1", "classics", 10, user);
	}

	@Test
	public void test_goToHomePage() throws Exception {
		when(mockService.findAllProducts()).thenReturn(expectedProductsList);
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(model().attribute("products", expectedProductsList));
	}
	
	
	@Test
	@WithMockUser
	public void test_addNewProductFirst() throws Exception {
		mockMvc.perform(get("/add"))
		.andExpect(status().isOk())
		.andExpect(view().name("add"));
	}

	@Test
	@WithMockUser
	public void test_addNewProductSecond() throws Exception {
		
		MockMultipartFile file 
	      = new MockMultipartFile(
	        "file", 
	        "hesssllo.txt", 
	        MediaType.TEXT_PLAIN_VALUE, 
	        "Hello, World!".getBytes()
	      );
		
		User buyer = mockUserService.getCurrentUser(Mockito.any(Authentication.class));
		Mockito.doReturn(buyer).when(mockUserService).getCurrentUser(Mockito.any(Authentication.class));
		Mockito.doNothing().when(mockService).saveProduct(expectedProduct);
		mockMvc.perform(MockMvcRequestBuilders.multipart("/add")
				.file(file)
				.param("title", "title")
				.param("description", "description")
				.param("author", "shakespeare")
				.param("category","classics")
				.param("price", "10"))
				.andExpect(view().name("redirect:/add"));
//				.andExpect(model().attribute("confirmation", "File successfully uploaded"));

	}		
		

	@Test
	@WithMockUser
	public void test_goToDetails() throws Exception {
		User user = new User();
		when(mockService.findProductbyId(200)).thenReturn(expectedProduct);
		List<ProductRating> productRatings= mockProductRatingService.findProductRatings(expectedProduct);
		double productRating = productRatings.stream().mapToDouble(r -> r.getRating()).average().orElse(0);	
		Mockito.doReturn(user).when(mockUserService).getCurrentUser(Mockito.any(Authentication.class));
		Mockito.doReturn(true).when(mockBuyService).userHasBuyedProduct(user, expectedProduct);
		when(mockService.findProductbyId(200)).thenReturn(expectedProduct);
		Mockito.doReturn(productRatings).when(mockProductRatingService).findProductRatings(expectedProduct);
		mockMvc.perform(get("/products/200"))
		.andExpect(status().isOk()).andExpect(view().name("details"))
		.andExpect(model().attribute("product", expectedProduct))
		.andExpect(model().attribute("productRating", productRating))
		.andExpect(model().attribute("productVotes", productRatings.size()))
		.andExpect(model().attribute("userHasBuyedProduct", true));
		
		
	}

	@Test
	@WithMockUser
	public void test_goToDetailsThrowsPlaceNotFoundException() throws Exception {
		when(mockService.findProductbyId(123)).thenThrow(new ProductNotFoundException(123));
		mockMvc.perform(get("/products/123")).andExpect(status().isNotFound()).andExpect(view().name("productNotFound"))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
				.andExpect(model().attribute("message", "The product with id 123 is not available."));
	}

	@Test
	@WithMockUser
	public void test_deleteProduct() throws Exception {
	 Mockito.doNothing().when(mockService).deleteProduct(Mockito.eq(200));
		mockMvc.perform(post("/deleteProduct").param("id", "200"))
		.andExpect(view().name("redirect:/")).andExpect(status().is3xxRedirection());
		verify(mockService, times(1)).deleteProduct(200);
	}
	
//	@Test
//	public void test_productList() throws Exception {
//		Mockito.doReturn(expectedProductsList).when(mockService)
//		.findProducts("title","horror", "author", 10d);
//		mockMvc.perform(get("/productList")
//		.param("search", "title")
//		.param("author","joyce")
//		.param("category", "horror")
//		.param("maxPrice","10")
//		.andExpect(view().name("productList"))
//		.andExpect(status().isOk())
//		.andExpect(model().attribute("products", expectedProductsList));
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_productBooking() throws Exception {
//		Mockito.doReturn(expectedProduct).when(mockService).findProductbyId(100);
//		mockMvc.perform(get("/productBooking")
//		.param("productId", "100")
//		.andExpect(view().name("productBooking"))
//		.andExpect(status().isOk())
//		.andExpect(model().attribute("price", 20.0));
//	}
	
	
}
