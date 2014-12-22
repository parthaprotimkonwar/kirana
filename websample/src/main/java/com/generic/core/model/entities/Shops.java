package com.generic.core.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SHOPS")
public class Shops implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Shops() {}
	
	public Shops(String shopId) {
		this.shopId = shopId;
	}
	
	public Shops(String shopId, String shopName) {
		this.shopId = shopId;
		this.shopName = shopName;
	}

	@Id
	@Column(name="SHOP_ID")
	private String shopId;
	
	@Column(name="SHOP_NAME")
	private String shopName;

	@OneToMany(mappedBy="shopIdItemId.shop")
	private Set<ShopsItems> shopItem;

	@OneToMany(mappedBy="shop")
	private Set<Transactions> transactions;
	
	@OneToMany(mappedBy="shopIdLocationId.shops")
	private Set<ShopsLocations> shopsLocations;

	
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Set<ShopsItems> getShopItem() {
		return shopItem;
	}

	public void setShopItem(Set<ShopsItems> shopItem) {
		this.shopItem = shopItem;
	}

	public Set<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transactions> transactions) {
		this.transactions = transactions;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Set<ShopsLocations> getShopsLocations() {
		return shopsLocations;
	}

	public void setShopsLocations(Set<ShopsLocations> shopsLocations) {
		this.shopsLocations = shopsLocations;
	}

}
