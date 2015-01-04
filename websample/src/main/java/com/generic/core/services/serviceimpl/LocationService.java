package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Location;
import com.generic.core.respository.LocationRepository;
import com.generic.core.services.service.LocationServiceI;
import com.generic.core.services.serviceimpl.LocationService;
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
		List<Location> locations =  locationRepository.findByLocationIdLike("LOC_CITY_%");
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

	@Override
	public ResponseDto insertLocations(List<Location> locations) {
		try {
			locationRepository.save(locations);
		} catch (Exception ex) {
			return new ResponseDto("00", "unable to insert");
		}
		return new ResponseDto("200", "ok");
	}
	
}
