package com.generic.core.model.entities;

import static com.generic.core.utilities.Util.chooseFirstIfNotNull;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	public Area(String areaId) {
		this.areaId = areaId;
	}

	public Area(String areaId, String areaName) {
		this.areaId = areaId;
		this.areaName = areaName;
	}
	
	public Area(String areaId, String areaName, City city) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.city = city;
	}
	
	@Id
	@Column(name="AREA_ID")
	private String areaId;
	
	@Column(name = "AREA_NAME", length = 30)
	private String areaName;

	@ManyToOne
	@JoinColumn(name = "CITY_ID")
	private City city;
	
	@OneToMany(mappedBy = "area", cascade=CascadeType.ALL)
	private Set<Landmark> landmark;

	@Override
	public int hashCode() {
		String areaId = chooseFirstIfNotNull(this.areaId, "");
		return areaId.hashCode();
	}
	
	@Override
	public boolean equals(Object that) {
		if(that == null || this.getClass() != that.getClass())
			return false;
		Area area = (Area)that;
		return this.areaId.equals(area.getAreaId());
	}
	
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