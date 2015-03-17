package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.generic.core.model.entities.Shops;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.core.onboarding.exceldto.ExcelShopsDto;
import com.generic.core.respository.ShopsRepository;
import com.generic.core.services.service.ShopsServiceI;
import com.generic.core.utilities.Util;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.ResponseDto;

@Service
public class ShopsService implements ShopsServiceI{

	@Resource
	ShopsRepository shopsRepository;
	
	@Override
	public List<Shops> findAllShops() {
		return shopsRepository.findAll();
	}

	@Override
	public List<ResponseDto> onboardShops(ExcelSheetObject excelSheetObject) {

		List<String> shopInsertedIds = new ArrayList<String>();
		List<ResponseDto> response = new ArrayList<ResponseDto>();
		int rowCount = 0;
		
		for(Object anShopObject : excelSheetObject.getRows()) {
			rowCount++;
			ExcelShopsDto aSheetRow = (ExcelShopsDto)anShopObject;
			Shops aShop = new Shops(aSheetRow.getShopId(), aSheetRow.getShopName(), aSheetRow.getShopAddress(), aSheetRow.getShopType(), aSheetRow.getEmail(), aSheetRow.getPhoneNumber(), aSheetRow.getOwnerName(), aSheetRow.getTags());
			try {
				if(shopsPresent(aShop.getShopId()) || shopInsertedIds.contains(aShop.getShopId())) {
					String errorContent = "ShopId " + Constants.DATABASE_ERROR_KEY_PRESENT;
					String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, errorContent);
					response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
					continue;
				}
				shopsRepository.saveAndFlush(aShop);
				shopInsertedIds.add(aShop.getShopId());
			} catch (Exception e) {
				String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_WARNING, e.getMessage());
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
			}
		}

		if(!response.isEmpty()) {
			for(String aShopInsertedId : shopInsertedIds) {
				shopsRepository.delete(aShopInsertedId);
			}
		} else {			// no error send success message
			String successResponse = Constants.SUCCESS_RESPONSE_MESSAGE + ". Records Insserted :" + rowCount;
			response.add(new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, successResponse));
		}
		return response;
	}
	
	private Boolean shopsPresent(String shopId) {
		return shopsRepository.findOne(shopId) == null ? false : true;
	}
}
