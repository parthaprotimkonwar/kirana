package com.generic.core.model.entities;

import static com.generic.core.utilities.Util.chooseFirstIfNotNull;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SIZE", schema="factory")
public class Size implements Serializable {

	private static final long serialVersionUID = 1L;

	public Size() {
	}
	
	public Size(String sizeId) {
		this.sizeId = sizeId;
	}
	
	public Size(String sizeId, String sizeName, String unit, String permissibleValues) {
		this.sizeId = sizeId;
		this.sizeName = sizeName;
		this.unit = unit;
		this.permissibleValues = permissibleValues;
	}
	
	@Id
	@Column(name="SIZE_ID", length=20)
	private String sizeId;
	
	@Column(name="SIZE_NAME", length=200)
	private String sizeName;
	
	@Column(name="UNIT", length=10)
	private String unit;
	
	@Column(name="PERMISSIBLE_VALUES")
	private String permissibleValues;
	
	@OneToMany(mappedBy="size")
	private Set<ShopsItems> shopsItems;

	@Override
	public int hashCode() {
		String sizeId = chooseFirstIfNotNull(this.sizeId, "");
		return sizeId.hashCode();
	}
	
	@Override
	public boolean equals(Object that) {
		if(that == null || this.getClass() != that.getClass())
			return false;
		Size size = (Size)that;
		return this.sizeId.equals(size.getSizeId());
	}
	
	@Override
	public String toString() {
		return "SizeId :" + sizeId + "| SizeName :" + sizeName + "| Unit :" + unit + "| Permissible Values :" + permissibleValues;
	}
	
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
