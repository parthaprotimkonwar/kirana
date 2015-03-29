package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Area;
import com.generic.core.model.entities.City;
import com.generic.core.respository.AreaRepository;
import com.generic.core.services.service.AreaServiceI;
import com.generic.rest.dto.AreaDto;

@Service
@Transactional
public class AreaService implements AreaServiceI{

	@Resource
	AreaRepository areaRepository;

	@Override
	public List<AreaDto> getAllArea(String cityId) {
		City city = new City(cityId);
		List<Area> areas = areaRepository.findByCity(city);
		return convertToAreaDto(areas);
	}
	
	
	private List<AreaDto> convertToAreaDto(List<Area> areas) {
		List<AreaDto> areaDtos = new ArrayList<AreaDto>();
		for(Area anArea : areas) {
			areaDtos.add(new AreaDto(anArea.getAreaId(), anArea.getAreaName()));
		}
		return areaDtos;
	}
}
