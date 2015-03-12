package com.generic.core.services.service;

import java.util.List;

import com.generic.core.model.entities.Shops;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.rest.dto.ResponseDto;

public interface ShopsServiceI {

	List<Shops> findAllShops();

	//List<ShopDto> findShopsForLocation(String locationId);
	
	public List<ResponseDto> onboardShops(ExcelSheetObject excelSheetObject);
	
}
