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
public class Buy {

	@Id
	@GeneratedValue
	@Column(name = "buy_id")
	private Integer id;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(targetEntity = User.class)
	private User buyer;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(targetEntity = Product.class)
	private Product product;

	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Buy() {
	};

	public Buy(User buyer, Product product) {
			//Date start, Date end) {
		super();
		this.buyer = buyer;
		this.product = product;
		this.status = 0;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(buyer, id, product, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Buy other = (Buy) obj;
		return Objects.equals(buyer, other.buyer) && Objects.equals(id, other.id)
				&& Objects.equals(product, other.product) && status == other.status;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Integer getId() {
		return id;
	}

}
