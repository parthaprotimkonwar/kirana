package com.generic.core.services.service;

import java.util.List;

import com.generic.rest.dto.ShopDto;

public interface ShopsLocationsServiceI {
	
	public List<ShopDto> findShopsByLocation(String locationId);
	
	public List<ShopDto> findShopsByLocationAndShopType(String landmarkId, String shopType);

}
