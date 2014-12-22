package com.generic.core.cache;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.generic.core.model.entities.Location;
import com.generic.core.model.entities.Size;
import com.generic.core.services.serviceimpl.ServicesFactory;

@Component

/**
 * Used for Cache Initialization purpose during Context reload event
 * @author pkonwar
 */
public class InitializeCaches implements ApplicationListener<ContextRefreshedEvent>{

	@Resource
	ServicesFactory services;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		//initialize LocationCache
		/*
		LocationCache locationCache = LocationCache.getInstance();
		List<Location> locations = services.getLocationService().findAllLocations();
		locationCache.updateCache(locations);
		
		SizeCache measurableQuantityCache = SizeCache.getInstance();
		List<Size> size = services.getQuantityService().findAllQuantity();
		measurableQuantityCache.updateCache(size);
		*/
		
		/*//initialize ShopCache
		ShopsCache shopCache = ShopsCache.getInstance();
		List<Shops> shops = services.getShopsService().findAllShops();
		shopCache.fillShops(shops);
		
		//initialize CategoriesCache
		CategoriesCache categoriesCache = CategoriesCache.getInstance();
		List<Categories> categories = services.getCategoriesService().findAllCategories();
		categoriesCache.fillCatagories(categories);
		
		//initialize ItemCache
		ItemsCache itemCache = ItemsCache.getInstance();
		List<Items> items = services.getItemService().findAllItems();
		itemCache.fillItems(items);
		
		//initializes MeasurementQuantityCache
		QuantityCache measurementQuantityCache = QuantityCache.getInstance();
		List<Quantity> measurementQuantities = services.getQuantityService().findAllQuantity();
		measurementQuantityCache.fillMeasurementQuantity(measurementQuantities);*/
		
		System.out.println("Cache Initialization done");
	}

}
