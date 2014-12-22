package com.generic.core.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SIZE")
public class Size implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SIZE_ID")
	private String sizeId;
	
	@Column(name="SIZE_NAME")
	private String sizeName;
	
	@Column(name="PERMISSIBLE_VALUES")
	private String permissibleValues;
	
	@OneToMany(mappedBy="size")
	private Set<ShopsItems> shopsItems;

	public String getSizeId() {
		return sizeId;
	}

	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getPermissibleValues() {
		return permissibleValues;
	}

	public void setPermissibleValues(String permissibleValues) {
		this.permissibleValues = permissibleValues;
	}

	public Set<ShopsItems> getShopsItems() {
		return shopsItems;
	}

	public void setShopsItems(Set<ShopsItems> shopsItems) {
		this.shopsItems = shopsItems;
	}
	
}
