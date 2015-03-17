package com.generic.core.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generic.core.model.entities.City;
import com.generic.core.model.entities.ShopIdLandmarkId;
import com.generic.core.model.entities.ShopsLandmark;

public interface ShopsLandmarkRepository extends JpaRepository<ShopsLandmark, ShopIdLandmarkId>{

	//List<ShopsLandmark> findByShopIdLandmarkIdLandmarkAreaCity(City city);
	List<ShopsLandmark> findByShopIdLandmarkIdLandmarkLandmarkIdAreaIdAreaAreaIdCityIdCity(City city);
}
