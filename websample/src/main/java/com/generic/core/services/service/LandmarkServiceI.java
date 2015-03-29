package com.generic.core.services.service;

import java.util.List;

import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.rest.dto.LandmarkDto;
import com.generic.rest.dto.ResponseDto;

public interface LandmarkServiceI {

	/**
	 * Onboard Landmarks from excel sheet
	 * @param locations
	 * @return
	 */
	List<ResponseDto> onboardLandmarks(ExcelSheetObject excelSheetObject);
	
	/**
	 * Check if a particular landmark is present or not.
	 * @param landmarkId
	 * @return
	 */
	Boolean landmarkPresent(String landmarkId);
	
	/**
	 * Lists all landmark for a Area
	 * @param areaId
	 * @return
	 */
	List<LandmarkDto> listsLandmarkForArea(String areaId);
	
}
