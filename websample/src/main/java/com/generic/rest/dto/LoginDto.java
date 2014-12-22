package com.generic.rest.dto;


public class LoginDto {

	public LoginDto() {
	}

	public LoginDto(String userId, String password, String checkoutType, String emailId, String phoneNo, String userName) {
		this.userId = userId;
		this.password = password;
		this.checkoutType = checkoutType;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.userName = userName;
	}
	
	private String userId;
	private String userName;
	private String password;
	private String checkoutType;
	private String emailId;
	private String phoneNo;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckoutType() {
		return checkoutType;
	}

	public void setCheckoutType(String checkoutType) {
		this.checkoutType = checkoutType;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
