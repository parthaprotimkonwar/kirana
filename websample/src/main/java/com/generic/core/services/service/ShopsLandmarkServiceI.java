package com.generic.core.services.service;

import java.util.Map;

import com.generic.rest.dto.ShopLandmarkDto;

public interface ShopsLandmarkServiceI {

	public Map<String, ShopLandmarkDto> findShopsConcadinatedWithLocation(String cityId);
	
}
