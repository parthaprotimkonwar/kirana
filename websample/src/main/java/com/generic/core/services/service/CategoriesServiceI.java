package com.generic.core.services.service;

import java.util.List;

import com.generic.core.model.entities.Categories;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.rest.dto.ResponseDto;

public interface CategoriesServiceI {

	public List<Categories> findAllCategories();
	
	/**
	 * Onboard Categories on DB
	 * @param excelSheetObject
	 * @return
	 */
	List<ResponseDto> onboardCategories(ExcelSheetObject excelSheetObject);
	
	Boolean categoryExist(String categoryId);
	
}
