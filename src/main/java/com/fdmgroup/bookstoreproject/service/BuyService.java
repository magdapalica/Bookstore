package com.fdmgroup.bookstoreproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.bookstoreproject.model.Product;
import com.fdmgroup.bookstoreproject.model.Buy;
import com.fdmgroup.bookstoreproject.model.User;
import com.fdmgroup.bookstoreproject.repository.BuyRepository;

@Service
public class BuyService {

	@Autowired
	private BuyRepository repo;

	public void createNewBuy(Buy buy) {
		repo.save(buy);
	}

	public boolean userHasBuyedProduct(User buyer, Product product) {
		return repo.userHasBuyedProduct(buyer, product);
	}

	public List<Buy> findRequestedBuyForUser(User buyer) {
		return filterBuyByOwner(repo.findBuyByStatus(0), buyer);
	}

	public List<Buy> findConfirmedBuyForUser(User buyer) {
		return filterBuyByOwner(repo.findBuyByStatus(1), buyer);

	}

	public List<Buy> findUnfinishedBuy(User buyer) {
		List<Integer> statuses = new ArrayList<>();
		statuses.add(0);
		statuses.add(1);
		return filterBuyByOwner(repo.findBuyByStatusIn(statuses), buyer);
	}

	private static List<Buy> filterBuyByOwner(List<Buy> buy, User buyer) {
		return buy.stream().filter(r -> r.getProduct().getOwner() == buyer).collect(Collectors.toList());
	}

	public Buy findBuyById(int id) {
		return repo.findById(id);
	}

	public void rejectBuy(Buy buy) {
		repo.deleteById(buy.getId());
	}

	public void confirmBuy(Buy buy) {
		buy.setStatus(1);
		repo.save(buy);
	}

}
