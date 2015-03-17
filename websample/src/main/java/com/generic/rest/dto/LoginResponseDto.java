package com.generic.rest.dto;

import java.io.Serializable;

public class LoginResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginResponseDto(ResponseDto responseStatus, LoginDto loginDetails) {
		this.responseStatus = responseStatus;
		this.loginDetails = loginDetails;
	}

	private ResponseDto responseStatus;
	private LoginDto loginDetails;

	public ResponseDto getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseDto responseStatus) {
		this.responseStatus = responseStatus;
	}

	public LoginDto getLoginDetails() {
		return loginDetails;
	}

	public void setLoginDetails(LoginDto loginDetails) {
		this.loginDetails = loginDetails;
	}
}
