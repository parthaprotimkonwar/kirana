package com.generic.rest.dto;

public class LandmarkDto {

	private String landmarkId;
	private String landmarkName;
	private String areaName;
	
	public LandmarkDto() {
	}
	
	public LandmarkDto(String landmarkId, String landmarkName, String areaName) {
		this.landmarkId = landmarkId;
		this.landmarkName = landmarkName;
		this.areaName = areaName;
	}
	
	@Override
	public boolean equals(Object thatLocationDto) {
		if(thatLocationDto == null || thatLocationDto.getClass() != this.getClass())
			return false;
		
		LandmarkDto thatLocation = (LandmarkDto)thatLocationDto;
		return thatLocation.landmarkId.equals(this.landmarkId);
	}
	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public int hashCode() {
		return landmarkId == null || landmarkId.trim().isEmpty() ? 17 : landmarkId.hashCode();
	}
	
	@Override
	public String toString() {
		return this.landmarkId + " : " + this.landmarkName ;
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
