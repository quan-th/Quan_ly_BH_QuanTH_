/**
 * Copyright(C) 2016  Luvina
 * DetailUser.java,Dec 27, 2016,HP
 */
package com.example.demo.entities;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

/**
 * @author HP
 *
 *         DetailUser
 */
public class DetailUser extends DisplayUser {

	private String company;

	public DetailUser(int id, String username, String gender, String birthdate, String insuranceNumber,
			String startDate, String endDate, String placeOfRegister, String company) {
		super(id, username, gender, birthdate, insuranceNumber, startDate, endDate, placeOfRegister);
		this.company = company;
	}
	@Override
	public boolean equals(Object obj) {
		DetailUser detailUserTemp=(DetailUser) obj;
		return super.equals(obj)&& company.equals(detailUserTemp.company);
	}
	/**
	 * 
	 */
	public DetailUser() {
		super();
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
}
