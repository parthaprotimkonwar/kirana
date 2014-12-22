package com.generic.core.model.entities;

import static com.generic.core.utilities.Util.chooseFirstIfNotNull;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ShopIdLocationId implements Serializable{

	private static final long serialVersionUID = 1L;

	public ShopIdLocationId() {}
	
	public ShopIdLocationId(Shops shops, Location location) {
		
		this.shops = shops;
		this.location = location;
	}
	
	@ManyToOne
	@JoinColumn(name="SHOP_ID")
	private Shops shops;
	
	@ManyToOne
	@JoinColumn(name="LOCATION_ID")
	private Location location;

	
	@Override
	public int hashCode() {
		String locationId = chooseFirstIfNotNull(this.location.getLocationId(), "");
		String shopId = chooseFirstIfNotNull(this.shops.getShopId() , "");
		return locationId.hashCode() + shopId.hashCode();
	}
	
	@Override
	public boolean equals(Object that) {
		
		if(that == null || this.getClass() != that.getClass())
			return false;
		ShopIdLocationId shopIdLocationId = (ShopIdLocationId)that;
		return this.shops.getShopId().equals(shopIdLocationId.getShops().getShopId()) && 
					this.location.getLocationId().equals(shopIdLocationId.getLocation().getLocationId());
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Shops getShops() {
		return shops;
	}

	public void setShops(Shops shops) {
		this.shops = shops;
	}
}
