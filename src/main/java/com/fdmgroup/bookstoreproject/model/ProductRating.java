package com.fdmgroup.bookstoreproject.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ProductRating {

	@Id
	@GeneratedValue
	@Column(name = "rating_id")
	private Integer id;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(targetEntity = Product.class)
	private Product product;
	private int rating;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(targetEntity = User.class)
	private User author;

	public ProductRating() {
		super();
	}

	public ProductRating(Product product, int rating, User author) {
		super();
		this.product = product;
		this.rating = rating;
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, product, rating);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductRating other = (ProductRating) obj;
		return Objects.equals(author, other.author) && Objects.equals(product, other.product) && rating == other.rating;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
