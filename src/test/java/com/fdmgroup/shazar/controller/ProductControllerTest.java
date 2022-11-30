//package com.fdmgroup.shazar.controller;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.fdmgroup.shazar.App;
//import com.fdmgroup.shazar.exception.ProductNotFoundException;
////import com.fdmgroup.shazar.model.LenderRating;
//import com.fdmgroup.shazar.model.Product;
////import com.fdmgroup.shazar.model.ProductRating;
//import com.fdmgroup.shazar.model.Role;
//import com.fdmgroup.shazar.model.User;
//import com.fdmgroup.shazar.security.DefaultUserDetailsService;
////import com.fdmgroup.shazar.service.LenderRatingService;
////import com.fdmgroup.shazar.service.ProductRatingService;
//import com.fdmgroup.shazar.service.ProductService;
//import com.fdmgroup.shazar.service.RentService;
//import com.fdmgroup.shazar.service.RoleService;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ContextConfiguration(classes = App.class)
//public class ProductControllerTest {
//
//	@Autowired
//	MockMvc mockMvc;
//	@Autowired
//	WebApplicationContext webApplicationContext;
//	
//	@MockBean
//	ProductService mockService;
//	@MockBean
//	RoleService roleService;
//	@MockBean
//	LoginAndRegisterController userService;
////	@MockBean
////	LenderRatingService mockLenderRatingService;
////	@MockBean
////	ProductRatingService mockProductRatingService;
//	@MockBean
//	DefaultUserDetailsService mockUserService;
//	@MockBean
//	RentService mockRentService;
//
//	List<Product> expectedProductsList;
//	Product expectedProduct;
//
//	@BeforeEach
//	public void init() {
//		expectedProductsList = new ArrayList<>();
//		Role role = roleService.findByRoleName("User");
//		User user = new User("Alice", "a", role);
//		userService.registerNewUser(user);
//		expectedProductsList.add(new Product("type", "description", "red", "bike", 10D, user));
//		expectedProductsList.add(new Product("type1", "description1", "green", "bike1", 11D, user));
//		expectedProductsList.add(new Product("type2", "description2", "blue", "bike2", 12D, user));
//		expectedProduct = new Product("type1", "description1", "red", "bike", 10, user);
//	}
//
//	@Test
//	public void test_goToHomePage() throws Exception {
//		when(mockService.findAllProducts()).thenReturn(expectedProductsList);
//		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
//				.andExpect(model().attribute("products", expectedProductsList));
//	}
//	
//	
//	@Test
//	@WithMockUser
//	public void test_addNewProductFirst() throws Exception {
//		mockMvc.perform(get("/add"))
//		.andExpect(status().isOk())
//		.andExpect(view().name("add"));
//	}
//
//	@Test
//	@WithMockUser
//	public void test_addNewProductSecond() throws Exception {
//		
//		MockMultipartFile file 
//	      = new MockMultipartFile(
//	        "file", 
//	        "hesssllo.txt", 
//	        MediaType.TEXT_PLAIN_VALUE, 
//	        "Hello, World!".getBytes()
//	      );
//		
//		User renter = mockUserService.getCurrentUser(Mockito.any(Authentication.class));
//		Mockito.doReturn(renter).when(mockUserService).getCurrentUser(Mockito.any(Authentication.class));
//		Mockito.doNothing().when(mockService).saveProduct(expectedProduct);
//		mockMvc.perform(MockMvcRequestBuilders.multipart("/add")
//				.file(file)
//				.param("type", "type")
//				.param("description", "description")
//				.param("color", "red")
//				.param("category","bike")
//				.param("price", "10"))
//				.andExpect(view().name("redirect:/add"));
////				.andExpect(model().attribute("confirmation", "File successfully uploaded"));
//
//	}		
//		
//
//	@Test
//	@WithMockUser
//	public void test_goToDetails() throws Exception {
//		User user = new User();
//		when(mockService.findProductbyId(200)).thenReturn(expectedProduct);
////		List<ProductRating> productRatings= mockProductRatingService.findProductRatings(expectedProduct);
////		double productRating = productRatings.stream().mapToDouble(r -> r.getRating()).average().orElse(0);
////		List<LenderRating> lenderRatings= mockLenderRatingService.findLenderRatings(user);
////		double lenderRating = lenderRatings.stream().mapToDouble(r -> r.getRating()).average().orElse(0);
//		
//		Mockito.doReturn(user).when(mockUserService).getCurrentUser(Mockito.any(Authentication.class));
//		Mockito.doReturn(true).when(mockRentService).userHasRentedProduct(user, expectedProduct);
//		
//		
//		when(mockService.findProductbyId(200)).thenReturn(expectedProduct);
////		Mockito.doReturn(productRatings).when(mockProductRatingService).findProductRatings(expectedProduct);
////		Mockito.doReturn(lenderRatings).when(mockLenderRatingService).findLenderRatings(expectedProduct.getOwner());
//
//		
//		mockMvc.perform(get("/products/200"))
//		.andExpect(status().isOk()).andExpect(view().name("details"))
//		.andExpect(model().attribute("product", expectedProduct))
////		.andExpect(model().attribute("productRating", productRating))
////		.andExpect(model().attribute("lenderRating", lenderRating))
////		.andExpect(model().attribute("lenderVotes", lenderRatings.size()))
////		.andExpect(model().attribute("productVotes", productRatings.size()))
//		.andExpect(model().attribute("userHasRentedProduct", true));
//		
//		
//	}
//
//	@Test
//	@WithMockUser
//	public void test_goToDetailsThrowsPlaceNotFoundException() throws Exception {
//		when(mockService.findProductbyId(123)).thenThrow(new ProductNotFoundException(123));
//		mockMvc.perform(get("/products/123")).andExpect(status().isNotFound()).andExpect(view().name("productNotFound"))
//				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
//				.andExpect(model().attribute("message", "The product with id 123 is not available."));
//	}
//
//	@Test
//	@WithMockUser
//	public void test_deleteProduct() throws Exception {
//	 Mockito.doNothing().when(mockService).deleteProduct(Mockito.eq(200));
//		mockMvc.perform(post("/deleteProduct").param("id", "200"))
//		.andExpect(view().name("redirect:/")).andExpect(status().is3xxRedirection());
//		verify(mockService, times(1)).deleteProduct(200);
//	}
//	
//	@Test
//	public void test_productList() throws Exception {
//		Mockito.doReturn(expectedProductsList).when(mockService)
//		.findProducts("name", "red", "bike", 10d, Date.valueOf("2022-11-22"), Date.valueOf("2022-11-23"));
//		mockMvc.perform(get("/productList")
//		.param("search", "name")
//		.param("color","red")
//		.param("category", "bike")
//		.param("maxPrice","10")
//		.param("startDate", "2022-11-22")
//		.param("endDate", "2022-11-23"))
//		.andExpect(view().name("productList"))
//		.andExpect(status().isOk())
//		.andExpect(model().attribute("products", expectedProductsList));
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_productBooking() throws Exception {
//		Mockito.doReturn(expectedProduct).when(mockService).findProductbyId(100);
//		Mockito.doReturn(true).when(mockService).getProductAvailability
//				(expectedProduct ,Date.valueOf("2022-11-22"), Date.valueOf("2022-11-23"));
//		mockMvc.perform(get("/productBooking")
//		.param("productId", "100")
//		.param("startDate", "2022-11-22")
//		.param("endDate", "2022-11-23"))
//		.andExpect(view().name("productBooking"))
//		.andExpect(status().isOk())
//		.andExpect(model().attribute("available", true))
//		.andExpect(model().attribute("price", 20.0));
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_Chat() throws Exception {
//		mockMvc.perform(get("/chat"))
//		.andExpect(status().isOk())
//		.andExpect(view().name("chat"));
//	}
//	
//}
