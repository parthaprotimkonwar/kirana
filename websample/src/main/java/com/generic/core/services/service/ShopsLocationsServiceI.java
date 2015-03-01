package com.generic.core.services.service;

import java.util.List;
import java.util.Map;

import com.generic.rest.dto.LocationDto;
import com.generic.rest.dto.ShopDto;
import com.generic.rest.dto.ShopLandmarkDto;

public interface ShopsLocationsServiceI {
	
	public List<ShopDto> findShopsByLocation(String locationId);
	
	public List<ShopDto> findShopsByLocationAndShopType(String landmarkId, String shopType);
	
	public List<LocationDto> findShopTypeByLocation(String locationId);
	
	public Map<String, ShopLandmarkDto> findShopsConcadinatedWithLocation(String cityId);

}
