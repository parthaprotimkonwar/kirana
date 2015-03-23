package com.generic.core.model.entities;

import static com.generic.core.utilities.Util.chooseFirstIfNotNull;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ShopIdLandmarkId implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShopIdLandmarkId() {}
	
	public ShopIdLandmarkId(Shops shops, Landmark landmark) {
		this.shops = shops;
		this.landmark = landmark;
	}
	
	@ManyToOne
	@JoinColumn(name="LANDMARK_ID", referencedColumnName="LANDMARK_ID")
	private Landmark landmark;

	@ManyToOne
	@JoinColumn(name="SHOP_ID")
	private Shops shops;
	
	@Override
	public int hashCode() {
		String landmarkId = chooseFirstIfNotNull(this.landmark.getLandmarkId(), "");
		String shopId = chooseFirstIfNotNull(this.shops.getShopId() , "");
		return landmarkId.hashCode() + shopId.hashCode();
	}
	
	@Override
	public boolean equals(Object that) {
		if(that == null || this.getClass() != that.getClass())
			return false;
		ShopIdLandmarkId shopIdLandmarkId = (ShopIdLandmarkId)that;
		return this.shops.getShopId().equals(shopIdLandmarkId.getShops().getShopId()) && 
					this.landmark.getLandmarkId().equals(shopIdLandmarkId.getLandmark().getLandmarkId());
	}
	
	public Shops getShops() {
		return shops;
	}

	public void setShops(Shops shops) {
		this.shops = shops;
	}

	public Landmark getLandmark() {
		return landmark;
	}

	public void setLandmark(Landmark landmark) {
		this.landmark = landmark;
	}
	
}
