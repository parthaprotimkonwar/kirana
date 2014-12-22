package com.generic.rest.dto;

public class ShopDto {

	private String shopId;
	private String shopName;
	
	public ShopDto() {
	}

	public ShopDto(String shopId, String shopName) {
		this.shopId = shopId;
		this.shopName = shopName;
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
	
}
