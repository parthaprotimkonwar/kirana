package com.generic.core.services.service;

import java.util.List;

import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.rest.dto.ItemDto;
import com.generic.rest.dto.ResponseDto;

public interface ItemServiceI {

	public List<ItemDto> findAllItems();
	
	public List<ResponseDto> onboardItems(ExcelSheetObject excelSheetObject);
	
	Boolean itemExist(String itemId);
	
}
