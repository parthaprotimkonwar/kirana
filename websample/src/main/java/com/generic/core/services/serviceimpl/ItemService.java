package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.generic.core.model.entities.Categories;
import com.generic.core.model.entities.Items;
import com.generic.core.onboarding.exceldto.ExcelItemsDto;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.core.respository.ItemsRepository;
import com.generic.core.services.service.CategoriesServiceI;
import com.generic.core.services.service.ItemServiceI;
import com.generic.core.utilities.Util;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.ResponseDto;

@Service
public class ItemService implements ItemServiceI {

	@Resource
	ItemsRepository itemRepository;
	
	@Resource
	CategoriesServiceI cateforyService;
	
	@Override
	public List<Items> findAllItems() {

		return itemRepository.findAll();
	}

	@Override
	public List<ResponseDto> onboardItems(ExcelSheetObject excelSheetObject) {

		List<String> itemInsertedIds = new ArrayList<String>();
		List<ResponseDto> response = new ArrayList<ResponseDto>();
		int rowCount = 1;
		
		for(Object anItemObject : excelSheetObject.getRows()) {
			rowCount++;
			ExcelItemsDto aSheetRow = (ExcelItemsDto)anItemObject;
			Categories aCategory = new Categories(aSheetRow.getCategoryId());
			if(!cateforyService.categoryExist(aCategory.getCategoryId())) {
				String errorContent = "CategoryId :" + aCategory.getCategoryId() + " " +  Constants.DATABASE_ERROR_KEY_NOT_PRESENT;
				String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, errorContent);
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
				continue;
			}
			
			Items anItem = new Items(aSheetRow.getItemId(), aSheetRow.getItemName(), aSheetRow.getImageName(), aSheetRow.getDescription(), aSheetRow.getBrand(), aCategory);
			try {
				if(itemPresent(anItem.getItemId()) || itemInsertedIds.contains(anItem.getItemId())) {
					String errorContent = "ItemId " + Constants.DATABASE_ERROR_KEY_PRESENT;
					String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, errorContent);
					response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
					continue;
				}
				itemRepository.saveAndFlush(anItem);
				itemInsertedIds.add(anItem.getItemId());
			} catch (Exception e) {
				String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_WARNING, e.getMessage());
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
			}
		}

		if(!response.isEmpty()) {
			for(String aItemInsertedId : itemInsertedIds) {
				itemRepository.delete(aItemInsertedId);
			}
		} else {			// no error send success message
			String successResponse = Constants.SUCCESS_RESPONSE_MESSAGE + ". Records Insserted :" + rowCount;
			response.add(new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, successResponse));
		}
		return response;
	}

	private Boolean itemPresent(String itemId) {
		return itemRepository.findOne(itemId) == null ? false : true;
	}

	@Override
	public Boolean itemExist(String itemId) {
		return itemRepository.exists(itemId);
	}
	
}
