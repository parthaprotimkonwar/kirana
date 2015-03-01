package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Location;
import com.generic.core.onboarding.exceldto.ExcelLocationDto;
import com.generic.core.respository.LocationRepository;
import com.generic.core.services.service.LocationServiceI;
import com.generic.core.utilities.Util;
import com.generic.core.utilities.UtilConstants;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.LocationDto;
import com.generic.rest.dto.ResponseDto;

@Service
@Transactional
public class LocationService implements LocationServiceI{

	@Resource
	LocationRepository locationRepository;
	
	@Override
	public List<Location> findAllLocations() {
		return locationRepository.findAll();
	}

	@Override
	public List<LocationDto> findAllCities() {
		List<Location> locations =  locationRepository.findByLocationIdLike(UtilConstants.CITY_PREFIX + "%");
		return convertToLocationDto(locations);
	}

	@Override
	public List<LocationDto> findByParentLocation(String locationId) {
		
		Location parentLocation = new Location(locationId);
		List<Location> locations = locationRepository.findByParentLocation(parentLocation);
		return convertToLocationDto(locations);
	}
	
	public List<LocationDto> convertToLocationDto(List<Location> locations) {
		
		List<LocationDto> locationDto = new ArrayList<LocationDto>();
		for(Location aLocation : locations) {
			LocationDto aLocationDto = new LocationDto(aLocation.getLocationId(), aLocation.getLocationName());
			locationDto.add(aLocationDto);
		}
		return locationDto;
	}

	
	/**
	 * Assumption : Location Name will be unique.
	 * Rules for Uploading location data
	 * Excel Syntax : City - Area - Landmark
	 * Rules:
	 * 1. Only Landmark can be entered.
	 * 2. 
	 */
	@Override
	public List<ResponseDto> insertLocations(List<Object> locations) {

		List<Location> location = locationRepository.findByLocationIdLikeOrLocationIdLike(UtilConstants.CITY_PREFIX + "%", UtilConstants.AREA_PREFIX + "%");
		System.out.println(location);
		
		//Map<LocationValue, LocationId> format
		Map<String,String> locationMap = getMapRepresentationOfLocation(location);
		int count = 1;
		List<ResponseDto> response = new ArrayList<ResponseDto>();
		List<String> valuesInserted = new ArrayList<String>();
		
		for(Object anObject : locations) {
			ExcelLocationDto anExcelLocation = (ExcelLocationDto)anObject;
			String cityId = locationMap.get(anExcelLocation.getCityName().toLowerCase());
			String areaId = locationMap.get(anExcelLocation.getAreaName().toLowerCase());
			
			if(cityId == null || cityId.trim().length() == 0 ) { //new city
				Location aLocationEntry = new Location();
				cityId = UtilConstants.CITY_PREFIX + Util.generateUniqueNumber();
				aLocationEntry.setLocationId(cityId);
				aLocationEntry.setLocationName(anExcelLocation.getCityName());
				try {
					locationRepository.save(aLocationEntry);
					valuesInserted.add(aLocationEntry.getLocationId());
				} catch (Exception e) {
					String errorResponse = Util.generateErrorString(count, Constants.LOGGER_WARNING, e.getMessage());
					response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
				}
			} 
			
			if(areaId == null || areaId.trim().length() == 0) {	//new area
				Location aLocationEntry = new Location();
				areaId = UtilConstants.AREA_PREFIX + Util.generateUniqueNumber();
				aLocationEntry.setLocationId(areaId);
				aLocationEntry.setLocationName(anExcelLocation.getAreaName());
				aLocationEntry.setParentLocation(new Location(cityId));
				try {
					locationRepository.save(aLocationEntry);
					valuesInserted.add(aLocationEntry.getLocationId());
				} catch (Exception e) {
					String errorResponse = Util.generateErrorString(count, Constants.LOGGER_WARNING, e.getMessage());
					response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
				}
			}
			
			if(cityId != null && areaId != null) {
				Location aLocationEntry = new Location();
				aLocationEntry.setLocationId(UtilConstants.LANDMARK_PREFIX + Util.generateUniqueNumber());
				aLocationEntry.setLocationName(anExcelLocation.getLandmark());
				aLocationEntry.setParentLocation(new Location(areaId));
				try {
					locationRepository.save(aLocationEntry);
					valuesInserted.add(aLocationEntry.getLocationId());
				} catch (Exception e) {
					String errorResponse = Util.generateErrorString(count, Constants.LOGGER_WARNING, e.getMessage());
					response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
				}
			}
			count++;		
		}
		
		if(!response.isEmpty()) {
			for(String aLocationId : valuesInserted) {
				locationRepository.delete(new Location(aLocationId));
			}
		}
		return response;
	}
	
	/**
	 * LocationMap<LocationValue,LocationId> for getting the locationId entered;
	 * @param locations
	 * @return
	 */
	private Map<String, String> getMapRepresentationOfLocation(List<Location> locations) {
		Map<String, String> locationMap = new HashMap<String, String>();
		for(Location aLocation : locations) {
			locationMap.put(aLocation.getLocationName().toLowerCase(), aLocation.getLocationId());
		}
		return locationMap;
	}
	
	/**
	 * Generated a random Guid number
	 * @return
	 */
	public static String generateGuid(){
		final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	    return uuid.toUpperCase();
	}
	
	public static synchronized long rand() {
		return System.currentTimeMillis();
	}
	public static void main(String[] args) {
		System.out.println(Calendar.getInstance().getTimeInMillis());
		System.out.println(rand());
		System.out.println(rand());
		System.out.println(System.currentTimeMillis());
		System.out.println(System.currentTimeMillis());
		System.out.println(generateGuid());
		System.out.println(UUID.randomUUID());
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
	/*@Override
	public List<ResponseDto> insertLocations(List<Object> locations) {
		//List<ExcelLocationDto> locations
		List<Location> loca = locationRepository.findByLocationIdLikeOrLocationIdLike("LOC_CITY_%", "LOC_AREA_%");
		
		System.out.println(loca);
		for(ExcelLocationDto anExcelLocation : locations) {
			Location aLocation = 
		}
		return null;
	}*/
	
}
