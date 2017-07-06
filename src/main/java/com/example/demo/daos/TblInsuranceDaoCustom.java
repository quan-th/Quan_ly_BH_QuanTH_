/**
 * 
 */
package com.example.demo.daos;

import com.example.demo.entities.InsuranceInfo;
import com.example.demo.entities.TblCompany;
import com.example.demo.entities.TblInsurance;
import com.example.demo.entities.TblUser;

/**
 * @author tranhongquan
 *
 */
public interface TblInsuranceDaoCustom {
	/**
	 * Lấy thông tin thẻ bảo hiểm
	 * @param userId id người dùng
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
