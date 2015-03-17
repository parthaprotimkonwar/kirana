package com.generic.core.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.core.model.entities.Landmark;
import com.generic.core.model.entities.LandmarkIdAreaId;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, LandmarkIdAreaId>{

}
