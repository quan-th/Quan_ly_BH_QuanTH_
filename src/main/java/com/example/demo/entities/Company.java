/**
 * Copyright(C) 2016  Luvina
 * Company.java,Dec 19, 2016,HP
 */
package com.example.demo.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * @author HP 
 * Company
 */

public class Company {
	private int companyID;
	private String companyName;
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	/**
	 * @return the companyID
	 */
	public int getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Company(int companyID, String companyName) {
		super();
		this.companyID = companyID;
		this.companyName = companyName;
	}

}
