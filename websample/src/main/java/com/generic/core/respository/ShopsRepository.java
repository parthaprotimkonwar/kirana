package com.generic.core.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.core.model.entities.Shops;

@Repository
public interface ShopsRepository extends JpaRepository<Shops, String>{

	//List<Shops> findByLocation(Location location);
}
