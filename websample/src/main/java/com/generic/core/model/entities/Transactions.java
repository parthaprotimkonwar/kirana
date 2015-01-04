package com.generic.core.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static com.generic.core.utilities.Util.*;

@Entity
@Table(name="TRANSACTIONS", schema="transaction")
public class Transactions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TXN_ID", length=20)
	private String txnId;
	
	@Column(name="TXN_CREATED_TIME", length=20)
	@Temporal(TemporalType.TIMESTAMP)
	private Date txnCreatedTime;
	
	//@Column(name="TXN_UPDATED_TIME", columnDefinition="DATETIME")
	@Column(name="TXN_UPDATED_TIME", length=20)
	@Temporal(TemporalType.TIMESTAMP)
	private Date txnUpdatedTime;
	
	@Column(name="PREFERRED_DELIVERY_DATE", length=20)
	@Temporal(TemporalType.DATE)
	private Date preferredDeliveryDate;
	
	@Column(name="PREFERRED_DELIVERY_TIMESLOT", length=20)
	private String preferredDeliveryTimeSlot;
	
	@Column(name="ITEMS", length=200)
	private String items;
	
	@Column(name="DELIVERY_ADDRESS", length=100)
	private String deliveryAddress;
	
	@Column(name="PAYMENT_MODE", length=20)
	private String paymentMode;
	
	@Column(name="PAYMENT_STATUS", length=20)
	private String paymentStatus;
	
	@Column(name="DELIVERY_STATUS", length=20)
	private String deliveryStatus;
	
	@ManyToOne
	@JoinColumn(name="SHOP_ID")
	private Shops shop;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Users theUser;

	public Transactions addTxnId(String txnId) {
		setTxnId(txnId);
		return this;
	}
	
	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		if(!isNullAndEmpty(items))
			this.items = items;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		if(!isNullAndEmpty(deliveryAddress))
			this.deliveryAddress = deliveryAddress;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		if(!isNullAndEmpty(paymentMode))
			this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		if(!isNullAndEmpty(paymentStatus))
			this.paymentStatus = paymentStatus;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		if(!isNullAndEmpty(deliveryStatus))
			this.deliveryStatus = deliveryStatus;
	}

	public Shops getShop() {
		return shop;
	}

	public void setShop(Shops shop) {
		if(shop != null)
			this.shop = shop;
	}

	public Date getTxnCreatedTime() {
		return txnCreatedTime;
	}

	public void setTxnCreatedTime(Date txnCreatedTime) {
		if(txnCreatedTime != null)
			this.txnCreatedTime = txnCreatedTime;
	}

	public Date getTxnUpdatedTime() {
		return txnUpdatedTime;
	}

	public void setTxnUpdatedTime(Date txnUpdatedTime) {
		if(txnUpdatedTime != null)
			this.txnUpdatedTime = txnUpdatedTime;
	}

	public Date getPreferredDeliveryDate() {
		return preferredDeliveryDate;
	}

	public void setPreferredDeliveryDate(Date preferredDeliveryDate) {
		this.preferredDeliveryDate = preferredDeliveryDate;
	}

	public String getPreferredDeliveryTimeSlot() {
		return preferredDeliveryTimeSlot;
	}

	public void setPreferredDeliveryTimeSlot(String preferredDeliveryTimeSlot) {
		this.preferredDeliveryTimeSlot = preferredDeliveryTimeSlot;
	}

	public Users getTheUser() {
		return theUser;
	}

	public void setTheUser(Users theUser) {
		if(theUser != null)
			this.theUser = theUser;
	}
}
