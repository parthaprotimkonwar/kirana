package com.generic.core.onboarding.exceldto;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.generic.core.utilities.Util;

public class ExcelItemsDto implements Excel{

	private String itemName;
	private String description;
	private String category;
	private String price;
	private String unit;
	private String discount;
	private String status;
	private String quantityValues;
	private String imageName;
	private String brand;
	public ExcelItemsDto() {}
	
	public ExcelItemsDto(String itemName, String description, String category, String price, String unit, String discount, String status, String quantityValues, String imageName, String brand) {
		this.itemName = itemName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.unit = unit;
		this.discount = discount;
		this.status = status;
		this.quantityValues = quantityValues;
		this.imageName = imageName;
		this.brand = brand;
	}
	
	@Override
	public Object createDataTypeObject(Row row) {
		Cell itemName = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
		Cell description = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
		Cell category = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
		Cell price = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
		Cell unit = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
		Cell discount = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
		Cell status = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
		Cell quantityValues = row.getCell(7, Row.CREATE_NULL_AS_BLANK);
		Cell imageName = row.getCell(8, Row.CREATE_NULL_AS_BLANK);
		Cell brand = row.getCell(9, Row.CREATE_NULL_AS_BLANK);

		if(Util.allValuesAreNullAndEmpty(itemName.toString(), description.toString(), category.toString(), price.toString(), unit.toString(), discount.toString(), status.toString(), quantityValues.toString(), imageName.toString(), brand.toString()))
			return null;
		
		return new ExcelItemsDto(itemName.toString(), description.toString(), category.toString(), price.toString(), unit.toString(), discount.toString(), status.toString(), quantityValues.toString(), imageName.toString(), brand.toString());
	}
	
	@Override
	public String toString() {
		return "ItemName: " + itemName + "|Description: " + description + "|Category: " + category + "|Price: " + price + "|Unit: " + unit + "|Discount: " + discount + "|Status: " + status + "|QuantityValues :" + quantityValues + "|ImageName: " + imageName + "|Brand: " + brand;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQuantityValues() {
		return quantityValues;
	}

	public void setQuantityValues(String quantityValues) {
		this.quantityValues = quantityValues;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}
