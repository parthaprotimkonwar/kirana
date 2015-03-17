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

import com.generic.core.model.entities.Area;

@Entity
@Table(name="LOCATION", schema="factory")
public class Location implements Serializable{

	private static final long serialVersionUID = 1L;

	public Location() {}
	
	public Location(String locationId) {
		this.locationId = locationId;
	}
	
	public Location(String locationId, String locationName) {
		this.locationId = locationId;
		this.locationName = locationName;
	}
	
	@Id
	@Column(name="LOCATION_ID", length=20)
	private String locationId;
	
	@Column(name="LOCATION_NAME", length=20)
	private String locationName;

	@ManyToOne
	@JoinColumn(name="PARENT_LOCATION")
	private Location parentLocation; 
	
	@OneToMany(mappedBy="parentLocation")
	private Set<Location> childLocations;
	
	@OneToMany(mappedBy="shopIdLocationId.location")
	private Set<ShopsLocations> shopslocations;

	@Override
	public int hashCode() {
		String locationId = chooseFirstIfNotNull(this.locationId, "");
		return locationId.hashCode();
	}
	
	@Override
	public boolean equals(Object that) {
		if(that == null || this.getClass() != that.getClass())
			return false;
		Area area = (Area)that;
		return this.areaId.equals(area.getAreaId());
	}
	
	@Override
	public String toString() {
		return this.locationId + " : " + this.locationName;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Location getParentLocation() {
		return parentLocation;
	}

	public void setParentLocation(Location parentLocation) {
		this.parentLocation = parentLocation;
	}

	public Set<Location> getChildLocations() {
		return childLocations;
	}

	public void setChildLocations(Set<Location> childLocations) {
		this.childLocations = childLocations;
	}

	public Set<ShopsLocations> getShopslocations() {
		return shopslocations;
	}

	public void setShopslocations(Set<ShopsLocations> shopslocations) {
		this.shopslocations = shopslocations;
	}

}
