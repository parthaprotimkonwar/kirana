package com.generic.core.services.service;

import java.util.List;

import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.rest.dto.ResponseDto;

public interface LandmarkServiceI {

	/**
	 * Onboard Landmarks from excel sheet
	 * @param locations
	 * @return
	 */
	List<ResponseDto> onboardLandmarks(ExcelSheetObject excelSheetObject);
}
