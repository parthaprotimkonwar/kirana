package com.generic.core.onboarding.exceldto;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.generic.core.utilities.Util;

public class ExcelShopItemDto implements Excel{

	// ITEM_ID SIZE_ID PRICE DISCOUNT STATUS SHOP_ID

	private String itemId;
	private String sizeId;
	private String price;
	private String discount;
	private String status;
	private String shopId;

	public ExcelShopItemDto() {}
	
	public ExcelShopItemDto( String itemId, String sizeId, String price, String discount, String status, String shopId) {
		 this.itemId = itemId;
		 this.sizeId = sizeId;
		 this.price = price;
		 this.discount = discount;
		 this.status = status;
		 this.shopId = shopId;
	}
	
	@Override
	public Object createDataTypeObject(Row row) {
		Cell itemId = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
		Cell sizeId = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
		Cell price = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
		Cell discount = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
		Cell status = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
		Cell shopId = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
		
		if(Util.allValuesAreNullAndEmpty(itemId.toString(), sizeId.toString(), price.toString(), discount.toString(), status.toString(), shopId.toString()))
			return null;
		return new ExcelShopItemDto(itemId.toString(), sizeId.toString(), price.toString(), discount.toString(), status.toString(), shopId.toString());
	}
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getSizeId() {
		return sizeId;
	}

	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

}
