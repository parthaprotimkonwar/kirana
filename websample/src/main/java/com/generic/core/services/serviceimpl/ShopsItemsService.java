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
import com.generic.core.model.entities.Shops;
import com.generic.core.model.entities.ShopsItems;
import com.generic.core.respository.ShopsItemsRepository;
import com.generic.core.services.service.ShopsItemsServiceI;
import com.generic.rest.dto.CategoryDto;
import com.generic.rest.dto.ItemDto;

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
