package com.fdmgroup.shazar.controller;

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

import com.fdmgroup.shazar.App;
import com.fdmgroup.shazar.exception.ProductNotFoundException;
import com.fdmgroup.shazar.model.Product;
import com.fdmgroup.shazar.model.Rent;
import com.fdmgroup.shazar.model.User;
import com.fdmgroup.shazar.security.DefaultUserDetailsService;
import com.fdmgroup.shazar.service.ProductService;
import com.fdmgroup.shazar.service.RentService;
import com.fdmgroup.shazar.service.UserService;
import com.fdmgroup.shazar.utils.Time;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = App.class)
public class RentControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	DefaultUserDetailsService mockUserService;
	@MockBean
	RentService mockRentService;
	@MockBean
	ProductService mockProductService;
	
	@Test
	@WithMockUser
	public void test_rentProduct() throws Exception {
	User renter = mockUserService.getCurrentUser(Mockito.any(Authentication.class));
	Mockito.doReturn(renter).when(mockUserService).getCurrentUser(Mockito.any(Authentication.class));
	Product product = mockProductService.findProductbyId(200);
	Mockito.doReturn(product).when(mockProductService).findProductbyId(200);
	Mockito.doNothing().when(mockRentService).createNewRent(new Rent());
	mockMvc.perform(get("/rentProduct")
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
		List<Rent> requestedRents = mockRentService.findRequestedRentsForUser(owner);
		Mockito.doReturn(owner).when(mockUserService).getCurrentUser(Mockito.any(Authentication.class));
		Mockito.doReturn(requestedRents).when(mockRentService).findRequestedRentsForUser(owner);
		mockMvc.perform(get("/ownProducts"))
		.andExpect(status().isOk())
		.andExpect(view().name("/ownProducts"))
		.andExpect(model().attribute("requestedRents", requestedRents));
	}
	
	@Test
	@WithMockUser
	public void test_rejectRent() throws Exception {
		Rent rent = mockRentService.findRentById(100);
		Mockito.doNothing().when(mockRentService).rejectRent(rent);
		mockMvc.perform(post("/rejectRent")
		.param("rentId", "100"))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/ownProducts"));
	}
	
	
	@Test
	@WithMockUser
	public void test_confirmRent() throws Exception {
		Rent rent = mockRentService.findRentById(100);
		Mockito.doNothing().when(mockRentService).confirmRent(rent);
		mockMvc.perform(post("/confirmRent")
		.param("rentId", "100"))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/ownProducts"));
	}
	
	@Test
	@WithMockUser
	public void test_confirmReturn() throws Exception {
		Rent rent = mockRentService.findRentById(100);
		Mockito.doNothing().when(mockRentService).confirmReturn(rent);
		mockMvc.perform(post("/confirmReturn")
		.param("rentId", "100"))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/ownProducts"));
	}
	
}
