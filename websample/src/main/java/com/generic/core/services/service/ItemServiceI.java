package com.generic.core.services.service;

import java.util.List;

import com.generic.core.model.entities.Items;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.rest.dto.ResponseDto;

public interface ItemServiceI {

	public List<Items> findAllItems();
	
	public List<ResponseDto> onboardItems(ExcelSheetObject excelSheetObject);
	
}
