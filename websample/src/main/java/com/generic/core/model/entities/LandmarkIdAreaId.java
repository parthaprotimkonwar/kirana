package com.generic.core.model.entities;

import static com.generic.core.utilities.Util.chooseFirstIfNotNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Embeddable
public class LandmarkIdAreaId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="LANDMARK_ID", length=30)
	private String landmarkId;
	
	@ManyToOne
	@JoinColumns(
		{
			@JoinColumn(name="AREA_ID", referencedColumnName="AREA_ID"),
			@JoinColumn(name="CITY_ID", referencedColumnName="CITY_ID")
		}
	)
	private Area area;

	public LandmarkIdAreaId() {}
	
	public LandmarkIdAreaId(String landmarkId, Area area) {
		this.landmarkId = landmarkId;
		this.area = area;
	}
	
	@Override
	public int hashCode() {
		String landmarkId = chooseFirstIfNotNull(this.landmarkId, "");
		String areaId = chooseFirstIfNotNull(this.area.getAreaIdCityId().getAreaId() , "");
		return landmarkId.hashCode() + areaId.hashCode();
	}
	
	@Override
	public boolean equals(Object that) {
		if(that == null || this.getClass() != that.getClass())
			return false;
		LandmarkIdAreaId shopIdLandmarkId = (LandmarkIdAreaId)that;
		return this.getLandmarkId().equals(shopIdLandmarkId.getLandmarkId()) && 
					this.getArea().getAreaIdCityId().getAreaId().equals(shopIdLandmarkId.getArea().getAreaIdCityId().getAreaId());
	}
	
	public String getLandmarkId() {
		return landmarkId;
	}

	public void setLandmarkId(String landmarkId) {
		this.landmarkId = landmarkId;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
}
