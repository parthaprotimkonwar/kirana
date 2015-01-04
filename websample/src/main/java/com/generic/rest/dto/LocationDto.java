package com.generic.rest.dto;


public class LocationDto {

	private String cityId;
	private String cityName;

	public LocationDto() {
	}
	
	public LocationDto(String cityId, String cityName) {
		this.cityId = cityId;
		this.cityName = cityName;
	}
	
	@Override
	public boolean equals(Object thatLocationDto) {
		if(thatLocationDto == null || thatLocationDto.getClass() != this.getClass())
			return false;
		
		LocationDto thatLocation = (LocationDto)thatLocationDto;
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
