package com.generic.core.onboarding.exceldto;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.generic.core.utilities.Util;

public class ExcelItemsDto implements Excel{

	//ITEM_ID	BRAND	DESCRIPTION	IMAGE_NAME	ITEM_NAME	CATEGORY_ID
	
	private String itemId;
	private String brand;
	private String description;
	private String imageName;
	private String itemName;
	private String categoryId;
	public ExcelItemsDto() {}
	
	public ExcelItemsDto(String itemId, String brand, String description, String imageName, String itemName, String categoryId) {
		this.itemId = itemId;
		this.brand = brand;
		this.description = description;
		this.imageName = imageName;
		this.itemName = itemName;
		this.categoryId = categoryId;
	}
	
	@Override
	public Object createDataTypeObject(Row row) {
		Cell itemId = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
		Cell brand = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
		Cell description = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
		Cell imageName = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
		Cell itemName = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
		Cell categoryId = row.getCell(5, Row.CREATE_NULL_AS_BLANK);

		if(Util.allValuesAreNullAndEmpty(itemId.toString(), brand.toString(), description.toString(), imageName.toString(), itemName.toString(), categoryId.toString()))
			return null;
		
		return new ExcelItemsDto(itemId.toString(), brand.toString(), description.toString(), imageName.toString(), itemName.toString(), categoryId.toString());
	}
	
	@Override
	public String toString() {
		return "ItemId:" + itemId + "|Brand: " + brand + "|Description: " + description + "|ImageName: " + imageName + "|ItemName: " + itemName + "|CategoryId :" + categoryId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
}
