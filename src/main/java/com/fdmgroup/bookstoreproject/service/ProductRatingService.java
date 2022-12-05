package com.fdmgroup.bookstoreproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.ProductRating;
import com.fdmgroup.bookstoreproject.repository.ProductRatingRepository;

@Service
public class ProductRatingService {

	@Autowired
	private ProductRatingRepository repo;

	public void rateProduct(ProductRating rating) {
		repo.deleteByProductAndAuthor(rating.getProduct(), rating.getAuthor());
		repo.save(rating);
	}

	public List<ProductRating> findProductRatings(Product product) {
		return repo.findByProduct(product);
	}
	
//	public List<ProductRating> getTop10(Product product) {
//		return repo.getTop10(product);
//	}
	
}
