package com.generic.rest.constants;


public enum DeliveryDate {

	TODAY("TODAY"), TOMORROW("TOMORROW");

	private String date;

	private DeliveryDate(String date) {
		this.date = date;
	}

}
