/**
 * Copyright(C) 2016  Luvina
 * SearchingInfo.java,Dec 19, 2016,HP
 */
package com.example.demo.entities;

/**
 * @author HP
 *
 *         SearchingInfo lớp chứa các thông tin tìm kiếm
 */
public class SearchingInfo {
	private String username="";
	private String insuranceId="";
	private String placeOfRegister="";
	private String currentPage = "1";
	private String orderByName = "ASC";
	private String idNumber="0";
	private String companyId="0";

	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the orderByName
	 */
	public String getOrderByName() {
		return orderByName;
	}

	/**
	 * @param orderByName
	 *            the orderByName to set
	 */
	public void setOrderByName(String orderByName) {
		this.orderByName = orderByName;
	}



	/**
	 * @return the currentPage
	 */
	public String getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
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
	 * @return the insuranceId
	 */
	public String getInsuranceId() {
		return insuranceId;
	}

	/**
	 * @param insuranceId
	 *            the insuranceId to set
	 */
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
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
