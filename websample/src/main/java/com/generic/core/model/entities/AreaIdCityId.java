package com.generic.core.model.entities;

import static com.generic.core.utilities.Util.chooseFirstIfNotNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AreaIdCityId implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AreaIdCityId() {}
	
	public AreaIdCityId(String areaId, City city) {
		this.areaId = areaId;
		this.city = city;
	}

	@Column(name="AREA_ID")
	private String areaId;
	
	@ManyToOne
	@JoinColumn(name = "CITY_ID")
	private City city;

	@Override
	public int hashCode() {
		String areaId = chooseFirstIfNotNull(this.areaId, "");
		String cityId = chooseFirstIfNotNull(this.city.getCityId() , "");
		return areaId.hashCode() + cityId.hashCode();
	}
	
	@Override
	public boolean equals(Object that) {
		if(that == null || this.getClass() != that.getClass())
			return false;
		AreaIdCityId shopIdLandmarkId = (AreaIdCityId)that;
		return this.getAreaId().equals(shopIdLandmarkId.getAreaId()) && 
					this.getCity().getCityId().equals(shopIdLandmarkId.getCity().getCityId());
	}
	
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
}
