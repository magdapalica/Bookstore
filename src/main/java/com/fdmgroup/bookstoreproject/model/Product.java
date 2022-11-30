package com.fdmgroup.bookstoreproject.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private Integer id;

	public Product() {
	};

	private String title;
	private String description;
	private String author;
	private String category;
	private double price;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(targetEntity = User.class)
	private User owner;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Picture> pictures;

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String color) {
		this.author = color;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product(String title, String description, String author, String category, double price, User owner) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.category = category;
		this.price = price;
		this.owner = owner;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, category, description, id, owner, pictures, price, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(author, other.author) && Objects.equals(category, other.category)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(owner, other.owner) && Objects.equals(pictures, other.pictures)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(title, other.title);
	}
	

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((title == null) ? 0 : title.hashCode());
//		result = prime * result + ((description == null) ? 0 : description.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Product other = (Product) obj;
//		if (title == null) {
//			if (other.title != null)
//				return false;
//		} else if (!title.equals(other.title))
//			return false;
//		if (description == null) {
//			if (other.description != null)
//				return false;
//		} else if (!description.equals(other.description))
//			return false;
//		return true;
//	}

}
