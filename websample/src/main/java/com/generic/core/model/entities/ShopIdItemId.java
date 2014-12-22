package com.generic.core.model.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static com.generic.core.utilities.Util.*;

@Embeddable
public class ShopIdItemId implements Serializable {

	private static final long serialVersionUID = 1L;

	public ShopIdItemId() {}
	
	public ShopIdItemId(Shops shop, Items item) {
		this.shop = shop;
		this.item = item;
	}
	
	@ManyToOne
	@JoinColumn(name="SHOP_ID")
	private Shops shop;

	@ManyToOne
	@JoinColumn(name="ITEM_ID")
	private Items item; //the foreign key column

	
	@Override
	public int hashCode() {
		String shopId = chooseFirstIfNotNull(shop.getShopId(), "");
		String itemId = chooseFirstIfNotNull(item.getItemId(), "");
		return shopId.hashCode() + itemId.hashCode();
	}
	
	@Override
	public boolean equals(Object thatObject) {
		
		if(thatObject == null || thatObject.getClass() != getClass())
			return false;
		
		ShopIdItemId thatShopIdItemId = (ShopIdItemId)thatObject;
		String shopId = chooseFirstIfNotNull(shop.getShopId(), "");
		String itemId = chooseFirstIfNotNull(item.getItemId(), "");
		
		return shopId.equals(thatShopIdItemId.getShop().getShopId()) 
								&& itemId.equals(thatShopIdItemId.getItem().getItemId());
	}
	
	
	public Shops getShop() {
		return shop;
	}

	public void setShop(Shops shop) {
		this.shop = shop;
	}

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}
	
}
