package com.generic.core.model.entities;

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
@Table(name="LANDMARK", schema="factory")
public class Landmark implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Landmark() {}
	
	public Landmark(String landmarkId) {
		this.landmarkId = landmarkId;
	}
	
	public Landmark(String landmarkId, String landmarkName) {
		this.landmarkId = landmarkId;
		this.landmarkName = landmarkName;
	}
	
	public Landmark(String landmarkId, String landmarkName, Area area) {
		this.landmarkId = landmarkId;
		this.landmarkName = landmarkName;
		this.area = area;
	}
	
	@Id
	@Column(name="LANDMARK_ID", length=30)
	private String landmarkId;
	
	@Column(name="LANDMARK_NAME", length=30)
	private String landmarkName;
	
	@ManyToOne
	@JoinColumn(name="AREA_ID")
	private Area area;
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Set<ShopsLandmark> getShopslandmark() {
		return shopslandmark;
	}

	public void setShopslandmark(Set<ShopsLandmark> shopslandmark) {
		this.shopslandmark = shopslandmark;
	}

	@OneToMany(mappedBy="shopIdLandmarkId.landmark")
	private Set<ShopsLandmark> shopslandmark;
	

	public String getLandmarkId() {
		return landmarkId;
	}

	public void setLandmarkId(String landmarkId) {
		this.landmarkId = landmarkId;
	}

	public String getLandmarkName() {
		return landmarkName;
	}

	public void setLandmarkName(String landmarkName) {
		this.landmarkName = landmarkName;
	}
	
}
