package com.generic.rest.dto;

public class AreaDto {

	private String areaId;
	private String areaName;

	public AreaDto() {
	}
	
	public AreaDto(String areaId, String areaName) {
		this.areaId = areaId;
		this.areaName = areaName;
	}
	
	@Override
	public boolean equals(Object thatAreaDto) {
		if(thatAreaDto == null || thatAreaDto.getClass() != this.getClass())
			return false;
		
		AreaDto thatLocation = (AreaDto)thatAreaDto;
		return thatLocation.areaId.equals(this.areaId);
	}
	
	@Override
	public int hashCode() {
		return areaId == null || areaId.trim().isEmpty() ? 17 : areaId.hashCode();
	}
	
	@Override
	public String toString() {
		return this.areaId + " : " + this.areaName ;
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
}
