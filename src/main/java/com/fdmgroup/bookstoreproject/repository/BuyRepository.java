package com.fdmgroup.bookstoreproject.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.Buy;
import com.fdmgroup.bookstoreproject.model.User;

@Repository
public interface BuyRepository extends JpaRepository<Buy, Integer> {

	public Buy findById(int id);

	@Query("""
			SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
			FROM Buy
			WHERE buyer = ?1
			  AND product = ?2
			  
			
			""")
	
//	@Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END\n" +
//			"FROM Buy\n" +
//			"WHERE buyer = ?1\n" +
//			"  AND product = ?2\n" +
//			"\n", nativeQuery = true)

	
	public boolean userHasBuyedProduct(User user, Product product);

	public List<Buy> findBuyByStatus(int status);

	public List<Buy> findBuyByStatusIn(Collection<Integer> statuses);
}
