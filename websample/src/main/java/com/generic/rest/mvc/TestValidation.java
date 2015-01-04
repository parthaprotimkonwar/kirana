package com.generic.rest.mvc;

import java.util.ArrayList;
import java.util.List;

import com.generic.core.onboarding.exceldto.ExcelLocationDto;
import com.generic.core.validation.validate.GenericValidation;
import com.generic.core.validation.validate.ValidationRules;
import com.generic.rest.dto.ResponseDto;

public class TestValidation {

	public static void main(String[] args) {
		List<ExcelLocationDto> locationList = new ArrayList<ExcelLocationDto>();
		
		for(int i=0;i<5;i++) {
			locationList.add(new ExcelLocationDto("City" + i, "Area" + i, "Landmark" + i));
		}
		
		GenericValidation validation  = new GenericValidation(ExcelLocationDto.class, locationList, ValidationRules.locationRules);
		List<ResponseDto> results = null;
		try {
			results = validation.validate();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(ResponseDto resp : results) {
			System.out.println(resp);
		}
	}
}
