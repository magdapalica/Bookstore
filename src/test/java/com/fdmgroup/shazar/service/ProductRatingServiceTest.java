//package com.fdmgroup.shazar.service;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import static org.mockito.Mockito.verify;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.fdmgroup.shazar.model.Product;
//import com.fdmgroup.shazar.model.ProductRating;
//import com.fdmgroup.shazar.model.User;
//import com.fdmgroup.shazar.repository.ProductRatingRepository;
//
//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//
//public class ProductRatingServiceTest {
//
//	@InjectMocks
//	ProductRatingService productRatingService;
//	@MockBean
//	ProductRatingRepository mockProductRatingRepository;
//	
//	
//
//	@Test
//	public void test_rateProduct() {
//		Product product = new Product();
//		User author = new User();
//		ProductRating productRating = new ProductRating(product, 10, author);
//		
//		
//		Mockito.doReturn(null).when(mockProductRatingRepository)
//		.deleteByProductAndAuthor(productRating.getProduct(), productRating.getAuthor());
//		Mockito.doReturn(null).when(mockProductRatingRepository).save(productRating);
//		productRatingService.rateProduct(productRating);
//		
//		verify(mockProductRatingRepository).save(productRating);
//		verify(mockProductRatingRepository)
//		.deleteByProductAndAuthor(productRating.getProduct(), productRating.getAuthor());
//	}
//	
//	@Test
//	public void test_findProductRatings() {
//		Product product = new Product();
//		User author = new User();
//		ProductRating productRating = new ProductRating(product, 10, author);
//		
//		List<ProductRating> ratingList = new ArrayList<>();
//		ratingList.add(productRating);
//		Mockito.doReturn(ratingList).when(mockProductRatingRepository).findByProduct(product);
//		
//		List<ProductRating> ratingServiceList= productRatingService.findProductRatings(product);
//		
//		verify(mockProductRatingRepository).findByProduct(product);
//		Assertions.assertEquals(ratingServiceList, ratingList);
//	}
//	
//}
