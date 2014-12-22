package com.generic.core.model.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SHOPS_LOCATIONS")
public class ShopsLocations implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ShopIdLocationId shopIdLocationId;

	public ShopIdLocationId getShopIdLocationId() {
		return shopIdLocationId;
	}

	public void setShopIdLocationId(ShopIdLocationId shopIdLocationId) {
		this.shopIdLocationId = shopIdLocationId;
	}
	
}
