package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Location;
import com.generic.core.model.entities.Shops;
import com.generic.core.model.entities.ShopsLocations;
import com.generic.core.respository.ShopsLocationsRepository;
import com.generic.core.services.service.ShopsLocationsServiceI;
import com.generic.core.utilities.UtilConstants;
import com.generic.rest.dto.LandmarkDto;
import com.generic.rest.dto.LocationDto;
import com.generic.rest.dto.ShopDto;
import com.generic.rest.dto.ShopLandmarkDto;

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
	
	@Override
	public List<ShopDto> findShopsByLocationAndShopType(String locationId, String shopType) {
		Location aLocation = new Location(locationId);
		List<ShopsLocations> shopsLocations = shopsLocationRepository.findByShopIdLocationIdLocation(aLocation);
		return convertToShopDtoAfterFilteringOnShopType(shopsLocations, shopType);
	}

	@Override
	public List<LocationDto> findShopTypeByLocation(String locationId) {
		Location aLocation = new Location(locationId);
		List<ShopsLocations> shopsLocations = shopsLocationRepository.findByShopIdLocationIdLocation(aLocation);
		return convertToShopType(shopsLocations);
	}

	@Override
	public Map<String, ShopLandmarkDto> findShopsConcadinatedWithLocation(String cityId) {
		
		Location aLocation = new Location(cityId);
		List<ShopsLocations> shopsLocations = shopsLocationRepository.findByShopIdLocationIdLocationParentLocationParentLocation(aLocation);
		return concadenateLocationAndShop(shopsLocations);
	}
	
	private Map<String, ShopLandmarkDto> concadenateLocationAndShop(List<ShopsLocations> shopsLocations) {
		
		List<ShopDto> shops = new ArrayList<ShopDto>();
		Map<String, ShopLandmarkDto> landmarkCache = new HashMap<String, ShopLandmarkDto>();
		
		for(ShopsLocations aShopLocation : shopsLocations) {
			Shops aShop = aShopLocation.getShopIdLocationId().getShops();
			Location landmarkLocation = aShopLocation.getShopIdLocationId().getLocation();
			Location areaLocation = landmarkLocation.getParentLocation();
			if(landmarkCache.containsKey(aShop.getShopId())) {
				ShopLandmarkDto aShopLocationDto = landmarkCache.get(aShop.getShopId());
				List<LandmarkDto> shopsLandmark = aShopLocationDto.getLocations();
				shopsLandmark.add(new LandmarkDto(landmarkLocation.getLocationId(), landmarkLocation.getLocationName(), areaLocation.getLocationName()));
			} else {
				ShopLandmarkDto aShopLandmarkDto = new ShopLandmarkDto();
				List<LandmarkDto> shopsLandmark = new ArrayList<LandmarkDto>();
				shopsLandmark.add(new LandmarkDto(landmarkLocation.getLocationId(), landmarkLocation.getLocationName(), areaLocation.getLocationName()));
				aShopLandmarkDto.setShopName(aShop.getShopName()); aShopLandmarkDto.setLocations(shopsLandmark);
				landmarkCache.put(aShop.getShopId(), aShopLandmarkDto);
			}
		}
		return landmarkCache;
	}
	
	private List<LocationDto> convertToShopType(List<ShopsLocations> shopsLocation) {
		List<LocationDto> locationDtoList = new ArrayList<LocationDto>();
		Set<LocationDto> shopTypeSet = new HashSet<LocationDto>();
		for(ShopsLocations aShopsLocation : shopsLocation) {
			String shopType = aShopsLocation.getShopIdLocationId().getShops().getShopType();
			String[] shopTypeArray = shopType.split(UtilConstants.DELIMETER_BAR);
			for(String aShopType : shopTypeArray) {
				LocationDto aLocationDto = new LocationDto(aShopType, aShopType);
				shopTypeSet.add(aLocationDto);
			}
		}
		locationDtoList.addAll(shopTypeSet);
		return locationDtoList;
	}
	
	private List<ShopDto> convertToShopDtoAfterFilteringOnShopType(List<ShopsLocations> shopLocations, String shopType) {
	
		List<ShopDto> shopList = new ArrayList<ShopDto>();
		for(ShopsLocations aShopLocation : shopLocations) {
			Shops aShop = aShopLocation.getShopIdLocationId().getShops();
			if(shopTypeMatch(aShop.getShopType(), shopType))
				shopList.add(new ShopDto(aShop.getShopId(), aShop.getShopName()));
		}
		return shopList;
	}
	
	private Boolean shopTypeMatch(String actualShopType, String requiredShopType) {
		
		String[] supportedShopTypeList = actualShopType.split(UtilConstants.DELIMETER_BAR);
		for(String aShopType : supportedShopTypeList) {
			if(aShopType.equalsIgnoreCase(requiredShopType))
				return true;
		}
		return false;
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
