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
@Table(name="LANDMARK", schema="factory")
public class Landmark implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Landmark() {}
	
	public Landmark(String landmarkId) {
		this.landmarkId = landmarkId;
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
	@JoinColumn(name="AREA_ID", referencedColumnName="AREA_ID")
	private Area area;
	
	@OneToMany(mappedBy="shopIdLandmarkId.landmark", cascade=CascadeType.ALL)
	private Set<ShopsLandmark> shopLandmarks;

	@Override
	public int hashCode() {
		String landmarkId = chooseFirstIfNotNull(this.landmarkId, "");
		return landmarkId.hashCode();
	}
	
	@Override
	public boolean equals(Object that) {
		if(that == null || this.getClass() != that.getClass())
			return false;
		Landmark landmark = (Landmark)that;
		return this.landmarkId.equals(landmark.getLandmarkId());
	}
	
	public String getLandmarkName() {
		return landmarkName;
	}

	public void setLandmarkName(String landmarkName) {
		this.landmarkName = landmarkName;
	}

	public Set<ShopsLandmark> getShopLandmarks() {
		return shopLandmarks;
	}

	public void setShopLandmarks(Set<ShopsLandmark> shopLandmarks) {
		this.shopLandmarks = shopLandmarks;
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
