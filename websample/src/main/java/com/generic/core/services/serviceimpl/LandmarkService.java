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
import com.generic.core.onboarding.exceldto.ExcelLocationDto;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.core.respository.AreaRepository;
import com.generic.core.respository.CityRepository;
import com.generic.core.respository.LandmarkRepository;
import com.generic.core.services.service.LandmarkServiceI;
import com.generic.core.utilities.Util;
import com.generic.core.utilities.UtilConstants;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.ResponseDto;

@Service
@Transactional
public class LandmarkService implements LandmarkServiceI{ 

	@Resource
	LandmarkRepository landmarkRepository;
	
	@Resource
	CityRepository cityRepository;
	
	@Resource
	AreaRepository areaRepository;
	
	@Override
	public List<ResponseDto> onboardLandmarks(ExcelSheetObject excelSheetObject) {
		
		List<Object> locationSheetRows = excelSheetObject.getRows();
		List<ResponseDto> responseDto = new ArrayList<ResponseDto>();					//response message insertion
		
		List<String> cityInsertedIds = new ArrayList<String>();
		List<String> areaInsertedIds = new ArrayList<String>();
		List<String> landmarkInsertedIds = new ArrayList<String>();
		List<ResponseDto> response = new ArrayList<ResponseDto>();
		int count = 1;
		Map<String, String> cityDetails = getCityIdName(excelSheetObject.getExcelSheetName());
		
		if(!cityDetails.containsKey(UtilConstants.CITY_ID)) {
			responseDto.add(new ResponseDto(Constants.LOCATION_ID_NULL_DATA_CODE, Constants.LOCATION_ID_NULL_DATA_MESSAGE));
			return responseDto;
		}
		City aCity = new City(cityDetails.get(UtilConstants.CITY_ID), cityDetails.get(UtilConstants.CITY_NAME));
		if(!cityPresent(aCity.getCityId())) {				//new City so insert it into DB.
			
			try {
				cityRepository.save(aCity);
				cityInsertedIds.add(aCity.getCityId());
			} catch (Exception e) {
				String errorResponse = Util.generateErrorString(count, Constants.LOGGER_WARNING, e.getMessage());
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
			}
		}
		
		for(Object anObject : locationSheetRows) {
			ExcelLocationDto anExcelLocationDto = (ExcelLocationDto)anObject;
			String areaId = anExcelLocationDto.getAreaId();
			String areaName = anExcelLocationDto.getAreaName();
			String landmarkId = anExcelLocationDto.getLandmarkId();
			String landmarkName = anExcelLocationDto.getLandmarkName();
			Area anArea = new Area(areaId, areaName);
			if(!areaPresent(anArea.getAreaId()) && !areaInsertedIds.contains(areaId)) {				//create a new area if does't exist
				try {
					areaRepository.save(anArea);
					areaInsertedIds.add(areaId);
				} catch (Exception e) {
					String errorResponse = Util.generateErrorString(count, Constants.LOGGER_WARNING, e.getMessage());
					response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
				}
			}
			Landmark anLandmark = new Landmark(landmarkId, landmarkName, anArea);
			if(!landmarkPresent(anLandmark.getLandmarkId()) && !landmarkInsertedIds.contains(landmarkId)) {	// insert a new landmark
				try {
					landmarkRepository.save(anLandmark);
					landmarkInsertedIds.add(landmarkId);
				} catch (Exception e) {
					String errorResponse = Util.generateErrorString(count, Constants.LOGGER_WARNING, e.getMessage());
					response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
				}
			}
		}
		
		// delete the ID's if there are some problems.
		if(!response.isEmpty()) {
			for(String aCityInsertedId : cityInsertedIds) {
				cityRepository.delete(new City(aCityInsertedId));
			}
			for(String aAreaInsertedId : areaInsertedIds) {
				areaRepository.delete(new Area(aAreaInsertedId));
			}
			for(String alandmarkInsertedId : landmarkInsertedIds) {
				landmarkRepository.delete(new Landmark(alandmarkInsertedId));
			}
		} else {			// no error send success message
			String successResponse = Constants.SUCCESS_RESPONSE_MESSAGE + ". Records Insserted :" + count;
			response.add(new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, successResponse));
		}
		return response;
	}
	
	
	private Boolean cityPresent(String cityId) {
		return cityRepository.findOne(cityId).getCityId().trim().length() == 0 ? false : true;
	}
	
	private Boolean areaPresent(String areaId) {
		return areaRepository.findOne(areaId).getAreaId().trim().length() == 0 ? false : true;
	}
	
	private Boolean landmarkPresent(String landmarkId) {
		return landmarkRepository.findOne(landmarkId).getLandmarkId().trim().length() == 0 ? false : true;
	}
	
	private Map<String, String> getCityIdName(String cityIdWithName) {
		
		String[] cityDetails  = cityIdWithName.split(UtilConstants.CITY_ID_NAME_DELIMETER);
		Map<String, String> cityDetailsMap = new HashMap<String, String>();
		
		if(cityDetails == null)
			return cityDetailsMap;
		
		if(cityDetails.length == 1) { 
			cityDetailsMap.put(UtilConstants.CITY_NAME, cityDetails[0]);
		} else if (cityDetails.length == 2){
			cityDetailsMap.put(UtilConstants.CITY_NAME, cityDetails[0]);
			cityDetailsMap.put(UtilConstants.CITY_ID, cityDetails[1]);
		}
		return cityDetailsMap;
	}
	
	
}
