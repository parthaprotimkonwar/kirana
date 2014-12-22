package com.generic.rest.dto;


public class TransactionDto {

	private String deliveryAddress;
	private String paymentMode;
	private String paymentStatus;
	private String deliveryStatus;
	private String preferredDeliveryDate;
	private String preferreddeliveryTimeSlot;
	
	public TransactionDto() {}
	
	TransactionDto(String paymentMode, String paymentStatus, String deliveryStatus) {
		this.paymentMode = paymentMode;
		this.deliveryStatus = deliveryStatus;
	}
	
	public TransactionDto addDeliveryAddress(String deliveryAddress) {
		this.setDeliveryAddress(deliveryAddress);
		return this;
	}
	
	public TransactionDto addPaymentStatus(String paymentStatus) {
		this.setPaymentStatus(paymentStatus);
		return this;
	}
	
	public TransactionDto addDeliveryStatus(String deliveryStatus) {
		this.setDeliveryStatus(deliveryStatus);
		return this;
	}
	
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getPreferredDeliveryDate() {
		return preferredDeliveryDate;
	}

	public void setPreferredDeliveryDate(String preferredDeliveryDate) {
		this.preferredDeliveryDate = preferredDeliveryDate;
	}

	public String getPreferreddeliveryTimeSlot() {
		return preferreddeliveryTimeSlot;
	}

	public void setPreferreddeliveryTimeSlot(String preferreddeliveryTimeSlot) {
		this.preferreddeliveryTimeSlot = preferreddeliveryTimeSlot;
	}
	
}
