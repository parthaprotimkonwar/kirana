package com.generic.core.services.service;

import java.util.List;

import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.rest.dto.CategoryDto;
import com.generic.rest.dto.ResponseDto;

public interface CategoriesServiceI {

	/**
	 * Lists all Categories
	 * @return
	 */
	public List<CategoryDto> findAllCategories();
	
	/**
	 * Onboard Categories on DB
	 * @param excelSheetObject
	 * @return
	 */
	List<ResponseDto> onboardCategories(ExcelSheetObject excelSheetObject);
	
	/**
	 * check if the category exist
	 * @param categoryId
	 * @return
	 */
	Boolean categoryExist(String categoryId);
	
}
