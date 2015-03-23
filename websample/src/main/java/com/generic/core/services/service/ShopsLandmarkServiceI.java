package com.generic.core.services.service;

import java.util.Map;

import com.generic.core.model.entities.Landmark;
import com.generic.core.model.entities.ShopIdLandmarkId;
import com.generic.core.model.entities.Shops;
import com.generic.core.model.entities.ShopsLandmark;
import com.generic.rest.dto.ShopLandmarkDto;

public interface ShopsLandmarkServiceI {

	public Map<String, ShopLandmarkDto> findShopsConcadinatedWithLocation(String cityId);
	
	ShopsLandmark saveFlushShopsLandmark(Shops shop, Landmark landmark);
	
	ShopsLandmark saveShopsLandmark(Shops shop, Landmark landmark);
	
	ShopsLandmark saveFlushShopsLandmark(ShopsLandmark aShopLandmark);
	
	ShopsLandmark saveShopsLandmark(ShopsLandmark aShopLandmark);
	
	Boolean shopsLandmarkExist(Shops shop, Landmark landmark);
	
	void deleteShopsLandmark(ShopsLandmark aShopLandmark);
	
	void deleteShopsLandmark(ShopIdLandmarkId shopIdLandmarkId);
	
}
