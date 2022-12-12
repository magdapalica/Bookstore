package com.fdmgroup.bookstoreproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.ProductRating;
import com.fdmgroup.bookstoreproject.model.Buy;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.repository.BuyRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class BuyServiceTest {

	
	@InjectMocks
	BuyService buyService;
	
	@MockBean
	BuyRepository mockBuyRepository;
	
	@Captor
	ArgumentCaptor<Buy> buyArgumentCaptor;
	
	@Test
	public void test_createNewBuy() {
		Buy buy = new Buy();
		Mockito.doReturn(null).when(mockBuyRepository).save(buy);
		buyService.createNewBuy(buy);
		verify(mockBuyRepository).save(buy);
	}
	
	@Test
	public void test_userHasBuyedProduct() {
		User user = new User();
		Product product = new Product();
		
		Mockito.doReturn(true).when(mockBuyRepository)
		.userHasBuyedProduct(user, product);
		boolean trueService = buyService.userHasBuyedProduct(user, product);
		
		verify(mockBuyRepository).userHasBuyedProduct(user, product);
		Assertions.assertEquals(true, trueService);
	}
	
	@Test
	public void test_findConfirmedBuyForUser() {
		List<Buy> buyList = new ArrayList<>();
		User user1 = new User("username1","password1","firstName1","lastName","email",11111L,null);
		User user2 = new User("username2","password2","firstName2","lastName","email",11111L,null);
		Product product1 = new Product("title1","description", "author", "category", 11111D, user1);
		Product product2 = new Product("title2","description2", "author2", "category2", 11111D, user2);
		Buy buy1 = new Buy(user1,product1);
		Buy buy2 = new Buy(user2,product2);
		buyList.add(buy1);
		buyList.add(buy2);
		
		Mockito.doReturn(buyList).when(mockBuyRepository).findBuyByStatus(1);
		
		 List<Buy> buyListService = buyService.findConfirmedBuyForUser(user1);
	
		verify(mockBuyRepository).findBuyByStatus(1);
		Assertions.assertEquals(List.of(buy1), buyListService);
		
	}
	
	
	@Test
	public void test_findBuyById() {
		Buy buy = new Buy();
		Mockito.doReturn(buy).when(mockBuyRepository).findById(100);
		Buy buyFromService = buyService.findBuyById(100);
		verify(mockBuyRepository).findById(100);
		Assertions.assertEquals(buy, buyFromService);
	}
	
	@Test
	public void test_rejectBuy() {
		Buy buy = new Buy();
		Mockito.doNothing().when(mockBuyRepository).deleteById(buy.getId());
		buyService.rejectBuy(buy);
		verify(mockBuyRepository).deleteById(buy.getId());
		
	}
	
	@Test
	
	public void test_confirmBuy() {
		Mockito.doReturn(null).when(mockBuyRepository).save(Mockito.any());
		buyService.confirmBuy(new Buy());
		verify(mockBuyRepository).save(buyArgumentCaptor.capture());
		Buy value = buyArgumentCaptor.getValue();
		Assertions.assertEquals(1, value.getStatus());
	}
	
	
	@Test
	public void test_findRequestedBuyForUser() {
		List<Buy> buyList = new ArrayList<>();
		User user1 = new User("username1","password1","firstName1","lastName","email",11111L,null);
		User user2 = new User("username2","password2","firstName2","lastName","email",11111L,null);
		Product product1 = new Product("title1","description", "author", "category", 11111D, user1);
		Product product2 = new Product("title2","description2", "author2", "category2", 11111D, user2);
		Buy buy1 = new Buy(user1,product1);
		Buy buy2 = new Buy(user2,product2);
		buyList.add(buy1);
		buyList.add(buy2);
		
		Mockito.doReturn(buyList).when(mockBuyRepository).findBuyByStatus(0);
		
		 List<Buy> buyListService = buyService.findRequestedBuyForUser(user1);
	
		verify(mockBuyRepository).findBuyByStatus(0);
		Assertions.assertEquals(List.of(buy1), buyListService);
		
	}
	@Test
	public void test_findUnfinishedBuy() {
		List<Buy> buyList = new ArrayList<>();
		User user1 = new User("username1","password1","firstName1","lastName","email",11111L,null);
		User user2 = new User("username2","password2","firstName2","lastName","email",11111L,null);
		Product product1 = new Product("title1","description", "author", "category", 11111D, user1);
		Product product2 = new Product("title2","description2", "author2", "category2", 11111D, user2);
		Buy buy1 = new Buy(user1,product1);
		Buy buy2 = new Buy(user2,product2);
		buyList.add(buy1);
		buyList.add(buy2);
		List<Integer> statuses = new ArrayList<>();
		statuses.add(0);
		statuses.add(1);
		
		Mockito.doReturn(buyList).when(mockBuyRepository).findBuyByStatusIn(statuses);
		List<Buy> buyListService = buyService.findUnfinishedBuy(user1);
		
		verify(mockBuyRepository).findBuyByStatusIn(statuses);
		Assertions.assertEquals(List.of(buy1), buyListService);
		
	}
	
	
}
