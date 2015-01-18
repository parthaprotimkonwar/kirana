package com.generic.rest.constants;

public enum CheckoutType {

	GUEST("GUEST"), LOGIN("LOGIN"), FACEBOOK("FACEBOOK"), TWITTER("TWITTER"), GPLUS("GPLUS");
	
	private String value;
	
	private CheckoutType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
