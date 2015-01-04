package com.generic.core.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER_INTERESTS", schema="transaction")
public class UserInterests implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Users theUser;
	
	@Column(name="INTERESTED_ITEMS", length=20)
	private String interestedItems;


	public String getInterestedItems() {
		return interestedItems;
	}

	public void setInterestedItems(String interestedItems) {
		this.interestedItems = interestedItems;
	}

	/*public Users getTheUser() {
		return theUser;
	}

	public void setTheUser(Users theUser) {
		this.theUser = theUser;
	}*/

	
	
}
