package com.generic.core.onboarding.exceldto;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelLocationDto implements Excel {

	private String cityName;
	private String areaName;
	private String landmark;
	
	public ExcelLocationDto(String cityName, String areaName, String landmark) {
		this.cityName = cityName;
		this.areaName = areaName;
		this.landmark = landmark;
	}

	@Override
	public Object createDataTypeObject(Row row) {
		Cell city = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
		Cell area = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
		Cell landmark = row.getCell(2, Row.CREATE_NULL_AS_BLANK);

		return new ExcelLocationDto(city.toString(), area.toString(), landmark.toString());
	}
	
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

}
