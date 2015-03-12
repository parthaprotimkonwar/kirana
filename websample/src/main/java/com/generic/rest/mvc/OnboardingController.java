package com.generic.rest.mvc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generic.core.onboarding.exceldto.ExcelCategoryDto;
import com.generic.core.onboarding.exceldto.ExcelItemsDto;
import com.generic.core.onboarding.exceldto.ExcelLocationDto;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.core.onboarding.exceldto.ExcelShopItemDto;
import com.generic.core.onboarding.exceldto.ExcelShopsDto;
import com.generic.core.onboarding.exceldto.ExcelSizeDto;
import com.generic.core.services.serviceimpl.ServicesFactory;
import com.generic.core.utilities.ExcelUtilities;
import com.generic.core.validation.validate.ValidationRules;
import com.generic.rest.dto.ResponseDto;

@Controller
@RequestMapping(value="/rest/onboarding")
public class OnboardingController {

	@Resource
	private ServicesFactory serviceFactory;
	
	/**
	 * Method to Validate the Location Excel Sheet
	 * @param excelSheetUrlLocation
	 * @return
	 */
	@RequestMapping(value="location", method=RequestMethod.POST)
	public @ResponseBody List<ResponseDto> onboardLocation(@RequestBody String excelSheetUrlLocation) {
		
		List<Object> locationExcelSheetRows = null;
		List<ResponseDto> response = null;
		ExcelSheetObject excelSheetObject = null;
		try {
			excelSheetObject = ExcelUtilities.readExcelSheet(excelSheetUrlLocation, ExcelLocationDto.class, true);
			locationExcelSheetRows = excelSheetObject.getRows();
			response = ExcelUtilities.validate(ExcelUtilities.class, locationExcelSheetRows, ValidationRules.locationRules);
			
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * Method to insert location data into the DB
	 * @param excelSheetUrlLocation
	 * @return
	 */
	@RequestMapping(value="location/push", method=RequestMethod.POST)
	public @ResponseBody List<ResponseDto> pushLocation(@RequestBody String excelSheetUrlLocation) {
		
		List<Object> locationExcelSheetRows = null;
		List<ResponseDto> response = null;
		ExcelSheetObject excelSheetObject = null;
		try {
			excelSheetObject = ExcelUtilities.readExcelSheet(excelSheetUrlLocation, ExcelLocationDto.class, true);
			response = serviceFactory.getLandmarkService().onboardLandmarks(excelSheetObject);
					ExcelUtilities.validate(ExcelUtilities.class, locationExcelSheetRows, ValidationRules.locationRules);
			
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * Method to validate the Shops excel sheet. 
	 * @param excelSheetUrlLocation
	 * @return
	 */
	@RequestMapping(value="shops", method=RequestMethod.POST)
	public @ResponseBody List<ResponseDto> onboardShops(@RequestBody String excelSheetUrlLocation) {
		
		List<Object> shopsExcelSheetRows = null;
		List<ResponseDto> response = null;
		ExcelSheetObject excelSheetObject = null;
		try {
			excelSheetObject = ExcelUtilities.readExcelSheet(excelSheetUrlLocation, ExcelShopsDto.class, true);
			shopsExcelSheetRows = excelSheetObject.getRows();
			response = ExcelUtilities.validate(ExcelUtilities.class, shopsExcelSheetRows, ValidationRules.shopsRules);
			
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="shops/push", method=RequestMethod.POST)
	public @ResponseBody List<ResponseDto> pushShops(@RequestBody String excelSheetUrlLocation) {
		
		List<ResponseDto> response = null;
		ExcelSheetObject excelSheetObject = null;
		try {
			excelSheetObject = ExcelUtilities.readExcelSheet(excelSheetUrlLocation, ExcelShopsDto.class, true);
			response = serviceFactory.getShopsService().onboardShops(excelSheetObject);
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * Method to validate Items in the Item Onboarding sheet
	 * @param excelSheetUrlLocation
	 * @return
	 */
	@RequestMapping(value="items", method=RequestMethod.POST)
	public @ResponseBody List<ResponseDto> onboardItems(@RequestBody String excelSheetUrlLocation) {
		
		List<Object> itemExcelSheetRows = null;
		List<ResponseDto> response = null;
		ExcelSheetObject excelSheetObject = null;
		try {
			excelSheetObject = ExcelUtilities.readExcelSheet(excelSheetUrlLocation, ExcelItemsDto.class, true);
			itemExcelSheetRows = excelSheetObject.getRows();
			response = ExcelUtilities.validate(ExcelUtilities.class, itemExcelSheetRows, ValidationRules.itemsRules);
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="items/push", method=RequestMethod.POST)
	public @ResponseBody List<ResponseDto> pushItems(@RequestBody String excelSheetUrlLocation) {
		
		List<ResponseDto> response = null;
		ExcelSheetObject excelSheetObject = null;
		try {
			excelSheetObject = ExcelUtilities.readExcelSheet(excelSheetUrlLocation, ExcelItemsDto.class, true);
			response = serviceFactory.getItemService().onboardItems(excelSheetObject);
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="shopitems", method=RequestMethod.POST)
	public @ResponseBody List<ResponseDto> onboardShopItems(@RequestBody String excelSheetUrlLocation) {
		
		List<Object> shopItemExcelSheetRows = null;
		List<ResponseDto> response = null;
		ExcelSheetObject excelSheetObject = null;
		try {
			excelSheetObject = ExcelUtilities.readExcelSheet(excelSheetUrlLocation, ExcelShopItemDto.class, true);
			shopItemExcelSheetRows = excelSheetObject.getRows();
			response = ExcelUtilities.validate(ExcelUtilities.class, shopItemExcelSheetRows, ValidationRules.shopItemRules);
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="shopitems/push", method=RequestMethod.POST)
	public @ResponseBody List<ResponseDto> pushShopItems(@RequestBody String excelSheetUrlLocation) {
		
		List<ResponseDto> response = null;
		ExcelSheetObject excelSheetObject = null;
		try {
			excelSheetObject = ExcelUtilities.readExcelSheet(excelSheetUrlLocation, ExcelShopItemDto.class, true);
			response = serviceFactory.getShopsItemsService().onboardShopItems(excelSheetObject);
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="size", method=RequestMethod.POST)
	public @ResponseBody List<ResponseDto> onboardSize(@RequestBody String excelSheetUrlLocation) {
		
		List<Object> shopItemExcelSheetRows = null;
		List<ResponseDto> response = null;
		ExcelSheetObject excelSheetObject = null;
		try {
			excelSheetObject = ExcelUtilities.readExcelSheet(excelSheetUrlLocation, ExcelSizeDto.class, true);
			shopItemExcelSheetRows = excelSheetObject.getRows();
			response = ExcelUtilities.validate(ExcelUtilities.class, shopItemExcelSheetRows, ValidationRules.shopItemRules);
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="size/push", method=RequestMethod.POST)
	public @ResponseBody List<ResponseDto> pushSize(@RequestBody String excelSheetUrlLocation) {
		
		List<ResponseDto> response = null;
		ExcelSheetObject excelSheetObject = null;
		try {
			excelSheetObject = ExcelUtilities.readExcelSheet(excelSheetUrlLocation, ExcelSizeDto.class, true);
			response = serviceFactory.getSizeService().onboardSize(excelSheetObject);
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="category", method=RequestMethod.POST)
	public @ResponseBody List<ResponseDto> onboardCategory(@RequestBody String excelSheetUrlLocation) {
		
		List<Object> shopItemExcelSheetRows = null;
		List<ResponseDto> response = null;
		ExcelSheetObject excelSheetObject = null;
		try {
			excelSheetObject = ExcelUtilities.readExcelSheet(excelSheetUrlLocation, ExcelCategoryDto.class, true);
			shopItemExcelSheetRows = excelSheetObject.getRows();
			response = ExcelUtilities.validate(ExcelUtilities.class, shopItemExcelSheetRows, ValidationRules.categoryRules);
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="category/push", method=RequestMethod.POST)
	public @ResponseBody List<ResponseDto> pushCategory(@RequestBody String excelSheetUrlLocation) {
		
		List<ResponseDto> response = null;
		ExcelSheetObject excelSheetObject = null;
		try {
			excelSheetObject = ExcelUtilities.readExcelSheet(excelSheetUrlLocation, ExcelCategoryDto.class, true);
			response = serviceFactory.getCategoriesService().onboardCategories(excelSheetObject);
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
