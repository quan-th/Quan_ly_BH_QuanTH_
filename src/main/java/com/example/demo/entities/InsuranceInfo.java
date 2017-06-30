/**
  * Copyright(C) 2016  Luvina
 * InsuranceInfo.java,Jan 3, 2017,HP
 */
package com.example.demo.entities;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.demo.utils.Constant;

/**
 * @author HP
 *
 *         InsuranceInfo InsuranceInfo
 */
public class InsuranceInfo {
	public interface ValidateForCompany extends Default {
	}

	private int userId = 0;
	private String username;
	private String userPassword;
	@NotEmpty
	@Pattern(regexp = "[0-9]{10}$")
	private String insuranceNumber;
	@NotEmpty
	@Size(min = 1, max = 255)
	private String fullname;
	@NotEmpty
	private String gender;
	private String birthdate;
	private String choseCompany = Constant.ALREADY_HAVE;
	private String companyId = "0";
	@NotEmpty(groups = ValidateForCompany.class)
	private String companyName;
	@NotEmpty(groups = ValidateForCompany.class)
	private String companyAddress;
	private String companyEmail;
	private String companyPhone;

	@NotEmpty
	private String placeOfRegister;
	private String startDate;
	private String endDate;

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
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword
	 *            the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname
	 *            the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	 * @return the choseCompany
	 */
	public String getChoseCompany() {
		return choseCompany;
	}

	/**
	 * @param choseCompany
	 *            the choseCompany to set
	 */
	public void setChoseCompany(String choseCompany) {
		this.choseCompany = choseCompany;
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

	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * @param companyAddress
	 *            the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * @return the companyEmail
	 */
	public String getCompanyEmail() {
		return companyEmail;
	}

	/**
	 * @param companyEmail
	 *            the companyEmail to set
	 */
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	/**
	 * @return the companyPhone
	 */
	public String getCompanyPhone() {
		return companyPhone;
	}

	/**
	 * @param companyPhone
	 *            the companyPhone to set
	 */
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
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

	public InsuranceInfo(String fullname, String gender, String birthdate, String insuranceNumber, String startDate,
			String endDate, String placeOfRegister, int companyId, String username, String userPassword) {
		super();
		this.username = username;
		this.userPassword = userPassword;
		this.insuranceNumber = insuranceNumber;
		this.fullname = fullname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.companyId = companyId+"";
		this.placeOfRegister = placeOfRegister;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public InsuranceInfo() {
		super();
	}

}
