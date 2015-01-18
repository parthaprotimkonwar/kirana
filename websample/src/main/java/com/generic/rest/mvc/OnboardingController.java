package com.generic.rest.mvc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generic.core.onboarding.exceldto.ExcelLocationDto;
import com.generic.core.services.serviceimpl.ServicesFactory;
import com.generic.core.utilities.ExcelUtilities;
import com.generic.core.validation.validate.ValidationRules;
import com.generic.rest.dto.ResponseDto;

@Controller
@RequestMapping(value="/rest/onboarding")
public class OnboardingController {

	@Resource
	private ServicesFactory serviceFactory;
	
	@RequestMapping(value="location")
	public @ResponseBody List<ResponseDto> onboardLocation() {
		
		List<Object> locationSheet = null;
		List<ResponseDto> response = null;
		try {
			locationSheet = ExcelUtilities.readExcelSheet("asdf", ExcelLocationDto.class);
			response = ExcelUtilities.validate(ExcelUtilities.class, locationSheet, ValidationRules.locationRules);
			
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
}
