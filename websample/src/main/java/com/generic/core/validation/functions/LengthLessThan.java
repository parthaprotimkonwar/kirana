package com.generic.core.validation.functions;

import com.generic.rest.dto.ResponseDto;

/**
 * Length should be less than the specific length
 * @author pkonwar
 */
public class LengthLessThan implements ValidationFunction{

	private int maxlength = Integer.MAX_VALUE;
	private String aString;
	
	public LengthLessThan(int maxLength) {
		this.maxlength = maxLength;
	}
	
	@Override
	public ResponseDto validate(String string) {
		
		aString = string;
		
		Boolean check = stringLengthLessThanMaxLength();
		if(!check)
			return new ResponseDto("200", "LengthLessThan validation FAILED");
		return null;
	}
	
	private Boolean stringLengthLessThanMaxLength() {
		return aString.length() <=maxlength ? true : false;
	}
	
}
