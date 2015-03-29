package com.generic.rest.dto;

public class SizeDto {

	private String sizeId;
	private String[] permissibleValues;

	private String sizeName;
	private String allowedValues;
	private String unit;
	
	public SizeDto() {}
	
	public SizeDto(String sizeId, String[] permissibleValues) {
		this.sizeId = sizeId;
		this.permissibleValues = permissibleValues;
	}
	
	public SizeDto(String sizeId, String sizeName, String allowedValues, String unit) {
		this.sizeId = sizeId;
		this.sizeName = sizeName;
		this.allowedValues = allowedValues;
		this.unit = unit;
	}

	public String getSizeId() {
		return sizeId;
	}

	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}

	public String[] getPermissibleValues() {
		return permissibleValues;
	}

	public void setPermissibleValues(String[] permissibleValues) {
		this.permissibleValues = permissibleValues;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getAllowedValues() {
		return allowedValues;
	}

	public void setAllowedValues(String allowedValues) {
		this.allowedValues = allowedValues;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
