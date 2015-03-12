package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Categories;
import com.generic.core.model.entities.Shops;
import com.generic.core.onboarding.exceldto.ExcelCategoryDto;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.core.onboarding.exceldto.ExcelShopsDto;
import com.generic.core.respository.CategoriesRepository;
import com.generic.core.services.service.CategoriesServiceI;
import com.generic.core.utilities.Util;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.ResponseDto;

@Service
@Transactional
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
		int count = 1;
		
		for(Object anCategoryObject : excelSheetObject.getRows()) {
			ExcelCategoryDto aSheetRow = (ExcelCategoryDto)anCategoryObject;
			Categories parentCategory = new Categories(aSheetRow.getParentCategory());
			Categories aCategory = new Categories(aSheetRow.getCategoryId(), aSheetRow.getCategoryName(),parentCategory);
			try {
				categoryRepository.save(aCategory);
				categoryInsertedIds.add(aCategory.getCategoryId());
			} catch (Exception e) {
				String errorResponse = Util.generateErrorString(count, Constants.LOGGER_WARNING, e.getMessage());
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
			}
		}

		if(!response.isEmpty()) {
			for(String aCategoryInsertedId : categoryInsertedIds) {
				categoryRepository.delete(new Categories(aCategoryInsertedId));
			}
		} else {			// no error send success message
			String successResponse = Constants.SUCCESS_RESPONSE_MESSAGE + ". Records Insserted :" + count;
			response.add(new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, successResponse));
		}
		return response;
	}

	
}
