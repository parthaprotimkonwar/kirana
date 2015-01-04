package com.generic.core.onboarding.exceldto;

public class ExcelLocationDto {

	private String cityName;
	private String areaName;
	private String landmark;
	
	public ExcelLocationDto(String cityName, String areaName, String landmark) {
		this.cityName = cityName;
		this.areaName = areaName;
		this.landmark = landmark;
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
