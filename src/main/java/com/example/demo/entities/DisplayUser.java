/**
 * Copyright(C) 2016  Luvina
 * DisplayUser.java,Dec 20, 2016,HP
 */
package com.example.demo.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * @author HP
 *
 *         DisplayUser lớp chứa các thông tin hiển thị của user
 */

public class DisplayUser {
	private int id;
	private String username;
	private String gender;
	private String birthdate;
	private String insuranceNumber;
	private String startDate;
	private String endDate;
	private String placeOfRegister;
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public DisplayUser(int id, String username, String gender, String birthdate, String insuranceNumber,
			String startDate, String endDate, String placeOfRegister) {
		super();
		this.id = id;
		this.username = username;
		this.gender = gender;
		this.birthdate = birthdate;
		this.insuranceNumber = insuranceNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.placeOfRegister = placeOfRegister;
	}

	public DisplayUser() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthdate
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the insuranceNumber
	 */
	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	/**
	 * @param insuranceNumber
	 *            the insuranceNumber to set
	 */
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the placeOfRegister
	 */
	public String getPlaceOfRegister() {
		return placeOfRegister;
	}

	/**
	 * @param placeOfRegister
	 *            the placeOfRegister to set
	 */
	public void setPlaceOfRegister(String placeOfRegister) {
		this.placeOfRegister = placeOfRegister;
	}
}
