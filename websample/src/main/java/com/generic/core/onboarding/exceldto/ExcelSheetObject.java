package com.generic.core.onboarding.exceldto;

import java.util.List;

public class ExcelSheetObject {

	private String excelSheetName;
	private List<Object> rows;

	public ExcelSheetObject() {
	}

	public ExcelSheetObject(String excelSheetName, List<Object> rows) {
		this.excelSheetName = excelSheetName;
		this.rows = rows;
	}

	public String getExcelSheetName() {
		return excelSheetName;
	}

	public void setExcelSheetName(String excelSheetName) {
		this.excelSheetName = excelSheetName;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

}
