package com.generic.core.services.service;

import java.util.List;

import com.generic.core.model.entities.Location;
import com.generic.rest.dto.LocationDto;
import com.generic.rest.dto.ResponseDto;

public interface LocationServiceI {

	List<Location> findAllLocations();

	List<LocationDto> findAllCities();
	
	List<LocationDto> findByParentLocation(String locationId);
	
	ResponseDto insertLocations(List<Location> locations);
}
