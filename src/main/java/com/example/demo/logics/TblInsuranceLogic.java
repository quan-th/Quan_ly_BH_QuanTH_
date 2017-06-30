/**
 * Copyright(C) 2016  Luvina
 * TblInsuranceLogic.java,Jan 4, 2017,HP
 */
package com.example.demo.logics;

import com.example.demo.entities.InsuranceInfo;

/**
 * @author HP
 *
 * TblInsuranceLogic
 */
public interface TblInsuranceLogic {
	/**
	 * Kiểm tra mã thẻ bảo hiểm tồn tại
	 * 
	 * @param number
	 *            số thẻ bảo hiểm
	 * @return true nếu tồn tại false nếu không
	 */
	public boolean checkExist(String number);
	/**
	 * Thêm thông tin thẻ bảo hiểm
	 * @param insuranceInfo 
	 * @return true nếu thành công false nếu không thành công.
	 */
	public boolean insertOrUpdateInsurance(InsuranceInfo insuranceInfo);
	/**
	 * Lấy thông tin thẻ bảo hiểm
	 * @param userId id ngư�?i dùng
	 * @return thông tin thẻ bảo hiểm
	 */
	public InsuranceInfo getInsuranceInfo(int userId);
	/**
	 * Xoá thông tin bảo hiểm
	 * @param id userid
	 * @return true nếu xoá thành công, false nếu xoá không thành công
	 */
	public boolean deleteInsurance(int id);
}
