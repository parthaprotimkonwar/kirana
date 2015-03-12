package com.generic.core.onboarding.exceldto;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.generic.core.utilities.Util;

public class ExcelShopsDto implements Excel{

	private String shopId;
	private String shopName;
	private String shopAddress;
	private String email;
	private String phoneNumber;
	private String landmarksForDelivery;
	private String shopType;
	private String ownerName;
	private String tags;
	
	public ExcelShopsDto() {}
	
	public ExcelShopsDto( String shopId, String shopName, String shopAddress, String email, String phoneNumber, String landmarksForDelivery, String shopType, String ownerName, String tags) {
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.landmarksForDelivery = landmarksForDelivery;
		this.shopType = shopType;
		this.ownerName = ownerName;
		this.tags = tags;
	}
	
	@Override
	public Object createDataTypeObject(Row row) {
		Cell shopId = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
		Cell shopName = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
		Cell shopAddress = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
		Cell email = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
		Cell phoneNumber = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
		Cell landmarksForDelivery = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
		Cell shopType = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
		Cell ownerName = row.getCell(7, Row.CREATE_NULL_AS_BLANK);
		Cell tags = row.getCell(8, Row.CREATE_NULL_AS_BLANK);
		
		if(Util.allValuesAreNullAndEmpty(shopId.toString(), shopName.toString(), shopAddress.toString(), email.toString(), phoneNumber.toString(), landmarksForDelivery.toString(), shopType.toString(), ownerName.toString(), tags.toString()))
			return null;
		return new ExcelShopsDto(shopId.toString(),shopName.toString(), shopAddress.toString(), email.toString(), phoneNumber.toString(), landmarksForDelivery.toString(), shopType.toString(), ownerName.toString(), tags.toString());
	}
	
	@Override
	public String toString() {
		return "ShopId: " +  shopId + "|ShopName: " +  shopName + "|Address: " + shopAddress + "|Email: " + email + "|Phone: " + phoneNumber + "|Landmarks: "+ landmarksForDelivery + "|ShopType: " + shopType + "|OwnerName: " + ownerName + "|Tags: " + tags;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
