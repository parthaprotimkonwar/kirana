package com.generic.rest.mvc.onboarding;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generic.core.model.entities.Categories;
import com.generic.core.services.serviceimpl.ServicesFactory;
import com.generic.rest.dto.AreaDto;
import com.generic.rest.dto.CategoryDto;
import com.generic.rest.dto.CityDto;
import com.generic.rest.dto.ItemDto;
import com.generic.rest.dto.LandmarkDto;
import com.generic.rest.dto.ShopDto;
import com.generic.rest.dto.SizeDto;

@Controller
@RequestMapping(value="/rest/onboardingutil")
public class OnboardingUtilController {

	@Resource
	ServicesFactory serviceFactory;

	/**
	 * Retuens All Cities
	 * @return
	 */
	@RequestMapping(value="lists/cities")
	public @ResponseBody List<CityDto> listsCities() {
		return serviceFactory.getCityService().getAllCities();
	}
	
	/**
	 * Returns all Area for a City.
	 * @param cityId
	 * @return
	 */
	@RequestMapping(value="lists/area/{cityId}")
	public @ResponseBody List<AreaDto> listsAreaForCity(@PathVariable String cityId) {
		return serviceFactory.getAreaService().getAllArea(cityId);
	}
	
	/**
	 * Returns all area for a Landmark
	 * @param areaId
	 * @return
	 */
	@RequestMapping(value="lists/landmark/{areaId}")
	public @ResponseBody List<LandmarkDto> listsLandmarkForArea(@PathVariable String areaId) {
		return serviceFactory.getLandmarkService().listsLandmarkForArea(areaId);
	}
	
	/**
	 * Lists out all the shops
	 * @return
	 */
	@RequestMapping(value="lists/shops")
	public @ResponseBody List<ShopDto> listsShops() {
		return serviceFactory.getShopsService().findAllShops();
	}
	
	/**
	 * Lists all available categories.
	 * @return
	 */
	@RequestMapping(value="lists/categories")
	public @ResponseBody List<CategoryDto> listsAvailableCategories() {
		return serviceFactory.getCategoriesService().findAllCategories();
	}
	
	/**
	 * Lists all Items available
	 * @return
	 */
	@RequestMapping(value="lists/items")
	public @ResponseBody List<ItemDto> listAvailableItems() {
		return serviceFactory.getItemService().findAllItems();
	}
	
	/**
	 * Lists all the sizes
	 * @return
	 */
	@RequestMapping(value="lists/sizes")
	public @ResponseBody List<SizeDto> listAvailableSize() {
		return serviceFactory.getSizeService().listsAllSizes();
	}
}
