package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Location;
import com.generic.core.model.entities.Shops;
import com.generic.core.model.entities.ShopsLocations;
import com.generic.core.respository.ShopsLocationsRepository;
import com.generic.core.services.service.ShopsLocationsServiceI;
import com.generic.rest.dto.ShopDto;

@Service
@Transactional
public class ShopsLocationsService implements ShopsLocationsServiceI{

	@Resource
	ShopsLocationsRepository shopsLocationRepository;

	@Override
	public List<ShopDto> findShopsByLocation(String locationId) {
		
		Location aLocation = new Location(locationId);
		List<ShopsLocations> shopsLocations = shopsLocationRepository.findByShopIdLocationIdLocation(aLocation);
		return convertToShopDto(shopsLocations);
	}
	
	private List<ShopDto> convertToShopDto(List<ShopsLocations> shopsLocations) {
		
		List<ShopDto> shopList = new ArrayList<ShopDto>();
		for(ShopsLocations aShopLocation : shopsLocations) {
			Shops aShop = aShopLocation.getShopIdLocationId().getShops();
			ShopDto aShopDto = new ShopDto(aShop.getShopId(), aShop.getShopName());
			shopList.add(aShopDto);
		}
		return shopList;
	}
}
