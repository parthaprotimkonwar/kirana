package com.generic.core.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.core.model.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, String>{
	
}
