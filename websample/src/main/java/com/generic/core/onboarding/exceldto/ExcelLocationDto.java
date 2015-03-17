package com.generic.core.onboarding.exceldto;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.generic.core.utilities.Util;

public class ExcelLocationDto implements Excel {

	private String areaId;
	private String areaName;
	private String landmarkId;
	private String landmarkName;
	
	public ExcelLocationDto() {
	}
	
	public ExcelLocationDto(String areaId, String areaName, String landmarkId, String landmarkName) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.landmarkId = landmarkId;
		this.landmarkName = landmarkName;
	}

	@Override
	public Object createDataTypeObject(Row row) {
		Cell areaId = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
		Cell areaName = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
		Cell landmarkId = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
		Cell landmarkName = row.getCell(3, Row.CREATE_NULL_AS_BLANK);

		if(Util.allValuesAreNullAndEmpty(areaId.toString(), areaName.toString(), landmarkId.toString(), landmarkName.toString()))
			return null;
		return new ExcelLocationDto(areaId.toString(), areaName.toString(), landmarkId.toString(), landmarkName.toString());
	}
	
	@Override
	public String toString() {
		return "areaId: " + areaId + " :: areaName: " + areaName + " :: landmarkId: " + landmarkId + " :: landmarkName: " + landmarkName;
	}
	
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getLandmarkId() {
		return landmarkId;
	}

	public void setLandmarkId(String landmarkId) {
		this.landmarkId = landmarkId;
	}

	public String getLandmarkName() {
		return landmarkName;
	}

	public void setLandmarkName(String landmarkName) {
		this.landmarkName = landmarkName;
	}

}
