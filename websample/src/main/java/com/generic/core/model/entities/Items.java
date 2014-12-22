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
@Table(name="ITEMS")
public class Items implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Items() {}
	
	public Items(String itemId, String itemName, String imageName, String description) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.imageName = imageName;
		this.description = description;
	}
	
	@Id
	@Column(name="ITEM_ID")
	private String itemId;
	
	@Column(name="ITEM_NAME")
	private String itemName;
	
	@Column(name="IMAGE_NAME")
	private String imageName;

	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID")
	private Categories category;
	
	@OneToMany(mappedBy="shopIdItemId.item")
	private Set<ShopsItems> shopItem;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public Set<ShopsItems> getShopItem() {
		return shopItem;
	}

	public void setShopItem(Set<ShopsItems> shopItem) {
		this.shopItem = shopItem;
	}
	
}
