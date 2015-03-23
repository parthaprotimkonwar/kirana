package com.generic.core.onboarding.exceldto;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.generic.core.utilities.Util;

public class ExcelSizeDto implements Excel {

	//QuantityId	QuantityName	PermissibleValues	Unit
	private String quantityId;
	private String quantityName;
	private String permissibleValues;
	private String unit;
	
	public ExcelSizeDto() {
	}
	
	public ExcelSizeDto(String quantityId, String quantityName, String permissibleValues, String unit) {
		this.quantityId = quantityId;
		this.quantityName = quantityName;
		this.permissibleValues = permissibleValues;
		this.unit = unit;
	}
	
	@Override
	public Object createDataTypeObject(Row row) {
		Cell quantityId = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
		Cell quantityName = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
		Cell permissibleValues = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
		Cell unit = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
		
		if(Util.allValuesAreNullAndEmpty(quantityId.toString(), quantityName.toString(), permissibleValues.toString(), unit.toString()))
			return null;
		return new ExcelSizeDto(quantityId.toString(), quantityName.toString(), permissibleValues.toString(), unit.toString());
	}

	@Override
	public String toString() {
		return "QuantityId : " + quantityId + " |QuantityName :" + quantityName + " | PermissibleValues :" + permissibleValues + " | unit : " + unit;
	}
	
	public String getQuantityId() {
		return quantityId;
	}

	public void setQuantityId(String quantityId) {
		this.quantityId = quantityId;
	}

	public String getQuantityName() {
		return quantityName;
	}

	public void setQuantityName(String quantityName) {
		this.quantityName = quantityName;
	}

	public String getPermissibleValues() {
		return permissibleValues;
	}

	public void setPermissibleValues(String permissibleValues) {
		this.permissibleValues = permissibleValues;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
