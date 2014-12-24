package com.generic.core.services.service;

import java.util.List;

import com.generic.core.model.entities.Shops;
import com.generic.rest.dto.ShopDto;

public interface ShopsServiceI {

	List<Shops> findAllShops();

	//List<ShopDto> findShopsForLocation(String locationId);
	
}
