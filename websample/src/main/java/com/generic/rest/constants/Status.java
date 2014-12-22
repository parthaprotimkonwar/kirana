package com.generic.rest.constants;

public enum Status {

	DELIVERED("DELIVERED"), 
	PENDING("PENDING"), 
	FAILED_DELIVERY_ATTEMP("FAILED_DELIVERY_ATTEMP"), 
	OUT_FOR_DELIVERY("OUT_FOR_DELIVERY");
	
	private String value;
	
	private Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
