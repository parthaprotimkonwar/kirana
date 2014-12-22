package com.generic.rest.constants;

/**
 * Constants for Operational Success and Failure code and messages
 * @author pkonwar
 */
public interface Constants {

	String SUCCESS_RESPONSE_CODE = "200";
	String SUCCESS_RESPONSE_MESSAGE = "Operation Successful";
	
	String FAILURE_RESPONSE_CODE = "100";
	String FAILURE_RESPONSE_MESSAGE = "Operation Failed";
	
	String USER_NOT_LOGGED_IN_CODE = "101";
	String USER_NOT_LOGGED_IN_MESSAGE = "User has not yet logged in";
	
	String USER_LOGGED_IN_CODE = "102";
	String USER_LOGGED_IN_MESSAGE = "User is logged in";
	
	String SESSION_STOREHOUSE = "SESSION_STOREHOUSE";
	
	String USER_EXIST_CODE = "103";
	String USER_EXIST_MESSAGE = "User already exist";
	
	String INVALID_LOGIN_CODE = "104";
	String INVALID_LOGIN_MESSAGE = "Invalid username or password";
}
