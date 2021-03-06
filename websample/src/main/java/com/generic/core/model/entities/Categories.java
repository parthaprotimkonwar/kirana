package com.generic.core.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORIES", schema="factory")
public class Categories implements Serializable{


	private static final long serialVersionUID = 1L;
	
	public Categories() {
	}
	
	public Categories(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public Categories(String categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public Categories(String categoryId, String categoryName, Categories parentCategory) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.parentCategory = parentCategory;
	}
	
	@Id
	@Column(name="CATEGORY_ID", length=20)
	private String categoryId;
	
	@Column(name="CATEGORY_NAME", length=20)
	private String categoryName;
	
	@ManyToOne
	@JoinColumn(name="PARENT_CATEGORY")
	private Categories parentCategory;
	
	@OneToMany(mappedBy="parentCategory")
	private Set<Categories> childCategories;
	
	@OneToMany(mappedBy="category")
	private Set<Items> items;

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

	public Categories getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Categories parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Set<Categories> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Set<Categories> childCategories) {
		this.childCategories = childCategories;
	}

	public Set<Items> getItems() {
		return items;
	}

	public void setItems(Set<Items> items) {
		this.items = items;
	}
	
}
