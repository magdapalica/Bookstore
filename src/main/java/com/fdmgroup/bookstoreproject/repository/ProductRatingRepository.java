package com.fdmgroup.bookstoreproject.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.ProductRating;
import com.fdmgroup.bookstoreproject.model.User;

@Repository
public interface ProductRatingRepository extends JpaRepository<ProductRating, Integer> {

	public List<ProductRating> findByProduct(Product product);

	@Transactional
	public List<ProductRating> deleteByProductAndAuthor(Product product, User author);
	
//	@Query("""
//			SELECT  * FROM PRODUCT INNER JOIN PRODUCT_RATING 
//ON PRODUCT.PRODUCT_ID = PRODUCT_RATING .PRODUCT_PRODUCT_ID 
//			""")

//	public List<ProductRating> getTop10();
	
	public List<ProductRating> findAll();
	
	
}
