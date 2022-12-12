package com.fdmgroup.bookstoreproject.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.bookstoreproject.exception.ProductNotFoundException;
import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.ProductRating;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	public List<Product> findAllProducts() {
		return repo.findAll();
	}

	public void saveProduct(Product product) {
		repo.save(product);
	}

	public List<Product> findProducts(String text, String author, String category, String title, double maxPrice) {
		return repo.findProducts(text, author, category, title, maxPrice);
	}

	public void createNewProduct(Product product) {
		repo.save(product);
	}

	public Product findProductbyId(int id) throws ProductNotFoundException {
		Optional<Product> optPlace = repo.findById(id);
		return optPlace.orElseThrow(() -> new ProductNotFoundException(id));
	}

	public void deleteProduct(int id) {
		repo.deleteById(id);
	}

	public List<String> allAuthors() {
		return repo.allAuthors();
	}

	public List<String> allCategories() {
		return repo.allCategories();
	}
	
	public List<String> allTitles() {
		return repo.allTitles();
	}

	public List<Product> findByOwner(User owner) {
		return repo.findByOwner(owner);
	}

	
}
