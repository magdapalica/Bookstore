package com.fdmgroup.bookstoreproject.exception;

public class ProductNotFoundException extends Exception {

	
	public ProductNotFoundException(Integer id) {
		super("The product with id " + id + " is not available.");
	}
}
