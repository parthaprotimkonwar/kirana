package com.generic.core.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AREA", schema = "factory")
public class Area implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Area() {
	}
	
	public Area(AreaIdCityId areaIdCityId) {
		this.areaIdCityId = areaIdCityId;
	}

	public Area(AreaIdCityId areaIdCityId, String areaName) {
		this.areaIdCityId = areaIdCityId;
		this.areaName = areaName;
	}
	
	@EmbeddedId
	private AreaIdCityId areaIdCityId;
	
	@Column(name = "AREA_NAME", length = 30)
	private String areaName;

	@OneToMany(mappedBy = "landmarkIdAreaId.area", cascade=CascadeType.ALL)
	private Set<Landmark> landmark;

	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Set<Landmark> getLandmark() {
		return landmark;
	}

	public void setLandmark(Set<Landmark> landmark) {
		this.landmark = landmark;
	}

	public AreaIdCityId getAreaIdCityId() {
		return areaIdCityId;
	}

	public void setAreaIdCityId(AreaIdCityId areaIdCityId) {
		this.areaIdCityId = areaIdCityId;
	}

}