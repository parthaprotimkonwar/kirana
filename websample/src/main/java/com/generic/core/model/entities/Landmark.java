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
@Table(name="LANDMARK", schema="factory")
public class Landmark implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Landmark() {}
	
	public Landmark(LandmarkIdAreaId landmarkIdAreaId) {
		this.landmarkIdAreaId = landmarkIdAreaId;
	}
	
	public Landmark(LandmarkIdAreaId landmarkIdAreaId, String landmarkName) {
		this.landmarkIdAreaId = landmarkIdAreaId;
		this.landmarkName = landmarkName;
	}
	
	@EmbeddedId
	private LandmarkIdAreaId landmarkIdAreaId;
	
	@Column(name="LANDMARK_NAME", length=30)
	private String landmarkName;
	
	@OneToMany(mappedBy="shopIdLandmarkId.landmark", cascade=CascadeType.ALL)
	private Set<ShopsLandmark> shopLandmarks;

	public String getLandmarkName() {
		return landmarkName;
	}

	public void setLandmarkName(String landmarkName) {
		this.landmarkName = landmarkName;
	}

	public LandmarkIdAreaId getLandmarkIdAreaId() {
		return landmarkIdAreaId;
	}

	public void setLandmarkIdAreaId(LandmarkIdAreaId landmarkIdAreaId) {
		this.landmarkIdAreaId = landmarkIdAreaId;
	}

	public Set<ShopsLandmark> getShopLandmarks() {
		return shopLandmarks;
	}

	public void setShopLandmarks(Set<ShopsLandmark> shopLandmarks) {
		this.shopLandmarks = shopLandmarks;
	}
	
}
