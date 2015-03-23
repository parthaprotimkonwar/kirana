package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.generic.core.model.entities.Landmark;
import com.generic.core.model.entities.ShopIdLandmarkId;
import com.generic.core.model.entities.Shops;
import com.generic.core.model.entities.ShopsLandmark;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.core.onboarding.exceldto.ExcelShopsDto;
import com.generic.core.respository.ShopsRepository;
import com.generic.core.services.service.LandmarkServiceI;
import com.generic.core.services.service.ShopsLandmarkServiceI;
import com.generic.core.services.service.ShopsServiceI;
import com.generic.core.utilities.Util;
import com.generic.core.utilities.UtilConstants;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.ResponseDto;

@Service
public class ShopsService implements ShopsServiceI{

	@Resource
	ShopsRepository shopsRepository;
	
	@Resource
	LandmarkServiceI landmarkService;
	
	@Resource
	ShopsLandmarkServiceI shopsLandmarkService;
	
	@Override
	public List<Shops> findAllShops() {
		return shopsRepository.findAll();
	}

	@Override
	public List<ResponseDto> onboardShops(ExcelSheetObject excelSheetObject) {

		List<String> shopInsertedIds = new ArrayList<String>();
		List<ShopIdLandmarkId> shopLandmarkId = new ArrayList<ShopIdLandmarkId>();
		
		List<ResponseDto> response = new ArrayList<ResponseDto>();
		int rowCount = 0;
		
		for(Object anShopObject : excelSheetObject.getRows()) {
			rowCount++;
			ExcelShopsDto aSheetRow = (ExcelShopsDto)anShopObject;
			Shops aShop = new Shops(aSheetRow.getShopId(), aSheetRow.getShopName(), aSheetRow.getShopAddress(), aSheetRow.getShopType(), aSheetRow.getEmail(), aSheetRow.getPhoneNumber(), aSheetRow.getOwnerName(), aSheetRow.getTags());
			String[] landmarks = splitLandmarks(aSheetRow.getLandmarksForDelivery());
			//insert shops if not present
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
				continue;	//since there is an error in shop creation no need to insert location.
			}
			//associate the shop to all the locations. 
			//provided the location is already onboarded in the system
			// this can be done only once the shops are onboarded. So its placed here, after shop registration.
			
			try{
				for(String landmarkId : landmarks) {
					
					Landmark aLandmark = new Landmark(landmarkId);		//create a landmark object
					
					if(!landmarkService.landmarkPresent(landmarkId)) {
						String errorContent = "LandmarkId :" + landmarkId + " " + Constants.DATABASE_ERROR_KEY_NOT_PRESENT;
						String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, errorContent);
						response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
						continue;
					}
					if(shopsLandmarkService.shopsLandmarkExist(aShop, aLandmark)) {
						String errorContent = "ShopId :" + aShop.getShopId() + ", LandmarkId :" + aLandmark.getLandmarkId() + " " + Constants.DATABASE_ERROR_KEY_PRESENT;
						String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, errorContent);
						response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
						continue;
					}
					//insert into shop landmark table
					ShopIdLandmarkId aShopIdlandmarkId = new ShopIdLandmarkId(aShop, aLandmark);
					ShopsLandmark shopLandmark = new ShopsLandmark(aShopIdlandmarkId);
					shopsLandmarkService.saveFlushShopsLandmark(shopLandmark);
					shopLandmarkId.add(aShopIdlandmarkId);
				}
			} catch(Exception e) {
				String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, e.getCause().getMessage());
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
			}
		}

		if(!response.isEmpty()) {
			
			for(ShopIdLandmarkId aShopIdLandmarkId : shopLandmarkId) {
				shopsLandmarkService.deleteShopsLandmark(aShopIdLandmarkId);
			}
			for(String aShopInsertedId : shopInsertedIds) {
				shopsRepository.delete(aShopInsertedId);
			}
		} else {			// no error send success message
			String successResponse = Constants.SUCCESS_RESPONSE_MESSAGE + ". Records Insserted :" + rowCount;
			response.add(new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, successResponse));
		}
		return response;
	}

	/*private Boolean landmarkmarkAvailable(String landmarkId) {
		
	}*/
	private String[] splitLandmarks(String landmarks) {
		return landmarks.split(UtilConstants.DELIMETER_BAR);
	}
	
	@Override
	public Boolean shopsPresent(String shopId) {
		return shopsRepository.exists(shopId);
	}

	@Override
	public void deleteShops(String shopId) {
		shopsRepository.delete(shopId);;
	}
	
}
