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
			Categories parentCategory = null;
			if(aSheetRow.getParentCategory() != null && aSheetRow.getParentCategory().trim().length() != 0) {
				parentCategory = new Categories(aSheetRow.getParentCategory());
			}
			Categories aCategory = new Categories(aSheetRow.getCategoryId(), aSheetRow.getCategoryName(),parentCategory);
			try {
				if(categoryPresent(aCategory.getCategoryId()) || categoryInsertedIds.contains(aCategory.getCategoryId())) {
					String errorContent = "CategoryId " + Constants.DATABASE_ERROR_KEY_PRESENT;
					String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, errorContent);
					response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
					continue;
				}
				if(parentCategory != null) {
					if(!categoryPresent(parentCategory.getCategoryId())) {
						String errorContent = "ParentCategoryId :" + parentCategory.getCategoryId() + Constants.DATABASE_ERROR_KEY_NOT_PRESENT;
						String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, errorContent);
						response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
						continue;
					}
				}
				categoryRepository.saveAndFlush(aCategory);
				categoryInsertedIds.add(aCategory.getCategoryId());
			} catch (Exception e) {
				String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, e.getMessage());
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
			}
		}

		if(!response.isEmpty()) {
			for(int i = categoryInsertedIds.size() -1 ; i>=0; i--) {
				categoryRepository.delete(categoryInsertedIds.get(i));
			}
		} else {			// no error send success message
			String successResponse = Constants.SUCCESS_RESPONSE_MESSAGE + ". Records Insserted :" + rowCount;
			response.add(new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, successResponse));
		}
		return response;
	}

	Boolean categoryPresent(String categoryId) {
		return categoryRepository.exists(categoryId);
	}
	
	
	
	public static void main(String[] args) {
		
		List<String> aList = new ArrayList<String>();
		aList.add("first");
		aList.add("second");
		aList.add("third");
		aList.add("fourth");
		
		for(int i=aList.size()-1; i>0 ; i--) {
			System.out.println(aList.get(i));
		}
	}


	@Override
	public Boolean categoryExist(String categoryId) {
		return categoryRepository.exists(categoryId);
	}
}
