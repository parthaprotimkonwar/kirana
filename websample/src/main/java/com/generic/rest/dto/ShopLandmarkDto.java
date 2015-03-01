package com.generic.rest.dto;

import java.util.List;

public class ShopLandmarkDto {

	private String shopName;
	private List<LandmarkDto> locations;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<LandmarkDto> getLocations() {
		return locations;
	}

	public void setLocations(List<LandmarkDto> locations) {
		this.locations = locations;
	}

}
