package com.fdmgroup.shazar.service;

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

import com.fdmgroup.shazar.model.Product;
//import com.fdmgroup.shazar.model.ProductRating;
import com.fdmgroup.shazar.model.Rent;
import com.fdmgroup.shazar.model.User;
import com.fdmgroup.shazar.repository.RentRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class RentServiceTest {

	
	@InjectMocks
	RentService rentService;
	
	@MockBean
	RentRepository mockRentRepository;
	
	@Captor
	ArgumentCaptor<Rent> rentArgumentCaptor;
	
	@Test
	public void test_createNewRent() {
		Rent rent = new Rent();
		Mockito.doReturn(null).when(mockRentRepository).save(rent);
		rentService.createNewRent(rent);
		verify(mockRentRepository).save(rent);
	}
	
	@Test
	public void test_userHasRentedProduct() {
		User user = new User();
		Product product = new Product();
		
		Mockito.doReturn(true).when(mockRentRepository)
		.userHasRentedProduct(user, product);
		boolean trueService = rentService.userHasRentedProduct(user, product);
		
		verify(mockRentRepository).userHasRentedProduct(user, product);
		Assertions.assertEquals(true, trueService);
	}
	
	@Test
	public void test_findConfirmedRentsForUser() {
		List<Rent> rentList = new ArrayList<>();
		User user1 = new User("username1","password1","firstName1","lastName","email",11111L,null);
		User user2 = new User("username2","password2","firstName2","lastName","email",11111L,null);
		Product product1 = new Product("name1","description", "color", "category", 11111D, user1);
		Product product2 = new Product("name2","description2", "color2", "category2", 11111D, user2);
		Rent rent1 = new Rent(user1,product1, Date.valueOf("2022-11-22"),Date.valueOf("2022-11-23"));
		Rent rent2 = new Rent(user2,product2, Date.valueOf("2022-11-22"),Date.valueOf("2022-11-23"));
		rentList.add(rent1);
		rentList.add(rent2);
		
		Mockito.doReturn(rentList).when(mockRentRepository).findRentByStatus(1);
		
		 List<Rent> rentListService = rentService.findConfirmedRentsForUser(user1);
	
		verify(mockRentRepository).findRentByStatus(1);
		Assertions.assertEquals(List.of(rent1), rentListService);
		
	}
	
	
	@Test
	public void test_findRentById() {
		Rent rent = new Rent();
		Mockito.doReturn(rent).when(mockRentRepository).findById(100);
		Rent rentFromService = rentService.findRentById(100);
		verify(mockRentRepository).findById(100);
		Assertions.assertEquals(rent, rentFromService);
	}
	
	@Test
	public void test_rejectRent() {
		Rent rent = new Rent();
		Mockito.doNothing().when(mockRentRepository).deleteById(rent.getId());
		rentService.rejectRent(rent);
		verify(mockRentRepository).deleteById(rent.getId());
		
	}
	
	@Test
	
	public void test_confirmRent() {
		Mockito.doReturn(null).when(mockRentRepository).save(Mockito.any());
		rentService.confirmRent(new Rent());
		verify(mockRentRepository).save(rentArgumentCaptor.capture());
		Rent value = rentArgumentCaptor.getValue();
		Assertions.assertEquals(1, value.getStatus());
	}
	
	@Test
	
	public void test_confirmReturn() {
		Mockito.doReturn(null).when(mockRentRepository).save(Mockito.any());
		rentService.confirmReturn(new Rent());
		verify(mockRentRepository).save(rentArgumentCaptor.capture());
		Rent value = rentArgumentCaptor.getValue();
		Assertions.assertEquals(2, value.getStatus());
	}
	
	
	@Test
	public void test_findRequestedRentsForUser() {
		List<Rent> rentList = new ArrayList<>();
		User user1 = new User("username1","password1","firstName1","lastName","email",11111L,null);
		User user2 = new User("username2","password2","firstName2","lastName","email",11111L,null);
		Product product1 = new Product("name1","description", "color", "category", 11111D, user1);
		Product product2 = new Product("name2","description2", "color2", "category2", 11111D, user2);
		Rent rent1 = new Rent(user1,product1, Date.valueOf("2022-11-22"),Date.valueOf("2022-11-23"));
		Rent rent2 = new Rent(user2,product2, Date.valueOf("2022-11-22"),Date.valueOf("2022-11-23"));
		rentList.add(rent1);
		rentList.add(rent2);
		
		Mockito.doReturn(rentList).when(mockRentRepository).findRentByStatus(0);
		
		 List<Rent> rentListService = rentService.findRequestedRentsForUser(user1);
	
		verify(mockRentRepository).findRentByStatus(0);
		Assertions.assertEquals(List.of(rent1), rentListService);
		
	}
	@Test
	public void test_findUnfinishedRents() {
		List<Rent> rentList = new ArrayList<>();
		User user1 = new User("username1","password1","firstName1","lastName","email",11111L,null);
		User user2 = new User("username2","password2","firstName2","lastName","email",11111L,null);
		Product product1 = new Product("name1","description", "color", "category", 11111D, user1);
		Product product2 = new Product("name2","description2", "color2", "category2", 11111D, user2);
		Rent rent1 = new Rent(user1,product1, Date.valueOf("2022-11-22"),Date.valueOf("2022-11-23"));
		Rent rent2 = new Rent(user2,product2, Date.valueOf("2022-11-22"),Date.valueOf("2022-11-23"));
		rentList.add(rent1);
		rentList.add(rent2);
		List<Integer> statuses = new ArrayList<>();
		statuses.add(0);
		statuses.add(1);
		
		Mockito.doReturn(rentList).when(mockRentRepository).findRentByStatusIn(statuses);
		List<Rent> rentListService = rentService.findUnfinishedRents(user1);
		
		verify(mockRentRepository).findRentByStatusIn(statuses);
		Assertions.assertEquals(List.of(rent1), rentListService);
		
	}
	
	
}
