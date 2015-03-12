package com.generic.rest.dto;

public class CityDto {

	private String cityId;
	private String cityName;

	public CityDto() {
	}
	
	public CityDto(String cityId, String cityName) {
		this.cityId = cityId;
		this.cityName = cityName;
	}
	
	@Override
	public boolean equals(Object thatCityDto) {
		if(thatCityDto == null || thatCityDto.getClass() != this.getClass())
			return false;
		
		CityDto thatLocation = (CityDto)thatCityDto;
		return thatLocation.cityId.equals(this.cityId);
	}
	
	@Override
	public int hashCode() {
		return cityId == null || cityId.trim().isEmpty() ? 17 : cityId.hashCode();
	}
	
	@Override
	public String toString() {
		return this.cityId + " : " + this.cityName ;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
