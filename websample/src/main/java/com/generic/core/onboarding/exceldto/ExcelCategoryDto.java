package com.generic.core.onboarding.exceldto;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.generic.core.utilities.Util;

public class ExcelCategoryDto implements Excel {

	// CategoryId CategoryName ParentCategory
	private String categoryId;
	private String categoryName;
	private String parentCategory;

	public ExcelCategoryDto() {
	}
	
	public ExcelCategoryDto(String categoryId,String categoryName, String parentCategory) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.parentCategory = parentCategory;
	}
	
	
	@Override
	public Object createDataTypeObject(Row row) {
		Cell categoryId = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
		Cell categoryName = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
		Cell parentCategory = row.getCell(2, Row.CREATE_NULL_AS_BLANK);

		if(Util.allValuesAreNullAndEmpty(categoryId.toString(), categoryName.toString(), parentCategory.toString()))
			return null;
		
		return new ExcelCategoryDto(categoryId.toString(), categoryName.toString(), parentCategory.toString());
	}
	
	@Override
	public String toString() {
		return "CategoryId: " + categoryId + "| CategoryName: " + categoryName + "| parentCategory : " + parentCategory;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}

}
