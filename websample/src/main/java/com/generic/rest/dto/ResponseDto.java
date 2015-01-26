package com.generic.rest.dto;

import static com.generic.core.utilities.Util.chooseFirstIfNotNull;

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
	
	@Override
	public int hashCode() {
		String statusCode = chooseFirstIfNotNull(this.statusCode, "");
		String statusMessage = chooseFirstIfNotNull(this.statusMessage , "");
		return statusCode.hashCode() + statusMessage.hashCode();
	}
	
	@Override
	public boolean equals(Object that) {
		
		if(that == null || this.getClass() != that.getClass())
			return false;
		
		ResponseDto responseDto = (ResponseDto)that;
		return statusCode.equals(responseDto.getStatusCode()) && statusMessage.equals(responseDto.getStatusMessage());
	}
	
	@Override
	public String toString() {
		return "StatusCode : " + statusCode + " -- " + "StatusMessage : " + statusMessage;
	}
}
