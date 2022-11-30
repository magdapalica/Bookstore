//package com.fdmgroup.shazar.controller;
//
//import org.junit.jupiter.api.Test;
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
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import java.util.Optional;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//import com.fdmgroup.shazar.App;
//import com.fdmgroup.shazar.model.LenderRating;
//import com.fdmgroup.shazar.model.User;
//import com.fdmgroup.shazar.security.DefaultUserDetailsService;
//import com.fdmgroup.shazar.service.LenderRatingService;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ContextConfiguration(classes = App.class)
//
//public class LenderRatingControllerTest {
//
//	@Autowired
//	MockMvc mockMvc;
//	
//	@MockBean
//	DefaultUserDetailsService mockUserService;
//	
//	@MockBean
//	LenderRatingService mockLenderRatingService;
//	
//	// to be fixed
//	@Test
//	@WithMockUser
//	public void test_rateLender() throws Exception {
//		User author = new User();
//		Mockito.doReturn(author).when(mockUserService).getCurrentUser(Mockito.any(Authentication.class));
//		User lender = new User();
//		Mockito.doReturn(Optional.of(lender)).when(mockUserService).findById(100);
//		Mockito.doNothing().when(mockLenderRatingService).rateLender(new LenderRating());
//		mockMvc.perform(get("/rateLender")
//				.param("productId", "100")
//				.param("lenderId", "100")
//				.param("rating", "5"))
//		.andExpect(status().isFound())
//		.andExpect(view().name("redirect:/products/100"));
//		
//	}
//	
//}
