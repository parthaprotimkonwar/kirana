package com.generic.rest.dto;

public class SizeDto {

	private String sizeId;
	private String[] permissibleValues;

	public SizeDto() {}
	
	public SizeDto(String sizeId, String[] permissibleValues) {
		this.sizeId = sizeId;
		this.permissibleValues = permissibleValues;
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
	
	

}
