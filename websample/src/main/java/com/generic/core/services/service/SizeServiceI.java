package com.generic.core.services.service;

import java.util.List;

import com.generic.core.model.entities.Size;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.rest.dto.ResponseDto;

public interface SizeServiceI {

	List<Size> findAllQuantity();
	
	/**
	 * push size in db
	 * @param excelSheetObject
	 * @return
	 */
	public List<ResponseDto> onboardSize(ExcelSheetObject excelSheetObject);
	
	Boolean sizeExist(String sizeId);
}
