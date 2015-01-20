package com.generic.core.validation.functions;

import com.generic.rest.dto.ResponseDto;

public interface ValidationFunction {

	/**
	 * Validates the @objectValue
	 * @param objectKey : the field name
	 * @param objectValue : the field value
	 * @param objectNumber : the row number of the object
	 * @return
	 */
	ResponseDto validate(String objectKey, String objectValue, int objectNumber);
	
}
