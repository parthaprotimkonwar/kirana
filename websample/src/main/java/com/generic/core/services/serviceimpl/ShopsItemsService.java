package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.generic.core.model.entities.Categories;
import com.generic.core.model.entities.Items;
import com.generic.core.model.entities.ShopIdItemId;
import com.generic.core.model.entities.Shops;
import com.generic.core.model.entities.ShopsItems;
import com.generic.core.model.entities.Size;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.core.onboarding.exceldto.ExcelShopItemDto;
import com.generic.core.respository.ShopsItemsRepository;
import com.generic.core.services.service.ItemServiceI;
import com.generic.core.services.service.ShopsItemsServiceI;
import com.generic.core.services.service.ShopsServiceI;
import com.generic.core.services.service.SizeServiceI;
import com.generic.core.utilities.Util;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.CategoryDto;
import com.generic.rest.dto.ItemDto;
import com.generic.rest.dto.ResponseDto;

@Service
public class ShopsItemsService implements ShopsItemsServiceI{

	@Resource
	ShopsItemsRepository shopItemRepository;
	
	@Resource
	ShopsServiceI shopService;
	
	@Resource
	ItemServiceI itemService;
	
	@Resource
	SizeServiceI sizeService;
	
	@Override
	public Map<CategoryDto, Map<CategoryDto, List<ItemDto>>> findAllItemsForAShop(String shopId) {
		
		Shops aShop = new Shops(shopId);
		List<ShopsItems> matchedShopItem = shopItemRepository.findByShopIdItemIdShop(aShop);
		return generateItemInventory(matchedShopItem);
	}

	@Override
	public List<ResponseDto> onboardShopItems(ExcelSheetObject excelSheetObject) {

		List<ShopIdItemId> shopItemsInsertedIds = new ArrayList<ShopIdItemId>();
		List<ResponseDto> response = new ArrayList<ResponseDto>();
		int rowCount = 0;
		
		for(Object anShopItemsObject : excelSheetObject.getRows()) {
			rowCount++;
			ExcelShopItemDto aSheetRow = (ExcelShopItemDto)anShopItemsObject;
			
			ShopIdItemId aShopIdItemId = new ShopIdItemId(new Shops(aSheetRow.getShopId()), new Items(aSheetRow.getItemId()));
			ShopsItems aShopItem = new ShopsItems(aShopIdItemId, aSheetRow.getPrice(), aSheetRow.getDiscount(), aSheetRow.getStatus(), new Size(aSheetRow.getSizeId()));
			
			if(!shopService.shopsPresent(aShopIdItemId.getShop().getShopId())) {	//shop not present
				String errorContent = "ShopId : "  + aShopIdItemId.getShop().getShopId() + " "+ Constants.DATABASE_ERROR_KEY_NOT_PRESENT;
				String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, errorContent);
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
				continue;
			}
			
			if(!itemService.itemExist(aShopIdItemId.getItem().getItemId())) {	//check if item present
				String errorContent = "ItemId : "  + aShopIdItemId.getItem().getItemId() + " "+ Constants.DATABASE_ERROR_KEY_NOT_PRESENT;
				String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, errorContent);
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
				continue;
			}
			
			if(!sizeService.sizeExist(aShopItem.getSize().getSizeId())) {	//check if item present
				String errorContent = "SizeId : "  + aShopItem.getSize().getSizeId() + " "+ Constants.DATABASE_ERROR_KEY_NOT_PRESENT;
				String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, errorContent);
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
				continue;
			}
			
			try {
				if(shopsItemPresent(aShopItem.getShopIdItemId()) || shopItemsInsertedIds.contains(aShopItem.getShopIdItemId())) {
					String errorContent = "ShopIdItemId " + Constants.DATABASE_ERROR_KEY_PRESENT;
					String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_ERROR, errorContent);
					response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
					continue;
				}
				shopItemRepository.save(aShopItem);
				shopItemsInsertedIds.add(aShopItem.getShopIdItemId());
			} catch (Exception e) {
				String errorResponse = Util.generateErrorString(rowCount, Constants.LOGGER_WARNING, e.getMessage());
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
			}
		}

		if(!response.isEmpty()) {
			for(ShopIdItemId aShopInsertedId : shopItemsInsertedIds) {
				shopItemRepository.delete(aShopInsertedId);
			}
		} else {			// no error send success message
			String successResponse = Constants.SUCCESS_RESPONSE_MESSAGE + ". Records Insserted :" + rowCount;
			response.add(new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, successResponse));
		}
		return response;
	}
	
	private Boolean shopsItemPresent(ShopIdItemId shopIdItemId) {
		return shopItemRepository.findOne(shopIdItemId) == null ? false : true;
	}
	
	private List<ItemDto> convertToItemDto(List<ShopsItems> shopItems) {
		
		List<ItemDto> itemList = new ArrayList<ItemDto>();
		for(ShopsItems aShopItem : shopItems) { 
			Items aItem = aShopItem.getShopIdItemId().getItem();
			String sizeId =  aShopItem.getSize().getSizeId();
			ItemDto aItemDto = new ItemDto(aItem.getItemId(), aItem.getItemName(), aItem.getDescription(), aItem.getBrand(), aItem.getImageName(), aShopItem.getPrice(), sizeId);
			itemList.add(aItemDto);
		}
		return itemList;
	}
	
	
	private Map<CategoryDto, Map<CategoryDto, List<ItemDto>>> generateItemInventory(List<ShopsItems> shopsItems) {
		
		Map<CategoryDto, Map<CategoryDto, List<ItemDto>>> inventory = new HashMap<CategoryDto, Map<CategoryDto,List<ItemDto>>>();
		for(ShopsItems aShopItem : shopsItems) {
			
			Items aItem = aShopItem.getShopIdItemId().getItem();
			Categories aChildCategory = aItem.getCategory();
			Categories aParentCategory = aChildCategory.getParentCategory();
			
			CategoryDto childCategoryDto = new CategoryDto(aChildCategory.getCategoryId(), aChildCategory.getCategoryName());
			CategoryDto parentCategoryDto = new CategoryDto(aParentCategory.getCategoryId(), aParentCategory.getCategoryName());
			
			Map<CategoryDto, List<ItemDto>> childInventory = inventory.get(parentCategoryDto);
			if(childInventory == null)
				childInventory = new HashMap<CategoryDto, List<ItemDto>>();
			
			List<ItemDto> items = childInventory.get(childCategoryDto);
			
			if(items == null) 
				items = new ArrayList<ItemDto>();
			
			ItemDto anItemDto = new ItemDto(aItem.getItemId(), aItem.getItemName(), aItem.getDescription(), aItem.getBrand(), aItem.getImageName(), aShopItem.getPrice(), aShopItem.getSize().getSizeId());
			
			items.add(anItemDto);
			childInventory.put(childCategoryDto, items);
			inventory.put(parentCategoryDto, childInventory);
		}
		return inventory;
	}

}
