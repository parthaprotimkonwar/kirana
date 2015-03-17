package com.generic.core.model.entities;

import static com.generic.core.utilities.Util.chooseFirstIfNotNull;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CITY", schema="factory")
public class City implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public City() {}
	
	public City(String cityId) {
		this.cityId = cityId;
	}
	
	public City(String cityId, String cityName) {
		this.cityId = cityId;
		this.cityName = cityName;
	}
	
	@Id
	@Column(name="CITY_ID", length=30)
	private String cityId;
	
	@Column(name="CITY_NAME", length=30)
	private String cityName;

	@OneToMany(mappedBy="AreaIdCityId.city")
	private Set<Area> area;
	
	public Set<Area> getArea() {
		return area;
	}

	public void setArea(Set<Area> area) {
		this.area = area;
	}

	@Override
	public int hashCode() {
		String cityId = chooseFirstIfNotNull(this.cityId, "");
		return cityId.hashCode();
	}
	
	@Override
	public boolean equals(Object that) {
		if(that == null || this.getClass() != that.getClass())
			return false;
		City city = (City)that;
		return this.cityId.equals(city.getCityId());
	}
	
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
