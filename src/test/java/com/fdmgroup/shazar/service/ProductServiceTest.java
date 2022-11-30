//package com.fdmgroup.shazar.service;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.fdmgroup.shazar.controller.LoginAndRegisterController;
//import com.fdmgroup.shazar.exception.ProductNotFoundException;
//import com.fdmgroup.shazar.model.Product;
//import com.fdmgroup.shazar.model.Role;
//import com.fdmgroup.shazar.model.User;
//import com.fdmgroup.shazar.repository.ProductRepository;
//
//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//
//public class ProductServiceTest {
//
//	@InjectMocks
//	ProductService productService;
//	@MockBean
//	ProductRepository mockProductRepository;
//	@MockBean
//	RoleService roleService;
//	@MockBean
//	LoginAndRegisterController userService;
//
//	private List<Product> createList() {
//		Role role = roleService.findByRoleName("User");
//		User user = new User("Alice", "a", role);
//		userService.registerNewUser(user);
//		List<Product> expectedProductsList = new ArrayList<>();
//		expectedProductsList.add(new Product("type", "description", "red", "bike", 10D, user));
//		expectedProductsList.add(new Product("type1", "description1", "green", "bike1", 11D, user));
//		expectedProductsList.add(new Product("type2", "description2", "blue", "bike2", 12D, user));
//		return expectedProductsList;
//	}
//
//	@Test
//	public void test_findAllProducts() {
//		List<Product> expectedProductsList = createList();
//		Mockito.doReturn(expectedProductsList).when(mockProductRepository).findAll();
//
//		List<Product> findAllProducts = productService.findAllProducts();
//
//		verify(mockProductRepository, times(1)).findAll();
//		Assertions.assertEquals(expectedProductsList, findAllProducts);
//	}
//
//	@Test
//	public void test_findProducts() {
//		List<Product> expectedProductsList = createList();
//		Mockito.doReturn(expectedProductsList).when(mockProductRepository).findProducts("text", "color", "category",
//				10.0, Date.valueOf("2022-11-22"), Date.valueOf("2022-11-23"));
//
//		List<Product> findProducts = productService.findProducts("text", "color", "category", 10.0,
//				Date.valueOf("2022-11-22"), Date.valueOf("2022-11-23"));
//
//		verify(mockProductRepository).findProducts("text", "color", "category", 10D, Date.valueOf("2022-11-22"),
//				Date.valueOf("2022-11-23"));
//
//		Assertions.assertEquals(expectedProductsList, findProducts);
//	}
//
//	@Test
//	public void test_saveProduct() {
//		Product product = new Product();
//		Mockito.doReturn(null).when(mockProductRepository).save(Mockito.eq(product));
//		productService.saveProduct(product);
//		verify(mockProductRepository).save(Mockito.eq(product));
//	}
//
//	@Test
//	public void test_createNewProduct() {
//		Product product = new Product();
//		Mockito.doReturn(null).when(mockProductRepository).save(Mockito.eq(product));
//		productService.createNewProduct(product);
//		verify(mockProductRepository).save(Mockito.eq(product));
//	}
//
//	@Test
//	public void test_findProductbyId() throws ProductNotFoundException {
//		Product expectedProduct = new Product();
//		Mockito.doReturn(Optional.of(expectedProduct)).when(mockProductRepository).findById(10);
//		Product productById = productService.findProductbyId(10);
//		verify(mockProductRepository).findById(10);
//		Assertions.assertEquals(expectedProduct, productById);
//	}
//
//	@Test
//	public void test_findProductbyId_throwsProductNotFoundException() {
//		Mockito.doReturn(Optional.empty()).when(mockProductRepository).findById(10);
//		Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findProductbyId(10));
//	}
//
//	@Test
//	public void test_deleteProduct() {
//		Mockito.doNothing().when(mockProductRepository).deleteById(10);
//		productService.deleteProduct(10);
//		verify(mockProductRepository).deleteById(10);
//
//	}
//
//	@Test
//	public void test_allColors() {
//		List<String> stringList = new ArrayList<>();
//		stringList.add("test");
//		Mockito.doReturn(stringList).when(mockProductRepository).allColors();
//		List<String> allColorList = productService.allColors();
//		allColorList.add("test");
//		verify(mockProductRepository).allColors();
//		Assertions.assertEquals(allColorList, stringList);
//	}
//
//	@Test
//	public void test_allCategories() {
//		List<String> stringList = new ArrayList<>();
//		stringList.add("test category");
//		Mockito.doReturn(stringList).when(mockProductRepository).allCategories();
//		List<String> allCategories = productService.allCategories();
//		allCategories.add("test category");
//		verify(mockProductRepository).allCategories();
//		Assertions.assertEquals(allCategories, stringList);
//	}
//
//	@Test
//	public void test_getProductAvailability() {
//		Product product = new Product();
//		Mockito.doReturn(true).when(mockProductRepository).getProductAvailability(product, Date.valueOf("2022-11-22"),
//				Date.valueOf("2022-11-23"));
//		boolean productAvailability = productService.getProductAvailability(product, Date.valueOf("2022-11-22"),
//				Date.valueOf("2022-11-23"));
//
//		verify(mockProductRepository).getProductAvailability(product, Date.valueOf("2022-11-22"),
//				Date.valueOf("2022-11-23"));
//
//		Assertions.assertEquals(true, productAvailability);
//	}
//
//	@Test
//	public void test_findByOwner() {
//		productService.findByOwner(null);
//		verify(mockProductRepository).findByOwner(null);
//	}
//
//}