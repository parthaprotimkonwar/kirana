package com.generic.core.services.service;

import java.util.List;

import com.generic.rest.dto.CityDto;


public interface CityServiceI {

	/**
	 * Getting the list of all cities
	 * @return
	 */
	public List<CityDto> getAllCities();
}
