package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Shops;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.core.onboarding.exceldto.ExcelShopsDto;
import com.generic.core.respository.ShopsRepository;
import com.generic.core.services.service.ShopsServiceI;
import com.generic.core.utilities.Util;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.ResponseDto;
import com.generic.rest.dto.ShopDto;

@Service
@Transactional
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
		int count = 1;
		
		for(Object anShopObject : excelSheetObject.getRows()) {
			ExcelShopsDto aSheetRow = (ExcelShopsDto)anShopObject;
			Shops aShop = new Shops(aSheetRow.getShopId(), aSheetRow.getShopName(), aSheetRow.getShopAddress(), aSheetRow.getShopType(), aSheetRow.getEmail(), aSheetRow.getPhoneNumber(), aSheetRow.getOwnerName(), aSheetRow.getTags());
			try {
				shopsRepository.save(aShop);
				shopInsertedIds.add(aShop.getShopId());
			} catch (Exception e) {
				String errorResponse = Util.generateErrorString(count, Constants.LOGGER_WARNING, e.getMessage());
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
			}
		}

		if(!response.isEmpty()) {
			for(String aShopInsertedId : shopInsertedIds) {
				shopsRepository.delete(new Shops(aShopInsertedId));
			}
		} else {			// no error send success message
			String successResponse = Constants.SUCCESS_RESPONSE_MESSAGE + ". Records Insserted :" + count;
			response.add(new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, successResponse));
		}
		return response;
	}
	
	//@Override
	/*public List<ShopDto> findShopsForLocation(String locationId) {

		Location location = new Location(locationId, null);
		List<Shops> selectedShops = shopsRepository.findByLocation(location);
		return convertToShopDto(selectedShops);
	}*/
	
	

	private List<ShopDto> convertToShopDto(List<Shops> shops) {
		if(shops == null)
			return null;
		List<ShopDto> shopDtoList = new ArrayList<ShopDto>();
		for(Shops aShop : shops) {
			ShopDto shopDto = new ShopDto(aShop.getShopId(), aShop.getShopName());
			shopDtoList.add(shopDto);
		}
		return shopDtoList;
	}

	
}
