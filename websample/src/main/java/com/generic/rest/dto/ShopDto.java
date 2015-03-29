package com.generic.rest.dto;

public class ShopDto {

	private String shopId;
	private String shopName;
	private String shopAddress;
	private String shopType;
	private String email;
	private String phoneNumber;
	private String ownerName;
	private String tags;
	
	public ShopDto() {
	}

	public ShopDto(String shopId, String shopName) {
		this.shopId = shopId;
		this.shopName = shopName;
	}
	
	public ShopDto(String shopId, String shopName, String shopAddress, String shopType, String email, String phoneNumber, String ownerName, String tags) {
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.shopType = shopType;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.ownerName = ownerName;
		this.tags = tags;
	}

	@Override
	public String toString() {
		return shopId;
	}
	
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
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
	
}
