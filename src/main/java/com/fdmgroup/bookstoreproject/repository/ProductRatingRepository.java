package com.fdmgroup.bookstoreproject.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.ProductRating;
import com.fdmgroup.bookstoreproject.model.User;

@Repository
public interface ProductRatingRepository extends JpaRepository<ProductRating, Integer> {

	public List<ProductRating> findByProduct(Product product);

	@Transactional
	public List<ProductRating> deleteByProductAndAuthor(Product product, User author);
	
	

//	public List<ProductRating> getTop10(Product product);
	
}
