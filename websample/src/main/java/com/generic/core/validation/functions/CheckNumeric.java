package com.generic.core.validation.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.generic.core.utilities.UtilConstants;
import com.generic.rest.dto.ResponseDto;

/**
 * Checks if the String contains only number.
 * @author pkonwar
 */
public class CheckNumeric implements ValidationFunction{

	private String aString;
	@Override
	public ResponseDto validate(String string) {
		
		this.aString = string;
		Boolean check = checkNumeric();
		if(!check) {
			return new ResponseDto("0", "Not Numberic : Failed");
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
