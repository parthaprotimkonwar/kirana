package com.generic.rest.dto;

public class CartDto {

	private String itemId;
	private String quantity;
	
	/**
	 * Mandatorily this no-arg constructor is required as 
	 * Jackson uses this to construct an object
	 */
	public CartDto() {}
	
	public CartDto(String itemId, String quantity) {
		this.itemId = itemId;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "[ itemId : " + itemId + " quantity : " + quantity + "]";
	}
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
