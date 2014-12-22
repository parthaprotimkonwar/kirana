package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Location;
import com.generic.core.model.entities.Shops;
import com.generic.core.respository.ShopsRepository;
import com.generic.core.services.service.ShopsServiceI;
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
