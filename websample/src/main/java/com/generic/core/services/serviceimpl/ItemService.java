package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Categories;
import com.generic.core.model.entities.Items;
import com.generic.core.onboarding.exceldto.ExcelItemsDto;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.core.respository.ItemsRepository;
import com.generic.core.services.service.ItemServiceI;
import com.generic.core.utilities.Util;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.ResponseDto;

@Service
@Transactional
public class ItemService implements ItemServiceI {

	@Resource
	ItemsRepository itemRepository;
	
	@Override
	public List<Items> findAllItems() {

		return itemRepository.findAll();
	}

	@Override
	public List<ResponseDto> onboardItems(ExcelSheetObject excelSheetObject) {

		List<String> itemInsertedIds = new ArrayList<String>();
		List<ResponseDto> response = new ArrayList<ResponseDto>();
		int count = 1;
		
		for(Object anItemObject : excelSheetObject.getRows()) {
			ExcelItemsDto aSheetRow = (ExcelItemsDto)anItemObject;
			Categories aCategory = new Categories(aSheetRow.getCategoryId());
			Items anItem = new Items(aSheetRow.getItemId(), aSheetRow.getItemName(), aSheetRow.getImageName(), aSheetRow.getDescription(), aSheetRow.getBrand(), aCategory);
			try {
				itemRepository.save(anItem);
				itemInsertedIds.add(anItem.getItemId());
			} catch (Exception e) {
				String errorResponse = Util.generateErrorString(count, Constants.LOGGER_WARNING, e.getMessage());
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
			}
		}

		if(!response.isEmpty()) {
			for(String aItemInsertedId : itemInsertedIds) {
				itemRepository.delete(new Items(aItemInsertedId));
			}
		} else {			// no error send success message
			String successResponse = Constants.SUCCESS_RESPONSE_MESSAGE + ". Records Insserted :" + count;
			response.add(new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, successResponse));
		}
		return response;
	}

	
}
