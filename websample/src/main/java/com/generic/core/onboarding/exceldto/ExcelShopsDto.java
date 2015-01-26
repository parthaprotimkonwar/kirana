package com.generic.core.onboarding.exceldto;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.generic.core.utilities.Util;

public class ExcelShopsDto implements Excel{

	private String shopName;
	private String shopAddress;
	private String email;
	private String phoneNumber;
	private String landmarksForDelivery;
	private String shopType;
	private String ownerName;

	public ExcelShopsDto() {}
	
	public ExcelShopsDto( String shopName, String shopAddress, String email, String phoneNumber, String landmarksForDelivery, String shopType, String ownerName) {

		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.landmarksForDelivery = landmarksForDelivery;
		this.shopType = shopType;
		this.ownerName = ownerName;
	}
	
	@Override
	public Object createDataTypeObject(Row row) {
		Cell shopName = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
		Cell shopAddress = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
		Cell email = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
		Cell phoneNumber = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
		Cell landmarksForDelivery = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
		Cell shopType = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
		Cell ownerName = row.getCell(7, Row.CREATE_NULL_AS_BLANK);
		
		if(Util.allValuesAreNullAndEmpty(shopName.toString(), shopAddress.toString(), email.toString(), phoneNumber.toString(), landmarksForDelivery.toString(), shopType.toString(), ownerName.toString()))
			return null;
		return new ExcelShopsDto(shopName.toString(), shopAddress.toString(), email.toString(), phoneNumber.toString(), landmarksForDelivery.toString(), shopType.toString(), ownerName.toString());
	}
	
	@Override
	public String toString() {
		return "ShopName: " +  shopName + "|Address: " + shopAddress + "|Email: " + email + "|Phone: " + phoneNumber + "|Landmarks: "+ landmarksForDelivery + "|ShopType: " + shopType + "|OwnerName: " + ownerName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLandmarksForDelivery() {
		return landmarksForDelivery;
	}

	public void setLandmarksForDelivery(String landmarksForDelivery) {
		this.landmarksForDelivery = landmarksForDelivery;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}
