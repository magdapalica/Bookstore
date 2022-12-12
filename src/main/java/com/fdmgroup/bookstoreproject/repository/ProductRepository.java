package com.fdmgroup.bookstoreproject.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.ProductRating;
//import com.fdmgroup.bookstoreproject.model.Rating;
import com.fdmgroup.bookstoreproject.model.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("""
			SELECT p
			FROM Product p
			WHERE (UPPER(TITLE) LIKE '%' || UPPER(?1) || '%'
			       OR UPPER(description) LIKE '%' || UPPER(?1) || '%'
			       OR UPPER(author) LIKE '%' || UPPER(?1) || '%'
			       OR UPPER(category) LIKE '%' || UPPER(?1) || '%')
			  AND ?2 in ('', author)
			  AND ?3 in ('', category)
			  AND ?4 in ('', title)
			  AND price <= ?5
			 
			  """)

	public List<Product> findProducts(String search, String author, String category, String title, double maxPrice);

	@Query("SELECT DISTINCT author FROM Product p")
	public List<String> allAuthor();

	@Query("SELECT DISTINCT category FROM Product p")
	public List<String> allCategories();
	
	@Query("SELECT DISTINCT title FROM Product p")
	public List<String> allTitles();

	public List<Product> findByOwner(User owner);

}
