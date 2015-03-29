package com.generic.core.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.core.model.entities.Area;
import com.generic.core.model.entities.City;

@Repository
public interface AreaRepository extends JpaRepository<Area, String>{

	List<Area> findByCity(City city);
}
