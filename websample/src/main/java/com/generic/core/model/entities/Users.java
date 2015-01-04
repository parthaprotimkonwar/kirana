package com.generic.core.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="USERS", schema="transaction")
public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Users() {}
	
	public Users(String userId) {
		this.userId = userId;
	}
	
	@Id
	@Column(name="USER_ID", length=20)
	private String userId;
	
	@Column(name="USER_NAME", length=20)
	private String userName;
	
	@Column(name="EMAIL", length=100)
	private String email;
	
	@Column(name="PHONE", length=15)
	private String phone;
	
	@Column(name="HOME_ADDRESS", length=100)
	private String homeAddress;
	
	@Column(name="UNSAVED_CART_ITEMS", length=200)
	private String unsavedCartItems;
	
	@Column(name="PASSWORD", length=100)
	private String password;
	
	@Column(name="DELIVERY_ADDRESS", length=100)
	private String deliveryAddress;
	
	@Column(name="CREATION_TIME", length=20)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;
	
	@Column(name="LAST_ACCESSED_TIME", length=20)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastAccessedTime;
	
	@OneToMany(mappedBy="theUser")
	private Set<Transactions> transactions;

	@OneToMany(mappedBy="theUser")
	private Set<UserInterests> userInterests;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getUnsavedCartItems() {
		return unsavedCartItems;
	}

	public void setUnsavedCartItems(String unsavedCartItems) {
		this.unsavedCartItems = unsavedCartItems;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getLastAccessedTime() {
		return lastAccessedTime;
	}

	public void setLastAccessedTime(Date lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	public Set<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transactions> transactions) {
		this.transactions = transactions;
	}
	
}
