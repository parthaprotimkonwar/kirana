package com.generic.core.validation.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.generic.core.utilities.UtilConstants;
import com.generic.core.utilities.Validation;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.ResponseDto;

/**
 * Checks if the String contains only number.
 * @author pkonwar
 */
public class CheckNumeric implements ValidationFunction{

	private String aString;
	
	@Override
	public ResponseDto validate(String objectKey, String objectValue, int objectNumber) {
		
		this.aString = objectValue;
		Boolean check = checkNumeric();
		if(!check) {
			String errorResponse = Validation.generateErrorString(objectNumber, objectKey, Constants.LOGGER_ERROR, Constants.VALIDATION_NOT_NUMERIC_MESSAGE);
			return new ResponseDto(Constants.VALIDATION_NOT_NUMERIC_CODE, errorResponse);
		}
		return null;
	}
	
	private Boolean checkNumeric(){
		Pattern pattern = Pattern.compile(UtilConstants.REGEX_IS_NOT_NUMERIC);
		Matcher matcher = pattern.matcher(aString);
		while(matcher.find(0)){
			return false;
		}
		return true;
	}
	
	
}
