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
@Table(name="CATEGORIES")
public class Categories implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CATEGORY_ID")
	private String categoryId;
	
	@Column(name="CATEGORY_NAME")
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
