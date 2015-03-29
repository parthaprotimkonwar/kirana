package com.generic.core.services.service;

import java.util.List;

import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.rest.dto.ResponseDto;
import com.generic.rest.dto.ShopDto;

public interface ShopsServiceI {

	/**
	 * Lists all shops present
	 * @return
	 */
	List<ShopDto> findAllShops();

	//List<ShopDto> findShopsForLocation(String locationId);
	
	public List<ResponseDto> onboardShops(ExcelSheetObject excelSheetObject);
	
	/**
	 * Checks if a Shop is present or not
	 * @param shopId
	 * @return
	 */
	Boolean shopsPresent(String shopId);
	
	/**
	 * Delete a Shop
	 * @param shopId
	 * @return
	 */
	void deleteShops(String shopId);
	
}
