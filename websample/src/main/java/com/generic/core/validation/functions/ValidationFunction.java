package com.generic.core.validation.functions;

import com.generic.rest.dto.ResponseDto;

public interface ValidationFunction {

	ResponseDto validate(String string);
	
}
