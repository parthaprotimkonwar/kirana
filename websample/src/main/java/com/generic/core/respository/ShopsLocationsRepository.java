package com.generic.core.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.core.model.entities.Location;
import com.generic.core.model.entities.ShopIdLocationId;
import com.generic.core.model.entities.ShopsLocations;

@Repository
public interface ShopsLocationsRepository extends JpaRepository<ShopsLocations, ShopIdLocationId>{

	List<ShopsLocations> findByShopIdLocationIdLocation(Location location);
}
