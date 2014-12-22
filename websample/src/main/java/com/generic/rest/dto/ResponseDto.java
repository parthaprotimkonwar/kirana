package com.generic.rest.dto;

public class ResponseDto {

	private String statusCode;
	private String statusMessage;
	
	public ResponseDto( String statusCode,  String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}
}
