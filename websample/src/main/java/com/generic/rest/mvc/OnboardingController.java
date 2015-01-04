package com.generic.rest.mvc;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generic.core.services.serviceimpl.ServicesFactory;
import com.generic.rest.dto.ResponseDto;

@Controller
@RequestMapping(value="/rest/onboarding")
public class OnboardingController {

	@Resource
	private ServicesFactory serviceFactory;
	
	@RequestMapping(value="location")
	public @ResponseBody ResponseDto onboardLocation() {
		
		
		
		return null;
	}
	
}
