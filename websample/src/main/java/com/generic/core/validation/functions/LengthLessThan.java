package com.generic.core.validation.functions;

import com.generic.core.utilities.Util;
import com.generic.core.utilities.Validation;
import com.generic.rest.constants.Constants;
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
	public ResponseDto validate(String objectKey, String objectValue, int objectNumber) {
		
		aString = objectValue;
		if(Util.isNullAndEmpty(objectValue)) {
			String errorResponse = Validation.generateErrorString(objectNumber, objectKey, Constants.LOGGER_WARNING, Constants.VALIDATION_NULL_DATA_MESSAGE);
			return new ResponseDto(Constants.VALIDATION_NULL_DATA_CODE, errorResponse);
		}
		
		Boolean check = stringLengthLessThanMaxLength();
		if(!check) {
			String errorResponse = Validation.generateErrorString(objectNumber, objectKey, Constants.LOGGER_ERROR, Constants.VALIDATION_LENGTH_EXCEED_MESSAGE + maxlength);
			return new ResponseDto(Constants.VALIDATION_LENGTH_EXCEED_CODE, errorResponse);
		}
		return null;
	}
	
	private Boolean stringLengthLessThanMaxLength() {
		return aString.length() <=maxlength ? true : false;
	}
	
}
