/**
 * Copyright(C) 2016  Luvina
 * TblInsuranceDao.java,Jan 4, 2017,HP
 */
package com.example.demo.daos;

import com.example.demo.entities.InsuranceInfo;
import com.example.demo.entities.TblCompany;
import com.example.demo.entities.TblInsurance;
import com.example.demo.entities.TblUser;

/**
 * @author HP
 *
 *         TblInsuranceDao
 */
public interface TblInsuranceDao {
	/**
	 * Kiểm tra mã thẻ bảo hiểm tồn tại
	 * 
	 * @param number
	 *            số thẻ bảo hiểm
	 * @return true nếu tồn tại false nếu không
	 */
	public boolean checkExist(String number);
	/**
	 * Thêm/ sua thông tin thẻ bảo hiểm
	 * @param insuranceInfo 
	 * @return true nếu thành công false nếu không thành công.
	 * @throws Exception 
	 */
	public boolean insertOrUpdateInsurance(InsuranceInfo insuranceInfo,TblCompany company,TblUser user,TblInsurance insurance) throws Exception;
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
	public boolean deleteInsurance(int id) throws Exception;
	/**
	 * Lay TblIsurance theo Id
	 * @param id userId
	 * @return TblInsurance
	 */
	public TblInsurance getTblInsuranceByUserId(int id);
}
