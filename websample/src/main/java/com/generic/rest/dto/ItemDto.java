package com.generic.rest.dto;

public class ItemDto {

	private String itemId;
	private String itemName;
	private String description;
	private String brand;
	private String imagePath;
	private String price;
	private String sizeId;
	
	public ItemDto(String itemId, String itemName, String description, String brand, String imagePath, String price, String sizeId) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.description = description;
		this.brand = brand;
		this.imagePath = imagePath;
		this.price = price;
		this.sizeId = sizeId;
	}
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSizeId() {
		return sizeId;
	}

	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null || !(other instanceof ItemDto))
			return false;
		
		if(this == other)
			return true;
		
		ItemDto otherItem = (ItemDto)other;
		return this.itemId.equals(otherItem.itemId);
	}
	
	@Override
	public int hashCode() {
		int hashcode = this.itemId == null ? 17 : this.itemId.hashCode();
		return hashcode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
