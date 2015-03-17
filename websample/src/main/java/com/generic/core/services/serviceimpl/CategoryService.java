package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.generic.core.model.entities.Categories;
import com.generic.core.onboarding.exceldto.ExcelCategoryDto;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.core.respository.CategoriesRepository;
import com.generic.core.services.service.CategoriesServiceI;
import com.generic.core.utilities.Util;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.ResponseDto;

@Service
public class CategoryService implements CategoriesServiceI{

	@Resource
	CategoriesRepository categoryRepository;
	
	@Override
	public List<Categories> findAllCategories() {
		
		return categoryRepository.findAll();
	}

	
	@Override
	public List<ResponseDto> onboardCategories(ExcelSheetObject excelSheetObject) {

		List<String> categoryInsertedIds = new ArrayList<String>();
		List<ResponseDto> response = new ArrayList<ResponseDto>();
		int rowCount = 0;
		
		for(Object anCategoryObject : excelSheetObject.getRows()) {
			rowCount++;
			ExcelCategoryDto aSheetRow = (ExcelCategoryDto)anCategoryObject;
			Categories parentCategory = new Categories(aSheetRow.getParentCategory());
			Categories aCategory = new Categories(aSheetRow.getCategoryId(), aSheetRow.getCategoryName(),parentCategory);
			try {
				if(categoryPresent(aCategory.getCategoryId()) || categoryInsertedIds.contains(aCategory.getCategoryId())) {
					String errorContent = "CategoryId " + Constants.DATABASE_ERROR_KEY_PRESENT;
					String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, errorContent);
					response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
					continue;
				}
				categoryRepository.save(aCategory);
				categoryInsertedIds.add(aCategory.getCategoryId());
			} catch (Exception e) {
				String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_WARNING, e.getMessage());
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
			}
		}

		if(!response.isEmpty()) {
			for(String aCategoryInsertedId : categoryInsertedIds) {
				categoryRepository.delete(aCategoryInsertedId);
			}
		} else {			// no error send success message
			String successResponse = Constants.SUCCESS_RESPONSE_MESSAGE + ". Records Insserted :" + rowCount;
			response.add(new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, successResponse));
		}
		return response;
	}

	Boolean categoryPresent(String categoryId) {
		return categoryRepository.findOne(categoryId) == null ? false : true;
	}
	
}
