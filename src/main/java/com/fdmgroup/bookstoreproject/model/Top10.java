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
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToOne(targetEntity = ProductRating.class)
	private ProductRating productRating;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProductRating getProductRating() {
		return productRating;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, productRating);
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
		return Objects.equals(id, other.id) && Objects.equals(productRating, other.productRating);
	}
	
}
