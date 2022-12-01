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
public class Rating {

	@Id
	@GeneratedValue
	@Column(name = "rating_id")
	private Integer id;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(targetEntity = User.class)
	private User lender;
	private int rating;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(targetEntity = User.class)
	private User author;

	public Rating() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, lender, rating);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		return Objects.equals(author, other.author) && Objects.equals(lender, other.lender) && rating == other.rating;
	}

	public User getLender() {
		return lender;
	}

	public void setLender(User lender) {
		this.lender = lender;
	}

	public Rating(User lender, int rating, User author) {
		super();
		this.lender = lender;
		this.rating = rating;
		this.author = author;
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
