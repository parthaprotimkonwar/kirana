package com.generic.core.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SHOPS_ITEMS", schema="factory")
public class ShopsItems implements Serializable {

	private static final long serialVersionUID = 1L;

	public ShopsItems() {
	}
	
	public ShopsItems(ShopIdItemId shopIdItemId) {
		this.shopIdItemId = shopIdItemId;
	}
	
	public ShopsItems(ShopIdItemId shopIdItemId, String price, String discount, String status) {
		this.shopIdItemId = shopIdItemId;
		this.price = price;
		this.discount = discount;
		this.status = status;
	}
	
	public ShopsItems(ShopIdItemId shopIdItemId, String price, String discount, String status, Size size) {
		this.shopIdItemId = shopIdItemId;
		this.price = price;
		this.discount = discount;
		this.status = status;
		this.size = size;
	}
	
	@EmbeddedId
	private ShopIdItemId shopIdItemId;
	
	@Column(name="PRICE", length=20)
	private String price;
	
	@Column(name="DISCOUNT", length=10)
	private String discount;
	
	@Column(name="STATUS", length=20)
	private String status;

	//ManyToOne is the foreign key
	@ManyToOne
	@JoinColumn(name="SIZE_ID")
	private Size size;

	public ShopIdItemId getShopIdItemId() {
		return shopIdItemId;
	}

	public void setShopIdItemId(ShopIdItemId shopIdItemId) {
		this.shopIdItemId = shopIdItemId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}
	
}
