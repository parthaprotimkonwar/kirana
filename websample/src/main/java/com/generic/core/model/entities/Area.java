package com.generic.core.model.entities;

import static com.generic.core.utilities.Util.chooseFirstIfNotNull;

import java.io.Serializable;
import java.util.Set;

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

	@Id
	@Column(name = "AREA_ID", length = 30)
	private String areaId;

	@Column(name = "AREA_NAME", length = 30)
	private String areaName;

	@ManyToOne
	@JoinColumn(name = "CITY_ID")
	private City city;

	@OneToMany(mappedBy = "area")
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
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}
