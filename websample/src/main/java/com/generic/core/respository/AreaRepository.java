package com.generic.core.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.core.model.entities.Area;
import com.generic.core.model.entities.AreaIdCityId;

@Repository
public interface AreaRepository extends JpaRepository<Area, AreaIdCityId>{
	
}
