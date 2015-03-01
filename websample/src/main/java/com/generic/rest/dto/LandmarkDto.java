package com.generic.rest.dto;

public class LandmarkDto {

	private String landmarkId;
	private String landmarkName;

	public LandmarkDto() {
	}
	
	public LandmarkDto(String landmarkId, String landmarkName) {
		this.landmarkId = landmarkId;
		this.landmarkName = landmarkName;
	}
	
	@Override
	public boolean equals(Object thatLocationDto) {
		if(thatLocationDto == null || thatLocationDto.getClass() != this.getClass())
			return false;
		
		LandmarkDto thatLocation = (LandmarkDto)thatLocationDto;
		return thatLocation.landmarkId.equals(this.landmarkId);
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
