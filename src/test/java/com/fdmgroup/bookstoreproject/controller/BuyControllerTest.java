package com.fdmgroup.bookstoreproject.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.bookstoreproject.App;
import com.fdmgroup.bookstoreproject.exception.ProductNotFoundException;
import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.Buy;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.security.DefaultUserDetailsService;
import com.fdmgroup.bookstoreproject.service.ProductService;
import com.fdmgroup.bookstoreproject.service.BuyService;
import com.fdmgroup.bookstoreproject.service.UserService;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = App.class)
public class BuyControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	DefaultUserDetailsService mockUserService;
	@MockBean
	BuyService mockBuyService;
	@MockBean
	ProductService mockProductService;
	
	@Test
	@WithMockUser
	public void test_buyProduct() throws Exception {
	User buyer = mockUserService.getCurrentUser(Mockito.any(Authentication.class));
	Mockito.doReturn(buyer).when(mockUserService).getCurrentUser(Mockito.any(Authentication.class));
	Product product = mockProductService.findProductbyId(200);
	Mockito.doReturn(product).when(mockProductService).findProductbyId(200);
	Mockito.doNothing().when(mockBuyService).createNewBuy(new Buy());
	mockMvc.perform(get("/buyProduct")
	 .param("productId", "200")
	 .param("startDate", "2022-11-22")
	 .param("endDate", "2022-11-23"))
	 .andExpect(status().is3xxRedirection())
	 .andExpect(view().name("redirect:/"));
	 
	}
	
	@Test
	@WithMockUser
	public void test_ownProducts() throws Exception {
		User owner = mockUserService.getCurrentUser(Mockito.any(Authentication.class));
		List<Buy> requestedBuy = mockBuyService.findRequestedBuyForUser(owner);
		Mockito.doReturn(owner).when(mockUserService).getCurrentUser(Mockito.any(Authentication.class));
		Mockito.doReturn(requestedBuy).when(mockBuyService).findRequestedBuyForUser(owner);
		mockMvc.perform(get("/ownProducts"))
		.andExpect(status().isOk())
		.andExpect(view().name("/ownProducts"))
		.andExpect(model().attribute("requestedBuy", requestedBuy));
	}
	
	@Test
	@WithMockUser
	public void test_rejectBuy() throws Exception {
		Buy buy = mockBuyService.findBuyById(100);
		Mockito.doNothing().when(mockBuyService).rejectBuy(buy);
		mockMvc.perform(post("/rejectBuy")
		.param("buyId", "100"))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/ownProducts"));
	}
	
	
	@Test
	@WithMockUser
	public void test_confirmBuy() throws Exception {
		Buy buy = mockBuyService.findBuyById(100);
		Mockito.doNothing().when(mockBuyService).confirmBuy(buy);
		mockMvc.perform(post("/confirmBuy")
		.param("buyId", "100"))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/ownProducts"));
	}
	
//	@Test
//	@WithMockUser
//	public void test_confirmReturn() throws Exception {
//		Buy rent = mockRentService.findRentById(100);
//		Mockito.doNothing().when(mockRentService).confirmReturn(rent);
//		mockMvc.perform(post("/confirmReturn")
//		.param("rentId", "100"))
//		.andExpect(status().isFound())
//		.andExpect(view().name("redirect:/ownProducts"));
//	}
	
}
