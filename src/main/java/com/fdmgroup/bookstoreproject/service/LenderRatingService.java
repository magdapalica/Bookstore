package com.fdmgroup.bookstoreproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.bookstoreproject.model.LenderRating;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.repository.LenderRatingRepository;

@Service
public class LenderRatingService {

	@Autowired
	private LenderRatingRepository repo;

	public void rateLender(LenderRating rating) {
		repo.deleteByLenderAndAuthor(rating.getLender(), rating.getAuthor());
		repo.save(rating);
	}

	public List<LenderRating> findLenderRatings(User lender) {
		return repo.findByLender(lender);
	}
}