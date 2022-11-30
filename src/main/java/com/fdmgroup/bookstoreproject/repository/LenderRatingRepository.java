package com.fdmgroup.bookstoreproject.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.bookstoreproject.model.LenderRating;
import com.fdmgroup.bookstoreproject.model.User;

@Repository
public interface LenderRatingRepository extends JpaRepository<LenderRating, Integer> {

	public List<LenderRating> findByLender(User lender);

	@Transactional
	public void deleteByLenderAndAuthor(User lender, User author);
}