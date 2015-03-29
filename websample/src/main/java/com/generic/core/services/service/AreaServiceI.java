package com.generic.core.services.service;

import java.util.List;

import com.generic.rest.dto.AreaDto;

public interface AreaServiceI {

	List<AreaDto> getAllArea(String cityId);
}
