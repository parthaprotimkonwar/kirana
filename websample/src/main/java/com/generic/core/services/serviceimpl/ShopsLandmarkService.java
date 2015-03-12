package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Area;
import com.generic.core.model.entities.City;
import com.generic.core.model.entities.Landmark;
import com.generic.core.model.entities.Shops;
import com.generic.core.model.entities.ShopsLandmark;
import com.generic.core.respository.ShopsLandmarkRepository;
import com.generic.core.services.service.ShopsLandmarkServiceI;
import com.generic.rest.dto.LandmarkDto;
import com.generic.rest.dto.ShopLandmarkDto;

@Service
@Transactional
public class ShopsLandmarkService implements ShopsLandmarkServiceI{

	@Resource
	ShopsLandmarkRepository shopsLandmarkRepository;

	@Override
	public Map<String, ShopLandmarkDto> findShopsConcadinatedWithLocation(String cityId) {

		City aCity = new City(cityId);
		List<ShopsLandmark> shopsLandmarks = shopsLandmarkRepository.findByShopIdLandmarkIdLandmarkAreaCity(aCity);
		return concadenateShopsAreaLandmark(shopsLandmarks);
	}
	
	
	private Map<String, ShopLandmarkDto> concadenateShopsAreaLandmark(List<ShopsLandmark> shopsLandmarks) {
		
		Map<String, ShopLandmarkDto> landmarkCache = new HashMap<String, ShopLandmarkDto>();
		
		for(ShopsLandmark aShopLandmark : shopsLandmarks) {

			Shops theShop = aShopLandmark.getShopIdLandmarkId().getShops();
			Landmark theLandmark = aShopLandmark.getShopIdLandmarkId().getLandmark();
			Area theArea = theLandmark.getArea();
			City theCity = theArea.getCity();
			
			if(landmarkCache.containsKey(theShop.getShopId())) {		//shops already in cache
				ShopLandmarkDto aShopLandmarkDto = landmarkCache.get(theShop.getShopId());
				LandmarkDto aLandmark = new LandmarkDto(theLandmark.getLandmarkId(), theLandmark.getLandmarkName(), theArea.getAreaName());
				aShopLandmarkDto.getLocations().add(aLandmark);
			} else {													//first time a shop is been added
				
				List<LandmarkDto> landmarkDtoList = new ArrayList<LandmarkDto>();
				LandmarkDto aLandmark = new LandmarkDto(theLandmark.getLandmarkId(), theLandmark.getLandmarkName(), theArea.getAreaName());
				landmarkDtoList.add(aLandmark);
				ShopLandmarkDto aShopLandmarkDto = new ShopLandmarkDto(theShop.getShopName(),landmarkDtoList);
				landmarkCache.put(theShop.getShopId(), aShopLandmarkDto);
			}
		}
		return landmarkCache;
	}
	
	
}
