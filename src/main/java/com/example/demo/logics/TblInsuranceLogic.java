/**
 * Copyright(C) 2016  Luvina
 * TblInsuranceLogic.java,Jan 4, 2017,HP
 */
package com.example.demo.logics;

import com.example.demo.entities.InsuranceInfo;

/**
 * @author HP
 *
 * interface TblInsuranceLogic
 */
public interface TblInsuranceLogic {
	/**
	 * check exist Insurance
	 * @param number Insurance number
	 * @return true nếu tồn tại false nếu không
	 */
	public boolean checkExist(String number);
	/**
	 * insert Or Update Insurance
	 * @param insuranceInfo 
	 * @return true if success
	 */
	public boolean insertOrUpdateInsurance(InsuranceInfo insuranceInfo);
	/**
	 * get Insurance Detail
	 * @param userId user internal Id
	 * @return insurance detail
	 */
	public InsuranceInfo getInsuranceInfo(int userId);
	/**
	 * delete Insurance
	 * @param userId user internal Id
	 * @return true if success
	 */
	public boolean deleteInsurance(int userId);
	/**
	 * Check Valid Insurance Number
	 * @param insuranceInfo
	 * @return true if valid, false if invalid
	 */
	public boolean checkValidInsuranceForUpdate(InsuranceInfo insuranceInfo);
}
