package com.generic.core.services.service;

import java.util.List;
import java.util.Map;

import com.generic.rest.dto.CategoryDto;
import com.generic.rest.dto.ItemDto;

public interface ShopsItemsServiceI {

	Map<CategoryDto, Map<CategoryDto, List<ItemDto>>> findAllItemsForAShop(String shopId);
}
