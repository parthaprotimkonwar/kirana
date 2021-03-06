package com.generic.core.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SHOPS", schema="factory")
public class Shops implements Serializable {

	private static final long serialVersionUID = 1L;

	public Shops() {
	}

	public Shops(String shopId) {
		this.shopId = shopId;
	}

	public Shops(String shopId, String shopName) {
		this.shopId = shopId;
		this.shopName = shopName;
	}

	public Shops(String shopId, String shopName, String shopAddress, String shopType, String email, String phoneNumber, String ownerName, String tags) {
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.shopType = shopType;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.ownerName = ownerName;
		this.tags = tags;
	}
	
	@Id
	@Column(name = "SHOP_ID", length=20)
	private String shopId;

	@Column(name = "SHOP_NAME", length=20)
	private String shopName;

	@Column(name = "SHOP_ADDRESS", length=200)
	private String shopAddress;
	
	@Column(name = "SHOP_TYPE", length=255)
	private String shopType;

	@Column(name = "EMAIL", length=100)
	private String email;

	@Column(name = "PHONE_NUMBER", length=15)
	private String phoneNumber;

	@Column(name = "OWNER_NAME", length=20)
	private String ownerName;
	
	@Column(name = "TAGS", length=255)
	private String tags;

	@OneToMany(mappedBy = "shopIdItemId.shop")
	private Set<ShopsItems> shopItem;

	@OneToMany(mappedBy = "shop")
	private Set<Transactions> transactions;

	@OneToMany(mappedBy = "shopIdLandmarkId.shops")
	private Set<ShopsLandmark> shopsLandmark;
	
	@Override
	public String toString() {
		return "ShopId :" + shopId + "| ShopName : " + shopName;
	}
	
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

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

}
