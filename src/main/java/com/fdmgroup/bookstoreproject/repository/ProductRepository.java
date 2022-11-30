package com.fdmgroup.bookstoreproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("""
			SELECT p
			FROM Product p
			WHERE (UPPER(TYPE) LIKE '%' || UPPER(?1) || '%'
			       OR UPPER(description) LIKE '%' || UPPER(?1) || '%'
			       OR UPPER(author) LIKE '%' || UPPER(?1) || '%'
			       OR UPPER(category) LIKE '%' || UPPER(?1) || '%')
			  AND ?2 in ('', author)
			  AND ?3 in ('', category)
			  AND price <= ?4
			 
			  """)

	public List<Product> findProducts(String text, String author, String category, double maxPrice);

	@Query("SELECT DISTINCT author FROM Product p")
	public List<String> allAuthor();

	@Query("SELECT DISTINCT category FROM Product p")
	public List<String> allCategories();
	
	@Query("SELECT DISTINCT title FROM Product p")
	public List<String> allTitles();

	@Query("""
			SELECT CASE WHEN COUNT(r) = 0 THEN true ELSE false END
			FROM Buy b
			WHERE b.product = ?1
			""")
//			AND r.start <= ?3
//			AND r.end >= ?2
			

	public boolean getProductAvailability(Product product);

	public List<Product> findByOwner(User owner);
}