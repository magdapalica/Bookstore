//package com.fdmgroup.shazar.controller;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fdmgroup.shazar.App;
//import com.fdmgroup.shazar.exception.ProductNotFoundException;
//import com.fdmgroup.shazar.model.Product;
//import com.fdmgroup.shazar.model.ProductRating;
//import com.fdmgroup.shazar.model.User;
//import com.fdmgroup.shazar.security.DefaultUserDetailsService;
//import com.fdmgroup.shazar.service.ProductRatingService;
//import com.fdmgroup.shazar.service.ProductService;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ContextConfiguration(classes = App.class)
//
//public class ProductRatingControllerTest {
//
//	@Autowired
//	MockMvc mockMvc;
//	
//	@MockBean
//	ProductRatingService mockProductRatingService;
//	@MockBean
//	ProductService mockProductService;
//	@MockBean
//	DefaultUserDetailsService mockUserService;
//	
//	
//	@Test
//	@WithMockUser
//	public void test_rateProduct() throws Exception {
//		User author = new User();
//		Mockito.doReturn(author).when(mockUserService)
//		.getCurrentUser(Mockito.any(Authentication.class));
//		Product product = new Product();
//		Mockito.doReturn(product).when(mockProductService).findProductbyId(Mockito.eq(10));
//		Mockito.doNothing().when(mockProductRatingService).rateProduct(Mockito.any(ProductRating.class));
//		mockMvc.perform(get("/rateProduct")
//				.param("productId", "10")
//				.param("rating","5"))
//		.andExpect(view().name("redirect:/products/10"))
//		.andExpect(status().isFound());
//		
//		
//	}
//	
//	
//}
