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
	
	//validations messages
	String LOGGER_ERROR = "ERROR";
	String LOGGER_WARNING = "WARNING";
	
	String VALIDATION_NOT_NUMERIC_CODE = "105";
	String VALIDATION_NOT_NUMERIC_MESSAGE = "Not Numeric";
	
	String VALIDATION_LENGTH_EXCEED_CODE = "106";
	String VALIDATION_LENGTH_EXCEED_MESSAGE = "Length Exceeded. Max allowed length is : ";
	
	String VALIDATION_INVALID_EMAIL_CODE = "107";
	String VALIDATION_INVALID_EMAIL_MESSAGE = "Invalid Email format";
	
	String VALIDATION_NULL_DATA_CODE = "108";
	String VALIDATION_NULL_DATA_MESSAGE = "Value is empty";
}
