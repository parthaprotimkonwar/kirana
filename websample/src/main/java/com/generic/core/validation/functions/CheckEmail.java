package com.generic.core.validation.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.generic.core.utilities.Util;
import com.generic.core.utilities.UtilConstants;
import com.generic.core.utilities.Validation;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.ResponseDto;

public class CheckEmail implements ValidationFunction {

	private String anEmailString;
	
	@Override
	public ResponseDto validate(String objectKey, String objectValue,
			int objectNumber) {
		
		this.anEmailString = objectValue;
		if(Util.isNullAndEmpty(objectValue)) {
			String errorResponse = Validation.generateErrorString(objectNumber, objectKey, Constants.LOGGER_WARNING, Constants.VALIDATION_NULL_DATA_MESSAGE);
			return new ResponseDto(Constants.VALIDATION_NULL_DATA_CODE, errorResponse);
		}
		
		Boolean check = checkEmail();
		if(!check) {
			String errorResponse = Validation.generateErrorString(objectNumber, objectKey, Constants.LOGGER_ERROR, Constants.VALIDATION_INVALID_EMAIL_MESSAGE);
			return new ResponseDto(Constants.VALIDATION_INVALID_EMAIL_CODE, errorResponse);
		}
		return null;
	}
	
	private Boolean checkEmail(){
		Pattern pattern = Pattern.compile(UtilConstants.REGEX_IS_EMAIL);
		Matcher matcher = pattern.matcher(anEmailString);
		while(matcher.find(0)){
			if(matcher.group(0).equals(anEmailString))
				return true;
			else
				return false;
		}
		return false;
	}
	
	/*public static void main(String[] args) {
		CheckEmail chk = new CheckEmail();
		String anEmailString = "john.doe@example..com";
		System.out.println(chk.validate("email", anEmailString, 1));
		
	}*/

}
