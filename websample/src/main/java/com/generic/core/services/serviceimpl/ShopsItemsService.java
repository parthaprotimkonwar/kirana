package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Categories;
import com.generic.core.model.entities.Items;
import com.generic.core.model.entities.ShopIdItemId;
import com.generic.core.model.entities.Shops;
import com.generic.core.model.entities.ShopsItems;
import com.generic.core.model.entities.Size;
import com.generic.core.onboarding.exceldto.ExcelItemsDto;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.core.onboarding.exceldto.ExcelShopItemDto;
import com.generic.core.onboarding.exceldto.ExcelShopsDto;
import com.generic.core.respository.ShopsItemsRepository;
import com.generic.core.services.service.ShopsItemsServiceI;
import com.generic.core.utilities.Util;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.CategoryDto;
import com.generic.rest.dto.ItemDto;
import com.generic.rest.dto.ResponseDto;

@Service
@Transactional
public class ShopsItemsService implements ShopsItemsServiceI{

	@Resource
	ShopsItemsRepository shopItemRepository;
	
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
		int count = 1;
		
		for(Object anShopItemsObject : excelSheetObject.getRows()) {
			ExcelShopItemDto aSheetRow = (ExcelShopItemDto)anShopItemsObject;
			
			ShopIdItemId aShopIdItemId = new ShopIdItemId(new Shops(aSheetRow.getShopId()), new Items(aSheetRow.getItemId()));
			ShopsItems aShopItem = new ShopsItems(aShopIdItemId, aSheetRow.getPrice(), aSheetRow.getDiscount(), aSheetRow.getStatus(), new Size(aSheetRow.getSizeId()));
			try {
				shopItemRepository.save(aShopItem);
				shopItemsInsertedIds.add(aShopItem.getShopIdItemId());
			} catch (Exception e) {
				String errorResponse = Util.generateErrorString(count, Constants.LOGGER_WARNING, e.getMessage());
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
			}
		}

		if(!response.isEmpty()) {
			for(ShopIdItemId aShopInsertedId : shopItemsInsertedIds) {
				shopItemRepository.delete(new ShopsItems(aShopInsertedId));
			}
		} else {			// no error send success message
			String successResponse = Constants.SUCCESS_RESPONSE_MESSAGE + ". Records Insserted :" + count;
			response.add(new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, successResponse));
		}
		return response;
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
