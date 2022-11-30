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
//import com.fdmgroup.shazar.model.LenderRating;
//
//import com.fdmgroup.shazar.model.User;
//import com.fdmgroup.shazar.repository.LenderRatingRepository;
//
//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//
//public class LenderRatingServiceTest {
//
//	@InjectMocks
//	LenderRatingService lenderRatingService;
//	@MockBean
//	LenderRatingRepository mockLenderRatingRepository;
//	
//	
//
//	@Test
//	public void test_rateLender() {
//		User lender = new User();
//		User author = new User();
//		LenderRating lenderRating = new LenderRating(lender, 10, author);
//		
//		
//		Mockito.doNothing().when(mockLenderRatingRepository)
//		.deleteByLenderAndAuthor(lenderRating.getLender(), lenderRating.getAuthor());
//		Mockito.doReturn(null).when(mockLenderRatingRepository).save(lenderRating);
//		lenderRatingService.rateLender(lenderRating);
//		
//		verify(mockLenderRatingRepository).save(lenderRating);
//		verify(mockLenderRatingRepository)
//		.deleteByLenderAndAuthor(lenderRating.getLender(), lenderRating.getAuthor());
//	}
//	
//	@Test
//	public void test_findPLenderRatings() {
//		User lender = new User();
//		User author = new User();
//		LenderRating lenderRating = new LenderRating(lender, 10, author);
//		
//		List<LenderRating> ratingList = new ArrayList<>();
//		ratingList.add(lenderRating);
//		Mockito.doReturn(ratingList).when(mockLenderRatingRepository).findByLender(lender);
//		
//		List<LenderRating> ratingServiceList= lenderRatingService.findLenderRatings(lender);
//		
//		verify(mockLenderRatingRepository).findByLender(lender);
//		Assertions.assertEquals(ratingServiceList, ratingList);
//	}
//	
//}