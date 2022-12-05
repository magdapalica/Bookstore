package com.fdmgroup.bookstoreproject.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Top10 {
	
	@Id
	@GeneratedValue
	@Column(name = "top10_id")
	private Integer id;
	private String title;
	private String author;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToOne(targetEntity = ProductRating.class)
	private ProductRating productRatng;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public ProductRating getProductRatng() {
		return productRatng;
	}
	public void setProductRatng(ProductRating productRatng) {
		this.productRatng = productRatng;
	}
	@Override
	public int hashCode() {
		return Objects.hash(author, id, productRatng, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Top10 other = (Top10) obj;
		return Objects.equals(author, other.author) && Objects.equals(id, other.id)
				&& Objects.equals(productRatng, other.productRatng) && Objects.equals(title, other.title);
	}
	
	
	
	
}
