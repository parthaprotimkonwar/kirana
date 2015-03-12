package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.City;
import com.generic.core.respository.CityRepository;
import com.generic.core.services.service.CityServiceI;
import com.generic.rest.dto.CityDto;

@Service
@Transactional
public class CityService implements CityServiceI {

	@Resource
	CityRepository cityRepository;

	@Override
	public List<CityDto> getAllCities() {
		
		List<City> cities = cityRepository.findAll();
		return convertIntoCityDto(cities);
	}
	
	
	private List<CityDto> convertIntoCityDto(List<City> cities) {
		
		List<CityDto> cityDtos = new ArrayList<CityDto>();
		for(City aCity : cities) {
			cityDtos.add(new CityDto(aCity.getCityId(), aCity.getCityName()));
		}
		return cityDtos;
	}
	
	
}
