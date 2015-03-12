package com.generic.core.model.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SHOPS_LANDMARK", schema="factory")
public class ShopsLandmark implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ShopIdLandmarkId shopIdLandmarkId;

	public ShopIdLandmarkId getShopIdLandmarkId() {
		return shopIdLandmarkId;
	}

	public void setShopIdLandmarkId(ShopIdLandmarkId shopIdLandmarkId) {
		this.shopIdLandmarkId = shopIdLandmarkId;
	}
	
	@Override
	public String toString() {
		return "[Shops: " + shopIdLandmarkId.getShops() + "][Landmark: " +shopIdLandmarkId.getLandmark() + "]";
	}
}
