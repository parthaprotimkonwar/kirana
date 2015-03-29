package com.generic.rest.dto;

import java.io.Serializable;

public class CategoryDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String categoryId;
	private String categoryName;
	private String parentCategory;
	
	public CategoryDto() {
	}
	
	public CategoryDto(String categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public CategoryDto(String categoryId, String categoryName, String parentCategory) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.parentCategory = parentCategory;
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

	@Override
	public boolean equals(Object other) {

		if(other == null || !(other instanceof CategoryDto))
			return false;
		
		if(this == other)
			return true;
		
		CategoryDto otherCategory = (CategoryDto)other;
		return this.categoryId.equals(otherCategory.categoryId);
	}
	
	@Override
	public int hashCode() {
		int hashcode = this.categoryId == null ? 17 : this.categoryId.hashCode();
		return hashcode;
	}
	
	@Override
	public String toString() {
		return this.categoryId + ":" + this.categoryName;
	}

	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}
}
