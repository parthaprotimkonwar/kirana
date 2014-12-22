package com.generic.core.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.core.model.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, String>{

	List<Location> findByLocationIdLike(String locationId);
	
	List<Location> findByParentLocation(Location parentLocation);
}
