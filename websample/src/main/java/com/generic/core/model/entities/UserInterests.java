package com.generic.core.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER_INTERESTS")
public class UserInterests implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Users user;
	
	@Column(name="INTERESTED_ITEMS")
	private String interestedItems;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getInterestedItems() {
		return interestedItems;
	}

	public void setInterestedItems(String interestedItems) {
		this.interestedItems = interestedItems;
	}
	
}
